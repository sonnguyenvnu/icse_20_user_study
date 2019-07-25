/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package jetbrains.mps.smodel.action;

import jetbrains.mps.nodeEditor.cells.CellFinderUtil;
import jetbrains.mps.openapi.editor.EditorComponent;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.SubstituteAction;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;

public abstract class AbstractSubstituteAction implements SubstituteAction {
  private static final Logger LOG = LogManager.getLogger(AbstractSubstituteAction.class);
  private SNode mySourceNode;

  protected AbstractSubstituteAction(SNode sourceNode) {
    mySourceNode = sourceNode;
  }

  protected SNode doSubstitute(@Nullable final EditorContext editorContext, String pattern) {
    throw new UnsupportedOperationException();
  }

  @Override
  public SNode getIconNode(String pattern) {
    return null;
  }

  @Override
  public Object getParameterObject() {
    return null;
  }

  @Override
  public SNode getOutputConcept() {
    return null;
  }

  @Override
  public SNode getSourceNode() {
    return mySourceNode;
  }

  @Override
  public String getVisibleMatchingText(String pattern) {
    return getMatchingText(pattern);
  }


  @Override
  public boolean isReferentPresentation() {
    return false;
  }

  @Override
  public SNode getActionType(String pattern) {
    return null;
  }

  @Override
  public SNode getActionType(String pattern, EditorCell contextCell) {
    return getActionType(pattern);
  }

  @Override
  public boolean canSubstituteStrictly(String pattern) {
    if (pattern == null || getMatchingText(pattern) == null) {
      return false;
    }
    return getMatchingText(pattern).equals(pattern);
  }

  /**
   * @param pattern . NULL if pattern is not available yet
   */
  @Override
  public boolean canSubstitute(String pattern) {
    if (pattern == null || pattern.length() == 0) {
      return true;
    }
    String matchingText = null;
    try {
      matchingText = getMatchingText(pattern);
    } catch (Exception e) {
      LOG.error(null, e);
    }
    return matchingText != null && matchingText.length() != 0;
  }

  @Override
  public final SNode substitute(@Nullable final EditorContext editorContext, final String pattern) {
    if (editorContext != null) {
      // completion can be invoked by typing invalid stuff into existing cells, revert it back to the model state
      jetbrains.mps.nodeEditor.cells.EditorCell selectedCell = (jetbrains.mps.nodeEditor.cells.EditorCell) editorContext.getSelectedCell();
      if (selectedCell != null) {
        // Trying to invoke synchronizeViewWithModel() for the cell which was modified by "typing invalid stuff into" only.
        //
        // This is necessary to not reset all states of all "error" cells with modified text within them.
        // Important for auto-re-resolving functionality (see http://youtrack.jetbrains.com/issue/MPS-19751).
        //
        // In case this will break something we can thing of more careful "synchronizeViewWithModel()" execution.
        // For example: run synchronizeViewWithModel() only for constant cells or only for cells representing this
        // node only (not it's children).
        selectedCell.synchronizeViewWithModel();
      }
    }

    SNode nodeToSelect = doSubstitute(editorContext, pattern);

    if (editorContext != null && nodeToSelect != null) {
      EditorComponent editorComponent = editorContext.getEditorComponent();
      editorComponent.getUpdater().flushModelEvents();
      select(editorContext, nodeToSelect);
    }
    return nodeToSelect;
  }

  protected void select(EditorContext context, SNode node) {
    EditorComponent editorComponent = context.getEditorComponent();
    EditorCell cell = editorComponent.findNodeCell(node);
    if (cell != null) {
      EditorCell errorCell = CellFinderUtil.findFirstError(cell, true);
      //todo move "select wrt focus policy" to selection api
      if (errorCell != null) {
        ((jetbrains.mps.nodeEditor.EditorComponent) editorComponent).changeSelectionWRTFocusPolicy(errorCell);
      } else {
        ((jetbrains.mps.nodeEditor.EditorComponent) editorComponent).changeSelectionWRTFocusPolicy(cell);
      }
    }
  }
}
