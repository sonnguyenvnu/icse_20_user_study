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

import jetbrains.mps.editor.runtime.completion.CompletionItemInformation;
import jetbrains.mps.editor.runtime.completion.CompletionMenuItemCustomizationContext;
import jetbrains.mps.editor.runtime.menus.EditorMenuItemCompositeCustomizationContext;
import jetbrains.mps.editor.runtime.menus.EditorMenuItemCreatingCustomizationContext;
import jetbrains.mps.editor.runtime.menus.EditorMenuItemModifyingCustomizationContext;
import jetbrains.mps.nodeEditor.cellMenu.CompletionItemCustomizationUtil;
import jetbrains.mps.nodeEditor.cells.CellFinderUtil;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.SubstituteAction;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemCustomizationContext;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemStyle;
import jetbrains.mps.smodel.adapter.MetaAdapterByDeclaration;
import jetbrains.mps.smodel.presentation.NodePresentationUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.Optional;

public abstract class AbstractNodeSubstituteAction implements SubstituteAction {
  private static final Logger LOG = LogManager.getLogger(AbstractNodeSubstituteAction.class);
  private SNode mySourceNode;
  private Object myParameterObject;
  private SNode myOutputConcept;    // todo: this class is still too abstract to have 'output concept'

  protected AbstractNodeSubstituteAction(SNode outputConcept, Object parameterObject, SNode sourceNode) {
    myOutputConcept = outputConcept;
    myParameterObject = parameterObject;
    mySourceNode = sourceNode;
  }

  protected AbstractNodeSubstituteAction() {
  }

  protected SNode doSubstitute(@Nullable final EditorContext editorContext, String pattern) {
    throw new UnsupportedOperationException();
  }

  @Override
  public SNode getSourceNode() {
    return mySourceNode;
  }

  @Override
  public SNode getOutputConcept() {
    return myOutputConcept;
  }

  @Override
  public final Object getParameterObject() {
    return myParameterObject;
  }

  @Override
  public String getMatchingText(String pattern) {
    return getMatchingText(pattern, false, false);
  }

  @Override
  public String getVisibleMatchingText(String pattern) {
    return getMatchingText(pattern, false, true);
  }

  @Override
  public String getDescriptionText(String pattern) {
    if (myParameterObject instanceof SNode) {
      return NodePresentationUtil.descriptionText((SNode) myParameterObject);
    }
    if (myParameterObject instanceof SConcept) {
      return NodePresentationUtil.descriptionText((SConcept) myParameterObject);
    }
    return "";
  }

  @Override
  public SNode getIconNode(String pattern) {
    return myParameterObject instanceof SNode ? (SNode) myParameterObject : null;
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

  protected String getMatchingText(String pattern, boolean referent_presentation, boolean visible) {
    if (myParameterObject instanceof SNode) {
      return NodePresentationUtil.matchingText((SNode) myParameterObject, mySourceNode, visible);
    }
    if (myParameterObject instanceof SAbstractConcept) {
      return NodePresentationUtil.matchingText((SAbstractConcept) myParameterObject);
    }
    return "" + myParameterObject;
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
  public final SNode substitute(@Nullable final EditorContext context, final String pattern) {
    if (context != null) {
      // completion can be invoked by typing invalid stuff into existing cells, revert it back to the model state
      jetbrains.mps.nodeEditor.cells.EditorCell selectedCell = (jetbrains.mps.nodeEditor.cells.EditorCell) context.getSelectedCell();
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

    SNode nodeToSelect = null;
    try {
      nodeToSelect = doSubstitute(context, pattern);
    } catch (RuntimeException rte) {
      LOG.error("Exception on calling doSubstitute() method for " + AbstractNodeSubstituteAction.this.getClass(), rte);
    }
    // similar to: IntellijentInputUtil.applyRigthTransform() logic
    if (context != null && nodeToSelect != null) {
      jetbrains.mps.nodeEditor.EditorComponent editorComponent = ((jetbrains.mps.nodeEditor.EditorComponent) context.getEditorComponent());
      editorComponent.getUpdater().flushModelEvents();
      EditorCell cell = editorComponent.findNodeCell(nodeToSelect);
      if (cell != null) {
        EditorCell errorCell = CellFinderUtil.findFirstError(cell, true);
        if (errorCell != null) {
          editorComponent.changeSelectionWRTFocusPolicy(errorCell);
        } else {
          editorComponent.changeSelectionWRTFocusPolicy(cell);
        }
      }
    }
    return nodeToSelect;
  }

  @NotNull
  private CompletionItemInformation createCompletionItemInformation(String pattern, SAbstractConcept outputConcept) {
    return new CompletionItemInformation(getParameterObject(), outputConcept, getMatchingText(pattern), getDescriptionText(pattern));
  }

  @Nullable
  protected final SAbstractConcept getOutputSConcept() {
    return myOutputConcept != null ? MetaAdapterByDeclaration.getConcept(myOutputConcept) : null;
  }

  public void customize(String pattern, EditorMenuItemStyle style) {
    Optional<EditorMenuItemCompositeCustomizationContext> customizationContext = createCustomizationContext(pattern);
    if (customizationContext.isPresent() && mySourceNode.getModel() != null) {
      EditorMenuItemCustomizationContext finalContext = new EditorMenuItemCompositeCustomizationContext(customizationContext.get(), new CompletionMenuItemCustomizationContext(
          createCompletionItemInformation(pattern, getOutputSConcept())));
      CompletionItemCustomizationUtil.customize(finalContext, style, mySourceNode.getModel().getRepository());
    }
  }

  protected Optional<EditorMenuItemCompositeCustomizationContext> createCustomizationContext(String pattern) {
    SNode sourceNode = getSourceNode();
    SAbstractConcept outputSConcept = getOutputSConcept();
    if (sourceNode != null && outputSConcept != null) {
      return Optional.of(new EditorMenuItemCompositeCustomizationContext(new EditorMenuItemModifyingCustomizationContext(sourceNode, null, null, null),
                                                                         new EditorMenuItemCreatingCustomizationContext(sourceNode.getParent(), sourceNode,
                                                                                                                        null, outputSConcept)));
    }
    return Optional.empty();
  }
}
