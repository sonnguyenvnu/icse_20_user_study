/*
 * Copyright 2003-2017 JetBrains s.r.o.
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
package jetbrains.mps.ide.typesystem.trace;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.ui.JBColor;
import jetbrains.mps.ide.ui.tree.MPSTree;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.ide.ui.tree.TextTreeNode;
import jetbrains.mps.ide.util.ColorAndGraphicsUtil;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.SNodeOperations;
import jetbrains.mps.newTypesystem.TypesUtil;
import jetbrains.mps.newTypesystem.operation.AbstractOperation;
import jetbrains.mps.newTypesystem.operation.AddErrorOperation;
import jetbrains.mps.newTypesystem.operation.ApplyRuleOperation;
import jetbrains.mps.newTypesystem.operation.AssignTypeOperation;
import jetbrains.mps.newTypesystem.operation.ExpandTypeOperation;
import jetbrains.mps.newTypesystem.operation.TraceWarningOperation;
import jetbrains.mps.newTypesystem.operation.block.AbstractBlockOperation;
import jetbrains.mps.newTypesystem.operation.block.AddDependencyOperation;
import jetbrains.mps.newTypesystem.operation.block.RemoveDependencyOperation;
import jetbrains.mps.newTypesystem.operation.equation.AddEquationOperation;
import jetbrains.mps.newTypesystem.state.State;
import jetbrains.mps.newTypesystem.state.blocks.Block;
import jetbrains.mps.nodeEditor.DefaultEditorMessage;
import jetbrains.mps.nodeEditor.EditorComponent;
import jetbrains.mps.nodeEditor.NodeHighlightManager;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.message.EditorMessageOwner;
import jetbrains.mps.openapi.editor.message.SimpleEditorMessage;
import jetbrains.mps.openapi.editor.style.StyleRegistry;
import jetbrains.mps.project.Project;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.ModelReadRunnable;
import jetbrains.mps.util.Computable;
import jetbrains.mps.util.Pair;
import jetbrains.mps.workbench.MPSDataKeys;
import jetbrains.mps.workbench.action.ActionUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TypeSystemTraceTree extends MPSTree implements DataProvider {
  private final Project myProject;
  private final TypecheckingContextTracker myContextTracker;

  private final SNode mySelectedNode;
  private Set<SNode> myNodes;
  private TypeSystemTracePanel myParent;
  private EditorComponent myEditorComponent;
  private List<TypeSystemTraceTreeNode> myErrorNodes = new LinkedList<>();
  private TypeSystemTraceTree.DetailsTree myDetailsTree;
  private final NodeHighlightManager myHighlightManager;
  private EditorMessageOwner myMessageOwner;

  public TypeSystemTraceTree(Project mpsProject, SNode node, TypeSystemTracePanel parent, EditorComponent editorComponent) {
    myProject = mpsProject;
    myContextTracker = new TypecheckingContextTracker(node.getContainingRoot());
    myParent = parent;
    myEditorComponent = editorComponent;
    mySelectedNode = node;
    initNodes(node);

    setGenerationMode(TraceSettings.isGenerationMode());

    this.myHighlightManager = editorComponent.getHighlightManager();
    this.myMessageOwner = new EditorMessageOwner() {
    };
    EditorCell nodeCell = myEditorComponent.findNodeCell(mySelectedNode);
    if (nodeCell != null) {
      myHighlightManager.mark(new TypeSystemTraceTree.SelectedNodeEditorMessage(nodeCell, ""));
    }

    this.rebuildNow();
    expandAll();
    this.myDetailsTree = new TypeSystemTraceTree.DetailsTree(null);
    addTreeSelectionListener(new TypeSystemTraceTree.ShowDetailsUpdater());
    getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
  }

  private void initNodes(SNode node) {
    myNodes = new HashSet<>();
    myNodes.addAll(SNodeOperations.getNodeDescendants(node, null, false, new SConcept[]{}));
    myNodes.add(node);
  }

  @Override
  protected void runRebuildAction(Runnable rebuildAction, boolean saveExpansion) {
    super.runRebuildAction(new ModelReadRunnable(myProject.getModelAccess(), rebuildAction), saveExpansion);
  }

  /*package*/ MPSTree getDetailsTree() {
    return myDetailsTree;
  }

  public void rebuildTrace() {
    myContextTracker.checkRoot(true);
    this.rebuildNow();
    this.expandAll();
  }

  public void setGenerationMode(boolean generationMode) {
    myContextTracker.setGenerationMode(generationMode, mySelectedNode);
  }

  public State getState() {
    return myContextTracker.getStateCopy();
  }

  @Override
  protected MPSTreeNode rebuild() {
    setRootVisible(false);
    setGenerationMode(TraceSettings.isGenerationMode());
    if (TraceSettings.isTraceForSelectedNode() && mySelectedNode != null) {
      getSliceVars(myContextTracker.getOperation());
    }
    MPSTreeNode result = create(myContextTracker.getOperation(), true);
    if (result == null) {
      result = new TextTreeNode("Empty type system trace");
    }
    // <node>
    return result;
  }

  public TypeSystemTraceTreeNode create(AbstractOperation operation, boolean withChildren) {
    if (!(filterNodeType(operation))) {
      return null;
    }
    final boolean showNode = showNodeRecursively(operation);
    List<TypeSystemTraceTreeNode> children = new ArrayList<>();
    if (withChildren) {
      for (AbstractOperation consequence : operation.getConsequences()) {
        TypeSystemTraceTreeNode node = create(consequence, false);
        if (node != null) {
          children.add(node);
        }
      }
    } else if (!(showNode)) {
      if (!(operation.getConsequences().iterator().hasNext())) {
        return null;
      }
    }
    final boolean hasAnError = hasAnErrorAsConsequence(operation);
    TypeSystemTraceTreeNode result = new TypeSystemTraceTreeNode(operation, myContextTracker.getCurrentState(), myEditorComponent) {
      @Override
      public void doUpdatePresentation() {
        super.doUpdatePresentation();
        if (!(showNode)) {
          setColor(JBColor.GRAY);
        } else if (hasAnError) {
          setColor(JBColor.RED);
        }
      }
    };
    for (TypeSystemTraceTreeNode node : children) {
      result.add(node);
    }
    if (hasAnError) {
      myErrorNodes.add(result);
    }
    return result;
  }

  private boolean hasAnErrorAsConsequence(AbstractOperation operation) {
    if (operation instanceof AddErrorOperation || operation instanceof TraceWarningOperation) {
      return true;
    }
    for (AbstractOperation consequence : operation.getConsequences()) {
      if (hasAnErrorAsConsequence(consequence)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void dispose() {
    myHighlightManager.clearForOwner(myMessageOwner);
    myContextTracker.dispose();
    super.dispose();
  }

  private boolean showNode(AbstractOperation diff) {
    if (!(TraceSettings.isTraceForSelectedNode())) {
      return true;
    }
    if (mySelectedNode == null) {
      return true;
    }
    if (myNodes.contains(diff.getSource())) {
      return true;
    }
    if (diff instanceof AddEquationOperation) {
      AddEquationOperation eq = (AddEquationOperation) diff;
      if (myNodes.contains(eq.getChild()) || myNodes.contains(eq.getParent())) {
        return true;
      }
    }
    if (diff instanceof AbstractBlockOperation) {
      Block block = ((AbstractBlockOperation) diff).getBlock();
      for (SNode node : block.getInputs()) {
        if (myNodes.contains(node)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean showNodeRecursively(AbstractOperation diff) {
    if (diff == null) {
      return false;
    }
    for (AbstractOperation csq : diff.getConsequences()) {
      if (showNodeRecursively(csq)) {
        return true;
      }
    }
    return showNode(diff);
  }

  private boolean filterNodeType(AbstractOperation operation) {
    if (!(TraceSettings.isShowTypesExpansion()) && operation instanceof ExpandTypeOperation) {
      return false;
    }
    if (!(TraceSettings.isShowApplyRuleOperations()) && operation instanceof ApplyRuleOperation) {
      return false;
    }
    return TraceSettings.isShowBlockDependencies() || (!(operation instanceof AddDependencyOperation) && !(operation instanceof RemoveDependencyOperation));
  }

  public void goToNextError() {
    int currentRow = -1;
    MPSTreeNode currentNode = this.getCurrentNode();
    if (null != currentNode) {
      currentRow = this.getRowForPath(new TreePath(currentNode.getPath()));
    }
    MPSTreeNode errorNode = getNextErrorNode(currentRow);
    if (null != errorNode) {
      this.scrollPathToVisible(new TreePath(errorNode.getPath()));
      this.selectNode(errorNode);
    }
  }

  private MPSTreeNode getNextErrorNode(int row) {
    for (TypeSystemTraceTreeNode errorNode : myErrorNodes) {
      TreePath errorPath = new TreePath(errorNode.getPath());
      int errorRow = this.getRowForPath(errorPath);
      if (errorRow > row) {
        return errorNode;
      }
    }
    if (!(myErrorNodes.isEmpty())) {
      return myErrorNodes.get(0);
    }
    return null;
  }

  private void getSliceVars(AbstractOperation diff) {
    if (diff == null) {
      return;
    }
    if (diff instanceof AddEquationOperation) {
      AddEquationOperation eq = (AddEquationOperation) diff;
      SNode child = eq.getChild();
      SNode parent = eq.getParent();
      if (myNodes.contains(child)) {
        myNodes.addAll(TypesUtil.getVariables(parent, myContextTracker.getStateCopy()));
      }
      if (myNodes.contains(parent)) {
        myNodes.addAll(TypesUtil.getVariables(child, myContextTracker.getStateCopy()));
      }
    }
    if (diff instanceof AssignTypeOperation) {
      AssignTypeOperation typeDifference = (AssignTypeOperation) diff;
      if (myNodes.contains(typeDifference.getNode()) && TypesUtil.isVariable(typeDifference.getType())) {
        myNodes.add(typeDifference.getType());
      }
    }
    for (AbstractOperation childDiff : diff.getConsequences()) {
      getSliceVars(childDiff);
    }
  }

  @Override
  @Nullable
  public Object getData(@NonNls final String id) {
    MPSTreeNode currentNode = this.getCurrentNode();
    if (currentNode instanceof TypeSystemTraceTreeNode) {
      return new ModelAccessHelper(myProject.getModelAccess()).runReadAction(() -> _getData(id));
    }
    return null;
  }

  private Object _getData(String id) {
    AbstractOperation operation = getAssociatedOperation(this.getCurrentNode());
    if (operation == null) {
      return null;
    }
    final SNodeReference rule = operation.getRule();
    final SNode source = operation.getSource();
    if (MPSDataKeys.RULE_MODEL_AND_ID.is(id) && rule != null) {
      return new Pair<>(String.valueOf(rule.getModelReference()), String.valueOf(rule.getNodeId()));
    }
    if (MPSDataKeys.SOURCE_NODE.is(id) && source != null && source.getModel() != null) {
      return source;
    }
    return null;
  }

  @Override
  protected ActionGroup createPopupActionGroup(final MPSTreeNode treeNode) {
    return ActionUtils.groupFromActions(ActionManager.getInstance().getAction("jetbrains.mps.ide.actions.GoToNode_Action"));
  }

  private void showState(final TypeSystemTraceTreeNode newNode) {
    myProject.getModelAccess().runReadAction(() -> {
      Object difference = newNode.getUserObject();
      myParent.resetState(myContextTracker.resetCurrentState((AbstractOperation) difference));

      final State newState = myContextTracker.updateCurrentState((AbstractOperation) difference);
      if (newState != null) {
        myParent.updateState(newState);
      }
    });
  }

  private void showState(final MPSTreeNode fromNode, final MPSTreeNode toNode) {
    myProject.getModelAccess().runReadAction(() -> {
      Object fromDiff = fromNode.getUserObject();
      Object toDiff = toNode.getUserObject();

      myParent.resetState(myContextTracker.resetCurrentState((AbstractOperation) fromDiff));
      myParent.updateState(myContextTracker.updateCurrentState((AbstractOperation) fromDiff, (AbstractOperation) toDiff));
    });
  }


  private void showDetails(MPSTreeNode treeNode) {
    myDetailsTree.setOperation(getAssociatedOperation(treeNode));
  }

  private void showDetails(Collection<? extends MPSTreeNode> treeNodes) {
    List<AbstractOperation> operations = new ArrayList<>();
    for (MPSTreeNode treeNode : treeNodes) {
      operations.add(getAssociatedOperation(treeNode));
    }
    myDetailsTree.setOperations(operations);
  }

  private class ShowDetailsUpdater implements TreeSelectionListener {
    private ShowDetailsUpdater() {
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
      TreePath[] selectionPaths = getSelectionPaths();
      if (selectionPaths != null && selectionPaths.length >= 1) {
        if (selectionPaths.length == 1) {
          TreePath path = selectionPaths[0];
          if (path == null) {
            return;
          }
          MPSTreeNode treeNode = (MPSTreeNode) path.getLastPathComponent();
          showState((TypeSystemTraceTreeNode) treeNode);
          showDetails(treeNode);
        } else {
          TreePath fromPath = selectionPaths[0];
          TreePath toPath = selectionPaths[selectionPaths.length - 1];
          showState((MPSTreeNode) fromPath.getLastPathComponent(), (MPSTreeNode) toPath.getLastPathComponent());
          List<MPSTreeNode> selectedNodes = new ArrayList<>();
          for (TreePath tp : selectionPaths) {
            selectedNodes.add((MPSTreeNode) tp.getLastPathComponent());
          }
          showDetails(selectedNodes);
        }
      }
    }
  }

  private class SelectedNodeEditorMessage extends DefaultEditorMessage {
    private EditorCell myCell;

    public SelectedNodeEditorMessage(EditorCell cell, String message) {
      super(cell.getSNode(), StyleRegistry.getInstance().getSimpleColor(new Color(192, 255, 255)), message, myMessageOwner);
      this.myCell = cell;
    }

    @Override
    public EditorCell getCell(EditorComponent component) {
      return myCell;
    }

    @Override
    public boolean acceptCell(EditorCell cell, EditorComponent component) {
      return myCell == cell;
    }

    @Override
    public boolean sameAs(SimpleEditorMessage that) {
      return super.sameAs(that) && this.equals(that);
    }

    @Override
    protected void paintWithColor(Graphics graphics, EditorCell cell, Color color) {
      int x = cell.getX() + cell.getLeftInset();
      int y = cell.getY() + cell.getTopInset();
      int width = cell.getWidth() - cell.getLeftInset() - cell.getRightInset() - 1;
      int height = cell.getHeight() - cell.getTopInset() - cell.getBottomInset() - 1;

      graphics.setColor(color);
      ColorAndGraphicsUtil.fillStripes(graphics, x, y, width, height);
    }

    @Override
    public boolean isBackground() {
      return true;
    }

    @Override
    public boolean equals(Object that) {
      if (that == null) {
        return false;
      }
      if (this == that) {
        return true;
      }
      if (that.getClass() != TypeSystemTraceTree.SelectedNodeEditorMessage.class) {
        return false;
      }
      return this.myCell.equals(((TypeSystemTraceTree.SelectedNodeEditorMessage) that).myCell);
    }

    @Override
    public int hashCode() {
      return myCell.hashCode() * 37;
    }
  }

  public class DetailsTree extends MPSTree implements DataProvider {
    private List<AbstractOperation> myOperations;

    public DetailsTree(AbstractOperation operation) {
      this.myOperations = Collections.singletonList(operation);
      rebuildNow();
      expandAll();
      addTreeSelectionListener(new TypeSystemTraceTree.DetailsTree.ShowStateUpdater());
    }

    @Override
    protected void runRebuildAction(Runnable rebuildAction, boolean saveExpansion) {
      // some operations access SNode properties to compute their presentation during rebuild
      super.runRebuildAction(new ModelReadRunnable(myProject.getModelAccess(), rebuildAction), saveExpansion);
    }

    public void setOperation(AbstractOperation operation) {
      this.myOperations = Collections.singletonList(operation);
      rebuildNow();
      expandAll();
    }

    public void setOperations(Collection<? extends AbstractOperation> operation) {
      this.myOperations = new ArrayList<>(operation);
      rebuildNow();
      expandAll();
    }

    public Collection<MPSTreeNode> create(Collection<? extends AbstractOperation> operations, boolean showParent) {
      if (operations == null || operations.size() == 0 || operations.iterator().next() == null) {
        return null;
      }
      List<MPSTreeNode> result = new ArrayList<>();
      for (AbstractOperation operation : operations) {
        final boolean showNode = showNode(operation);
        List<MPSTreeNode> children = new ArrayList<>();
        for (AbstractOperation consequence : operation.getConsequences()) {
          Collection<MPSTreeNode> nodes = create(Collections.singletonList(consequence), true);
          if (nodes != null) {
            children.addAll(nodes);
          }
        }
        if (!(filterNodeType(operation))) {
          continue;
        }
        if (showParent) {
          TypeSystemTraceTreeNode treeNode = new TypeSystemTraceTreeNode(operation, myContextTracker.getCurrentState(), myEditorComponent) {
            @Override
            public void doUpdatePresentation() {
              super.doUpdatePresentation();
              if (!(showNode)) {
                setColor(JBColor.BLACK);
              }
            }
          };
          for (MPSTreeNode node : children) {
            treeNode.add(node);
          }
          result.add(treeNode);
        } else {
          result.addAll(children);
        }
        // <node>
      }
      return result;
    }

    @Override
    @Nullable
    public Object getData(@NonNls final String id) {
      MPSTreeNode currentNode = this.getCurrentNode();
      if (currentNode instanceof TypeSystemTraceTreeNode) {
        return new ModelAccessHelper(myProject.getModelAccess()).runReadAction(() -> _getData(id));
      }
      return null;
    }

    private Object _getData(String id) {
      AbstractOperation operation = getAssociatedOperation(this.getCurrentNode());
      if (operation == null) {
        return null;
      }
      final SNodeReference rule = operation.getRule();
      final SNode source = operation.getSource();
      if (MPSDataKeys.RULE_MODEL_AND_ID.is(id) && rule != null) {
        return new Pair<>(String.valueOf(rule.getModelReference()), String.valueOf(rule.getNodeId()));
      }
      if (MPSDataKeys.SOURCE_NODE.is(id) && source != null && source.getModel() != null) {
        return source;
      }
      return null;
    }

    @Override
    protected ActionGroup createPopupActionGroup(MPSTreeNode node) {
      return ActionUtils.groupFromActions(ActionManager.getInstance().getAction("jetbrains.mps.ide.actions.GoToNode_Action"),
          ActionManager.getInstance().getAction("jetbrains.mps.ide.actions.GoToRule_Action"));
    }

    @Override
    protected MPSTreeNode rebuild() {
      setRootVisible(false);
      MPSTreeNode result;
      Collection<MPSTreeNode> nodes = create(myOperations, false);
      if (nodes == null) {
        result = new TextTreeNode("Empty type system trace");
        setRootVisible(true);
      } else {
        result = new TextTreeNode("Details");
        for (MPSTreeNode node : nodes) {
          result.add(node);
        }
      }
      return result;
    }

    private class ShowStateUpdater implements TreeSelectionListener {
      private ShowStateUpdater() {
      }

      @Override
      public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getNewLeadSelectionPath();
        if (path == null) {
          return;
        }
        Object treeNode = path.getLastPathComponent();
        if (treeNode instanceof TypeSystemTraceTreeNode) {
          showState((TypeSystemTraceTreeNode) treeNode);
        }
      }
    }
  }

  private static AbstractOperation getAssociatedOperation(MPSTreeNode checkedDotOperand) {
    if (null != checkedDotOperand) {
      return (AbstractOperation) checkedDotOperand.getUserObject();
    }
    return null;
  }
}
