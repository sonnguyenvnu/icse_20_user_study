/*
 * Copyright 2003-2011 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.newTypesystem.state;


import gnu.trove.THashMap;
import gnu.trove.THashSet;
import jetbrains.mps.errors.IErrorReporter;
import jetbrains.mps.errors.messageTargets.NodeMessageTarget;
import jetbrains.mps.lang.typesystem.runtime.ICheckingRule_Runtime;
import jetbrains.mps.lang.typesystem.runtime.IsApplicableStatus;
import jetbrains.mps.logging.Logger;
import jetbrains.mps.newTypesystem.TypesUtil;
import jetbrains.mps.newTypesystem.VariableIdentifier;
import jetbrains.mps.newTypesystem.context.TracingTypecheckingContext;
import jetbrains.mps.newTypesystem.operation.AbstractOperation;
import jetbrains.mps.newTypesystem.operation.AddRemarkOperation;
import jetbrains.mps.newTypesystem.operation.ApplyRuleOperation;
import jetbrains.mps.newTypesystem.operation.CheckAllOperation;
import jetbrains.mps.newTypesystem.operation.ClearNodeTypeOperation;
import jetbrains.mps.newTypesystem.operation.SolveInequalitiesOperation;
import jetbrains.mps.newTypesystem.operation.SubstituteTypeOperation;
import jetbrains.mps.newTypesystem.operation.block.AddBlockOperation;
import jetbrains.mps.newTypesystem.operation.block.AddDependencyOperation;
import jetbrains.mps.newTypesystem.operation.block.RemoveBlockOperation;
import jetbrains.mps.newTypesystem.operation.block.RemoveDependencyOperation;
import jetbrains.mps.newTypesystem.state.blocks.Block;
import jetbrains.mps.newTypesystem.state.blocks.BlockKind;
import jetbrains.mps.newTypesystem.state.blocks.CheckEquationBlock;
import jetbrains.mps.newTypesystem.state.blocks.ComparableBlock;
import jetbrains.mps.newTypesystem.state.blocks.ConditionKind;
import jetbrains.mps.newTypesystem.state.blocks.InequalityBlock;
import jetbrains.mps.newTypesystem.state.blocks.RelationKind;
import jetbrains.mps.newTypesystem.state.blocks.WhenConcreteBlock;
import jetbrains.mps.smodel.SModelUtil_new;
import jetbrains.mps.smodel.SNodeUtil;
import jetbrains.mps.typesystem.inference.EquationInfo;
import jetbrains.mps.typesystem.inference.TypeCheckingContext;
import jetbrains.mps.typesystem.inference.TypeSubstitution;
import jetbrains.mps.typesystem.inference.util.StructuralNodeSet;
import jetbrains.mps.util.IterableUtil;
import jetbrains.mps.util.Pair;
import jetbrains.mps.util.containers.ManyToManyMap;
import org.apache.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeAccessUtil;

import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class State {
  private static final Logger LOG = Logger.wrap(LogManager.getLogger(State.class));

  private final TypeCheckingContext myTypeCheckingContext;

  private final Equations myEquations;
  private final Inequalities myInequalities;
  private final NodeMaps myNodeMaps;

  private final VariableIdentifier myVariableIdentifier;

  private final Stack<AbstractOperation> myOperationStack;
  private AbstractOperation myOperation;
  private List<AbstractOperation> myOperationsAsList;
  private boolean myInsideStateChangeAction = false;


  @StateObject
  private final Map<ConditionKind, ManyToManyMap<SNode, Block>> myBlocksAndInputs =
      new THashMap<>();

  @StateObject
  private final BlockSet myBlocks = new BlockSet();

  public State(TypeCheckingContext tcc, AbstractOperation operation) {
    myTypeCheckingContext = tcc;
    myEquations = new Equations(this);
    myInequalities = createInequalities();
    myNodeMaps = new NodeMaps(this);
    myVariableIdentifier = new VariableIdentifier();
    {
      myBlocksAndInputs.put(ConditionKind.SHALLOW, new ManyToManyMap<>());
      myBlocksAndInputs.put(ConditionKind.CONCRETE, new ManyToManyMap<>());
    }
    myOperationStack = new Stack<>();
    myOperation = operation;
    myOperationStack.push(myOperation);
  }

  public State(TypeCheckingContext tcc) {
    this(tcc, new CheckAllOperation());
  }

  protected Inequalities createInequalities() {
    return new Inequalities(this);
  }

  @StateMethod
  public void addDependency(Block dataFlowBlock, SNode var, ConditionKind condition) {
    assertIsInStateChangeAction();
    ManyToManyMap<SNode, Block> map = myBlocksAndInputs.get(condition);
    map.addLink(var, dataFlowBlock);
  }

  @StateMethod
  public void removeDependency(Block dataFlowBlock, SNode var, ConditionKind condition) {
    assertIsInStateChangeAction();
    ManyToManyMap<SNode, Block> map = myBlocksAndInputs.get(condition);
    map.removeLink(var, dataFlowBlock);
  }

  @StateMethod
  public void removeBlockNoVars(Block dataFlowBlock) {
    assertIsInStateChangeAction();
    for (ManyToManyMap<SNode, Block> map : myBlocksAndInputs.values()) {
      if (myInequalities.isSolvingInProcess()) {
        //we can remove blocks with vars while solving inequalities
        map.clearSecond(dataFlowBlock);
      } else {
        assert !map.containsSecond(dataFlowBlock);
      }
    }
    boolean removed = myBlocks.remove(dataFlowBlock);
    assert removed;
  }

  @StateMethod
  public void addBlockNoVars(Block dataFlowBlock) {
    assertIsInStateChangeAction();
    for (ManyToManyMap<SNode, Block> map : myBlocksAndInputs.values()) {
      assert !map.containsSecond(dataFlowBlock) || myInequalities.isSolvingInProcess();
    }
    boolean addedAnew = myBlocks.add(dataFlowBlock);
    assert addedAnew;
  }

  public void applyRuleToNode(SNode node, ICheckingRule_Runtime rule, IsApplicableStatus status) {
    try {
      executeOperation(new ApplyRuleOperation(node, rule, status));
    } catch (Throwable t) {
      LOG.error("an error occurred while applying rule to node " + node, t, node);
    }
  }

  public void substitute(SNode oldVar, SNode type) {
    for (ConditionKind conditionKind : new THashSet<>(myBlocksAndInputs.keySet())) {
      ManyToManyMap<SNode, Block> map = myBlocksAndInputs.get(conditionKind);
      Set<Block> blocks = map.getByFirst(oldVar);
      if (blocks == null) {
        return;
      }
      List<SNode> unresolvedInputs = conditionKind.getUnresolvedInputs(type, this);
      for (Block block : new THashSet<>(blocks)) {
        for (SNode variable : unresolvedInputs) {
          addInputAndTrack(block, variable, conditionKind);
        }
        removeInputAndTrack(block, oldVar, conditionKind);
        testInputsResolved(block);
      }
    }
  }

  private void addInputAndTrack(Block block, SNode var, ConditionKind conditionKind) {
    executeOperation(new AddDependencyOperation(block, var, conditionKind));
  }

  private void removeInputAndTrack(Block block, SNode var, ConditionKind conditionKind) {
    executeOperation(new RemoveDependencyOperation(block, var, conditionKind));
  }

  private void becameConcrete(Block block) {
    executeOperation(new RemoveBlockOperation(block));
  }

  public void addBlock(Block block) {
    executeOperation(new AddBlockOperation(block));
  }

  public boolean clearNode(SNode node) {
    SNode type = myNodeMaps.getType(node);
    List<IErrorReporter> nodeErrors = myNodeMaps.getNodeErrors(node);
    if (type != null || (nodeErrors != null && !nodeErrors.isEmpty())) {
      executeOperation(new ClearNodeTypeOperation(node, type, nodeErrors));
      return true;
    }
    return false;
  }

  private void testInputsResolved(Block block) {
    if (!myBlocks.contains(block)) return;
    boolean concrete = true;
    for (ManyToManyMap<SNode, Block> map : myBlocksAndInputs.values()) {
      concrete = concrete && map.getBySecond(block).isEmpty();
    }
    if (concrete) {
      becameConcrete(block);
    }
  }

  public void collectVarsExecuteIfNecessary(Block block) {
    Set<Pair<SNode, ConditionKind>> initialInputs = block.getInitialInputs();
    for (Pair<SNode, ConditionKind> input : initialInputs) {
      SNode type = input.o1;
      ConditionKind conditionKind = input.o2;
      for (SNode variable : conditionKind.getUnresolvedInputs(type, this)) {
        addInputAndTrack(block, variable, conditionKind);
      }
    }
    testInputsResolved(block);
  }


  //---------------------------------------------

  public Equations getEquations() {
    return myEquations;
  }

  public boolean addEquation(SNode left, SNode right, EquationInfo info) {
    return myEquations.addEquation(left, right, info);
  }

  public void addEquation(SNode left, SNode right, EquationInfo info, boolean checkOnly) {

    // substitute correct type
    left = lookupTypeSubstitution(left);
    right = lookupTypeSubstitution(right);

    if (!checkOnly) {
      addEquation(left, right, info);

    }
    else{
      if (myTypeCheckingContext.isSingleTypeComputation()) return; //no need to check if we don't need to report errors)
      addBlock(new CheckEquationBlock(this, left, right, RelationKind.CHECK_EQUATION, info));
    }
  }

  public void addInequality(SNode subType, SNode superType, boolean isWeak, boolean check, EquationInfo info, boolean lessThan) {

    // substitute correct type
    subType = lookupTypeSubstitution(subType);
    superType = lookupTypeSubstitution(superType);

    if (check && myTypeCheckingContext.isSingleTypeComputation()) return; //no need to check if we don't need to report errors
    addBlock(new InequalityBlock(this, subType, superType, lessThan, RelationKind.fromFlags(isWeak, check, false), info));
  }

  public void addComparable(SNode left, SNode right, boolean isWeak, boolean inference, EquationInfo info) {
    if (!inference && myTypeCheckingContext.isSingleTypeComputation()) return; //no need to check if we don't need to report errors)
    addBlock(new ComparableBlock(this, left, right, RelationKind.fromFlags(isWeak, !inference, true), info));
  }

  public NodeMaps getNodeMaps() {
    return myNodeMaps;
  }

  public Inequalities getInequalities() {
    return myInequalities;
  }

  public TypeCheckingContext getTypeCheckingContext() {
    return myTypeCheckingContext;
  }

  public void executeStateChangeAction(Runnable action) {
    try {
      myInsideStateChangeAction = true;
      action.run();
    } finally {
      myInsideStateChangeAction = false;
    }
  }

  public void assertIsInStateChangeAction() {
    LOG.assertLog(myInsideStateChangeAction, "state change can be executed only inside state change action");
  }

  public void executeOperation(AbstractOperation operation) {
    if (operation == null) return;
    if (myTypeCheckingContext instanceof TracingTypecheckingContext) {
      if (!myOperationStack.empty()) {
        myOperationStack.peek().addConsequence(operation);
      }
    }
    if (myTypeCheckingContext instanceof TracingTypecheckingContext || operation.hasEffect()) {
      myOperationStack.push(operation);
      operation.execute(this);
      if (!myOperationStack.empty()) {
        myOperationStack.pop();
      } else {
        LOG.warning("Operation stack in type system state was empty");
      }
    } else {
      operation.execute(this);   //do not store unneeded operations
    }
  }

  private void visit(AbstractOperation operation, List<AbstractOperation> result) {
    result.add(operation);
    for (AbstractOperation child : operation.getConsequences()) {
      visit(child, result);
    }
  }

  public List<AbstractOperation> getOperationsAsList() {
    List<AbstractOperation> result = new ArrayList<>();
    visit(myOperation, result);
    return result;
  }

  public void addError(IErrorReporter error) {
    myNodeMaps.addNodeToError(error);
  }

  public SNode typeOf(SNode node, EquationInfo info) {
    return myNodeMaps.typeOf(node, info);
  }

  public void clear(boolean clearDiff) {
    myEquations.clear();
    myInequalities.clear();
    myNodeMaps.clear();
    myVariableIdentifier.clear();
    myBlocks.clear();
    for (ManyToManyMap map : myBlocksAndInputs.values()) {
      map.clear();
    }
    if (clearDiff) {
      clearOperations();
    }
  }

  public void clearOperations() {
    myOperationStack.clear();
    myOperation = new CheckAllOperation();
    myOperationStack.push(myOperation);
  }

  public void clearStateObjects() {
    if (!(myTypeCheckingContext instanceof TracingTypecheckingContext)/* && myInequalitySystem == null*/) {
      for (Entry<ConditionKind, ManyToManyMap<SNode, Block>> map : myBlocksAndInputs.entrySet()) {
        map.getValue().clear();
      }
      myBlocks.clear();
      myEquations.clear();
      myNodeMaps.clearTypesToNodes();
    }
    clearOperations();
  }


  public void solveInequalities() {
    if (!myInequalities.getRelationsToSolve().isEmpty()) {
      executeOperation(new SolveInequalitiesOperation(() -> myInequalities.solveRelations()));
    }
  }

  public void checkNonConcreteWhenConcretes() {
    for (Block block : getBlocks()) {
      if (block.getBlockKind().equals(BlockKind.WHEN_CONCRETE)) {
        WhenConcreteBlock wCBlock = (WhenConcreteBlock) block;
        if (!wCBlock.isSkipError()) {
          SNode node = myNodeMaps.getNode(wCBlock.getArgument());
          if (node != null) {
            SConcept concept = node.getConcept();
            boolean isRuntime = concept.equals(SNodeUtil.concept_RuntimeTypeVariable);
            if (!concept.isAbstract() && !isRuntime) {
              myTypeCheckingContext.reportWarning(node, "argument of WHEN CONCRETE block is never concrete",
                  wCBlock.getNodeModel(), wCBlock.getNodeId(), null, new NodeMessageTarget());
            }
          }
        }
      }
    }
  }

  public void performActionsAfterChecking() {
    checkNonConcreteWhenConcretes();
  }

  public SNode expand(SNode node) {
    return myEquations.expandNode(node, false);
  }

  public SNode expandFinal(SNode node) {
    return myEquations.expandNode(node, true);
  }

  public AbstractOperation getOperation() {
    return myOperation;
  }

  public void expandAll(final Set<SNode> nodes, final boolean finalExpansion) {
    if (nodes != null && !nodes.isEmpty()) {
      executeOperation(new AddRemarkOperation("Types Expansion", () -> myNodeMaps.expandAll(nodes, finalExpansion)));
    }
  }

  public boolean executeOperationsBeforeAnchor(AbstractOperation firstOp, Object anchor) {
    firstOp.redo(this);
    if (firstOp.equals(anchor)) {
      return true;
    }
    for (AbstractOperation child : firstOp.getConsequences()) {
      if (executeOperationsBeforeAnchor(child, anchor)) {
        return true;
      }
    }
    return false;
  }

  public SNode getRepresentative(SNode node) {
    return myEquations.getRepresentative(node);
  }

  public SNode createNewRuntimeTypesVariable() {
    SNode typeVar = SModelUtil_new.instantiateConceptDeclaration(SNodeUtil.concept_RuntimeTypeVariable, null, null, false);
    //todo this code should be moved into MPS
    SNodeAccessUtil.setProperty(typeVar, SNodeUtil.property_INamedConcept_name, myVariableIdentifier.getNewVarName());
    return typeVar;
  }

  public Set<Block> getBlocks() {
    return Collections.unmodifiableSet(myBlocks);
  }

  public Set<Block> getBlocks(BlockKind kind) {
    return myBlocks.getBlocks(kind);
  }

  private void executeOperationsFromTo(int from, int to) {
    assert from < to;
    for (int i = from + 1; i <= to; i++) {
      myOperationsAsList.get(i).redo(this);
    }
  }

  private void revertOperationsFromTo(int from, int to) {
    assert from > to;
    for (int i = from; i > to; i--) {
      myOperationsAsList.get(i).undo(this);
    }
  }

  public void updateState(AbstractOperation from, AbstractOperation to) {
    if (myOperationsAsList == null) {
      myOperationsAsList = getOperationsAsList();
    }
    int fromIndex = myOperationsAsList.indexOf(from);
    int toIndex = myOperationsAsList.indexOf(to);
    if (fromIndex > toIndex) {
      revertOperationsFromTo(fromIndex, toIndex);
    } else if (fromIndex < toIndex) {
      executeOperationsFromTo(fromIndex, toIndex);
    }
  }

  @Nullable
  private SNode lookupTypeSubstitution(SNode origType) {
    if (origType == null || TypesUtil.isVariable(origType)) return origType;

    SNode newType = origType;

    // exhaustively apply substitutions until the operation has no effect
    StructuralNodeSet<SNode> seen = new StructuralNodeSet<>();

    TypeSubstitution typeSubs = myTypeCheckingContext.getSubstitution(origType);
    while (typeSubs != null && typeSubs.isValid()) {

      newType = typeSubs.getNewNode();
      if (seen.containsStructurally(newType)) {
        break;
      }
      seen.addStructurally(newType);

      executeOperation(new SubstituteTypeOperation(typeSubs));

      // next iteration
      typeSubs = myTypeCheckingContext.getSubstitution(newType);
    }

    return newType;
  }

  /** Nulls are not allowed. Not serializable. Not cloneable. */
  private static class BlockSet extends AbstractSet<Block> {

    private EnumMap<BlockKind, Set<Block>> myBlockKindsToBlocks = new EnumMap<>(BlockKind.class);
    private Iterable<Block>[] myBlockSetArray;

    @SuppressWarnings("unchecked")
    BlockSet () {
      for(BlockKind bk: BlockKind.values()) {
        myBlockKindsToBlocks.put(bk, new THashSet<>());
      }
      ArrayList<Iterable<Block>> sets = new ArrayList<>();
      for(BlockKind bk: BlockKind.values()) {
        sets.add(myBlockKindsToBlocks.get(bk));
      }
      final Iterable<Block>[] arr = (Iterable<Block>[]) Array.newInstance(Iterable.class, BlockKind.values().length);
      myBlockSetArray = sets.toArray(arr);
    }

    public Set<Block> getBlocks(BlockKind bk) {
      return Collections.unmodifiableSet(myBlockKindsToBlocks.get(bk));
    }

    @Override
    public int size() {
      int count = 0;
      for(BlockKind bk: BlockKind.values()) {
        count += myBlockKindsToBlocks.get(bk).size();
      }
      return count;
    }

    @Override
    @NotNull
    public Iterator<Block> iterator() {
      return IterableUtil.merge(myBlockSetArray).iterator();
    }

    @Override
    public boolean contains(Object o) {
      if (!(o instanceof Block)) return false;
      final Block blk = (Block) o;
      return myBlockKindsToBlocks.get(blk.getBlockKind()).contains(blk);
    }

    @Override
    public boolean add(Block block) {
      if (block == null) throw new IllegalArgumentException("nulls not aloowed");
      return myBlockKindsToBlocks.get(block.getBlockKind()).add(block);
    }

    @Override
    public boolean remove(Object o) {
      if (!(o instanceof Block)) return false;
      final Block blk = (Block) o;
      return myBlockKindsToBlocks.get(blk.getBlockKind()).remove(blk);
    }

    @Override
    public void clear() {
      for(BlockKind bk: BlockKind.values()) {
        myBlockKindsToBlocks.get(bk).clear();
      }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
      throw new CloneNotSupportedException();
    }
  }


}
