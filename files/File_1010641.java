/*
 * Copyright 2003-2018 JetBrains s.r.o.
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
package jetbrains.mps.newTypesystem.context;

import jetbrains.mps.classloading.ClassLoaderManager;
import jetbrains.mps.errors.MessageStatus;
import jetbrains.mps.errors.QuickFixProvider;
import jetbrains.mps.errors.SimpleErrorReporter;
import jetbrains.mps.errors.messageTargets.MessageTarget;
import jetbrains.mps.newTypesystem.SubTypingManagerNew;
import jetbrains.mps.newTypesystem.context.typechecking.IncrementalTypechecking;
import jetbrains.mps.newTypesystem.state.State;
import jetbrains.mps.typesystem.inference.TypeChecker;
import jetbrains.mps.util.Computable;
import jetbrains.mps.util.SNodeOperations;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.HashMap;
import java.util.Map;

public class IncrementalTypecheckingContext extends ReportingTypecheckingContext<State, IncrementalTypechecking> {
  private static Logger LOG = LogManager.getLogger(IncrementalTypecheckingContext.class);
  private final ClassLoaderManager myClassManager;

  private boolean myIsNonTypesystemComputation = false;
//  private boolean myIsInferenceMode = false;

  private Map<Object, Integer> myRequesting = new HashMap<>();
  private Integer myOldHash = 0;

  public IncrementalTypecheckingContext(SNode node, TypeChecker typeChecker, ClassLoaderManager clManager) {
    super(node, typeChecker);
    myClassManager = clManager;
  }

  @Override
  protected IncrementalTypechecking createTypechecking() {
    return new IncrementalTypechecking(getNode(), getState(), getTypeChecker(), myClassManager);
  }

  @Override
  public boolean isSingleTypeComputation() {
    return false;
  }

  public TypeChecker getTypeChecker() {
    return myTypeChecker;
  }

  public SubTypingManagerNew getSubTyping() {
    return (SubTypingManagerNew) myTypeChecker.getSubtypingManager();
  }

  @Override
  public void clear() {
    synchronized (TYPECHECKING_LOCK) {
      getTypechecking().clear();
    }
  }

  @Override
  public final IncrementalTypechecking getBaseNodeTypesComponent() {
    return getTypechecking();
  }

  @Override
  public void addDependencyForCurrent(SNode node) {
    getTypechecking().addDependencyForCurrent(node);
  }

  @Override
  public SNode getTypeOf(SNode node, TypeChecker typeChecker) {
    if (node == null) return null;
    synchronized (TYPECHECKING_LOCK) {
      return  getTypeOf_normalMode(node);
    }
  }

  @Override
  public SNode getTypeOf_generationMode(final SNode node) {
    throw new IllegalStateException("Invalid usage of IncrementalTypecheckingContext");
  }

  @Override
  public SNode getTypeOf_resolveMode(SNode node, TypeChecker typeChecker) {
    throw new IllegalStateException("Invalid usage of IncrementalTypecheckingContext");
  }

  @Override
  public SNode getTypeOf_normalMode(SNode node) {
    if (!checkIfNotChecked(node, false)) return null;
    return getTypeDontCheck(node);
  }

  @Override
  public void dispose() {
    getTypechecking().dispose();
    myRequesting.clear();
    super.dispose();
  }

  @Override
  public boolean messagesChanged(Object requesting) {
    int hash = getTypechecking().getNodesWithErrors(true).hashCode();
    if (hash != myOldHash) {
      myRequesting.clear();
      myOldHash = hash;
    }
    Integer oldHash = myRequesting.get(requesting);
    if (oldHash == null || oldHash != hash) {
      myRequesting.put(requesting, hash);
      return true;
    }
    return false;
  }

  //--------

  @Override
  public boolean isIncrementalMode() {
    return true; /*!myTypeChecker.isGenerationMode() && getState().getInequalitySystem() == null;*/
  }

  public void runTypeCheckingAction(Runnable r) {
    synchronized (TYPECHECKING_LOCK) {
      r.run();
    }
  }

  public <T> T runTypeCheckingAction(Computable<T> c) {
    synchronized (TYPECHECKING_LOCK) {
      return c.compute();
    }
  }

  @Override
  public void setIsNonTypesystemComputation() {
    myIsNonTypesystemComputation = true;
  }

  @Override
  public void resetIsNonTypesystemComputation() {
    myIsNonTypesystemComputation = false;
  }

  @Override
  public boolean isNonTypesystemComputation() {
    return myIsNonTypesystemComputation;
  }

  @Override
  protected void processDependency(SNode node, String ruleModel, String ruleId, boolean addDependency) {
    IncrementalTypechecking currentTypesComponent = getTypechecking();
    currentTypesComponent.typeOfNodeCalled(node);
    if (addDependency) {
      currentTypesComponent.addDependencyOnCurrent(node);
    }
    if (ruleModel != null && ruleId != null) {
      currentTypesComponent.markNodeAsAffectedByRule(node, ruleModel, ruleId);
      //todo wrap into "if (addDependency) {}" when sure that typeof works fine
    }
  }

  @Override
  protected void applyNonTypesystemRules() {
    getTypechecking().applyNonTypesystemRulesToRoot(this);
  }
}
