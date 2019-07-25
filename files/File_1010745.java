/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.nodeEditor.cells;

import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.SubstituteInfo;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.NodeReadAccessCasterInEditor;
import jetbrains.mps.smodel.NodeReadAccessInEditorListener;
import jetbrains.mps.util.Computable;
import jetbrains.mps.util.Pair;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.model.SNodeUtil;
import org.jetbrains.mps.openapi.module.ModelAccess;

/**
 * Author: Sergey Dmitriev
 * Created Sep 14, 2003
 */
public class EditorCell_Property extends EditorCell_Label implements SynchronizeableEditorCell {
  private final ModelAccessor myModelAccessor;
  private boolean myCommitInProgress;
  private boolean myCommitInCommand = true;
  private String myLastModelText;

  public EditorCell_Property(EditorContext editorContext, ModelAccessor accessor, SNode node) {
    super(editorContext, node, accessor.getText());
    myModelAccessor = accessor;
    if (myModelAccessor instanceof TransactionalPropertyAccessor) {
      TransactionalPropertyAccessor propertyAccessor = (TransactionalPropertyAccessor) myModelAccessor;
      propertyAccessor.setCell(this);
    }
    synchronizeViewWithModel_internal();
  }

  public static EditorCell_Property create(jetbrains.mps.openapi.editor.EditorContext editorContext, ModelAccessor modelAccessor, SNode node) {
    NodeReadAccessInEditorListener listener = NodeReadAccessCasterInEditor.getReadAccessListener();
    // todo are there situations when the listener's cleanlyReadAccessProperties are not empty? If yes, should not we always clean it
    if (modelAccessor instanceof IPropertyAccessor) {
      if (listener != null) {
        listener.clearCleanlyReadAccessProperties();
      }
    }
    EditorCell_Property result = new EditorCell_Property(editorContext, modelAccessor, node);
    if (listener != null) {
      // TODO: specify property name directly - we know it from PropertyAccessor
      addPropertyDependenciesToEditor(listener, result);
    }
    return result;
  }

  private static void addPropertyDependenciesToEditor(NodeReadAccessInEditorListener listener, EditorCell_Property result) {
    for (Pair<SNodeReference, String> pair : listener.popCleanlyReadAccessedProperties()) {
      result.getEditorComponent().getUpdater().getCurrentUpdateSession().registerCleanDependency(result, pair);
    }
  }

  @Override
  public void synchronizeViewWithModel() {
    synchronizeViewWithModel_internal();
  }

  private void synchronizeViewWithModel_internal() {
    String text = myModelAccessor.getText();
    myLastModelText = text;
    setErrorState(!isValidText(text));
    setText(text);
  }

  @Override
  public void setSelected(boolean selected) {
    boolean oldSelected = isSelected();
    super.setSelected(selected);
    if (oldSelected && !selected && isTransactional()) {
      final Runnable commitCommand = () -> commit();
      if (myCommitInCommand) {
        getModelAccess().executeCommandInEDT(commitCommand);
      } else {
        getModelAccess().runWriteInEDT(commitCommand);
      }
    }
  }

  public boolean hasUncommittedValue() {
    if (!isTransactional()) {
      return false;
    }

    TransactionalModelAccessor transactionalModelAccessor = (TransactionalModelAccessor) myModelAccessor;
    return transactionalModelAccessor.hasValueToCommit();
  }

  /**
   * should be executed inside write action
   *
   * @return true if new value was committed to model / false if nothing was changed
   */
  public boolean commit() {
    getModelAccess().checkWriteAccess();
    // a solution for MPS-13531
    // better solution is to redispatch all currently waiting EDT commands inside MPSProject.dispose() method
    // currently not available - not possible to redispatch all waiting commands from AWT Thread.
    if (!SNodeUtil.isAccessible(getSNode(), getContext().getRepository())) {
      return false;
    }
    if (myCommitInProgress) {
      return false;
    }
    myCommitInProgress = true;
    try {
      if (isTransactional()) {
        TransactionalModelAccessor transactionalModelAccessor = (TransactionalModelAccessor) myModelAccessor;
        boolean result = false;
        if (transactionalModelAccessor.hasValueToCommit()) {
          transactionalModelAccessor.commit();
          result = true;
        }
        getEditor().relayout();
        return result;
      }
    } finally {
      myCommitInProgress = false;
    }
    return false;
  }

  @Override
  public void changeText(String text) {
    super.changeText(text);

    if (isValidText(text) && isEditable()) {
      myModelAccessor.setText(text);
      synchronizeViewWithModel();
      return;
    }

    if (isTransactional()) {
      ((TransactionalModelAccessor) myModelAccessor).resetUncommittedValue();
    }
    setErrorState(!isValidText(text));
  }

  public String getLastModelText() {
    return myLastModelText;
  }

  @Override
  public boolean isValidText(final String text) {
    return new ModelAccessHelper(getModelAccess()).runReadAction(() -> myModelAccessor.isValidText(text));
  }

  @Override
  public SubstituteInfo getSubstituteInfo() {
    final SubstituteInfo substituteInfo = super.getSubstituteInfo();
    return new ModelAccessHelper(getModelAccess()).runReadAction(() -> {
      if (substituteInfo != null) {
        substituteInfo.setOriginalText(myModelAccessor.getText());
      }
      return substituteInfo;
    });
  }

  public ModelAccessor getModelAccessor() {
    return myModelAccessor;
  }

  public void setCommitInCommand(boolean commit) {
    myCommitInCommand = commit;
  }

  @Override
  public void synchronize() {
    synchronizeViewWithModel();
  }

  @Override
  public boolean canBeSynchronized() {
    return false;
  }

  private ModelAccess getModelAccess() {
    return getContext().getRepository().getModelAccess();
  }

  private boolean isTransactional() {
    return myModelAccessor instanceof TransactionalModelAccessor;
  }

  @Override
  public void onAdd() {
    super.onAdd();
    if (isTransactional()) {
      getEditor().getCellTracker().addTransactionalCell(this);
    }
  }

  @Override
  public void onRemove() {
    if (isTransactional()) {
      getEditor().getCellTracker().removeTransactionalCell(this);
    }
    super.onRemove();
  }
}
