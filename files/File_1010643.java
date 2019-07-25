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
package jetbrains.mps.newTypesystem.operation;

import jetbrains.mps.newTypesystem.state.State;
import jetbrains.mps.smodel.SNodePointer;
import jetbrains.mps.typesystem.inference.EquationInfo;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractOperation {
  private List<AbstractOperation> myConsequences;
  protected SNode mySource = null;
  protected SNodeReference myRule = null;

  public void addConsequence(AbstractOperation op) {
    if (myConsequences == null) {
      myConsequences = new LinkedList<>();
    }
    myConsequences.add(op);
  }

  protected final void setRule(EquationInfo info) {
    if (info != null) {
      myRule = info.getRuleNode();
    }
  }

  protected final void setRule(String model, String id) {
    myRule = model == null || id == null ? null : new SNodePointer(model, id);
  }

  protected final void setRule(SNodeReference ruleNode) {
    myRule = ruleNode;
  }

  public List<AbstractOperation> getConsequences() {
    return myConsequences == null ? Collections.emptyList() : Collections.unmodifiableList(myConsequences);
  }

  protected abstract void doUndo(State state);

  protected abstract void doRedo(State state);

  public void undo(final State state) {
    state.executeStateChangeAction(() -> doUndo(state));
  }

  public void redo(final State state) {
    state.executeStateChangeAction(() -> doRedo(state));
  }

  // default implementation
  public void execute(State state) {
    redo(state);
  }

  public abstract String getPresentation();

  public String getShortPresentation() {
    return "";
  }

  public String getPresentationKind() {
    return PresentationKind.DEFAULT;
  }

  public SNode getSource() {
    return mySource;
  }

  @Nullable
  public SNodeReference getRule() {
    return myRule;
  }

  public List<SNode> getVariables() {
    return null;
  }

  public boolean hasEffect() {
    return true;
  }
}
