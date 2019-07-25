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
package jetbrains.mps.lang.editor.menus.substitute;

import jetbrains.mps.actions.runtime.impl.ActionsUtil;
import jetbrains.mps.editor.runtime.completion.CompletionItemInformation;
import jetbrains.mps.editor.runtime.completion.CompletionMenuItemCustomizationContext;
import jetbrains.mps.editor.runtime.menus.EditorMenuItemCompositeCustomizationContext;
import jetbrains.mps.nodeEditor.EditorComponent;
import jetbrains.mps.nodeEditor.EditorManager;
import jetbrains.mps.nodeEditor.cellMenu.AbstractNodeSubstituteInfo;
import jetbrains.mps.nodeEditor.cellMenu.CompletionItemCustomizationUtil;
import jetbrains.mps.nodeEditor.cells.CellFinderUtil;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.menus.EditorMenuTraceInfo;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemCustomizer;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemStyle;
import jetbrains.mps.openapi.editor.menus.substitute.SubstituteMenuContext;
import jetbrains.mps.openapi.editor.menus.substitute.SubstituteMenuItem;
import jetbrains.mps.smodel.action.NodeFactoryManager;
import jetbrains.mps.smodel.presentation.NodePresentationUtil;
import jetbrains.mps.smodel.runtime.IconResource;
import jetbrains.mps.smodel.runtime.IconResourceUtil;
import jetbrains.mps.typechecking.TypecheckingFacade;
import jetbrains.mps.typesystem.inference.TypeChecker;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.model.SNode;

/**
 * @author simon
 */
public class DefaultSubstituteMenuItem implements SubstituteMenuItem {

  private static final Logger LOG = LogManager.getLogger(DefaultSubstituteMenuItem.class);

  @NotNull
  private SAbstractConcept myConcept;

  @NotNull
  private final SNode myParentNode;

  @Nullable
  private final SNode myCurrentChild;

  @NotNull
  private EditorContext myEditorContext;
  private EditorMenuTraceInfo myTraceInfo;

  SubstituteMenuContext myContext;

  @Deprecated
  public DefaultSubstituteMenuItem(@NotNull SAbstractConcept concept, @NotNull SNode parentNode, @Nullable SNode currentChild,
                                   @NotNull EditorContext editorContext) {
    myConcept = concept;
    myParentNode = parentNode;
    myCurrentChild = currentChild;
    myEditorContext = editorContext;
  }

  public DefaultSubstituteMenuItem(@NotNull SAbstractConcept concept, @NotNull SubstituteMenuContext context) {
    myConcept = concept;
    myParentNode = context.getParentNode();
    myCurrentChild = context.getCurrentTargetNode();
    myEditorContext = context.getEditorContext();
    myTraceInfo = context.getEditorMenuTrace().getTraceInfo();
    myContext = context;
  }

  @Nullable
  @Override
  public SAbstractConcept getOutputConcept() {
    return myConcept;
  }

  @Nullable
  @Override
  public SNode getType(@NotNull String pattern) {
    SNode node = createNode(pattern);
    if (node == null) {
      return null;
    }
    if (node.getParent() != null) {
      LOG.warn("Node, created by " + this.getClass() + " action already has parent node.", new Throwable());
    }

    if (ActionsUtil.isInstanceOfIType(node)) {
      return node;
    }

    //the following is for smart-type completion

    AbstractNodeSubstituteInfo.getModelForTypechecking().addRootNode(node);
    try {
      return TypecheckingFacade.getFromContext().getTypeOf(node);
    } finally {
      AbstractNodeSubstituteInfo.getModelForTypechecking().removeRootNode(node);
    }
  }

  @Nullable
  @Override
  public String getMatchingText(@NotNull String pattern) {
    return NodePresentationUtil.matchingText(myConcept);
  }

  @Nullable
  @Override
  public String getDescriptionText(@NotNull String pattern) {
    return NodePresentationUtil.descriptionText(myConcept);
  }

  @Override
  public boolean canExecute(@NotNull String pattern) {
    return true;
  }

  @Override
  public boolean canExecuteStrictly(@NotNull String pattern) {
    return pattern.equals(getMatchingText(pattern));
  }

  @Nullable
  @Override
  public SNode createNode(@NotNull String pattern) {
    SNode currentChild = myCurrentChild;
    if (myCurrentChild != null) {
      final Object oldNodeForSubstitute = myCurrentChild.getUserObject(EditorManager.OLD_NODE_FOR_SUBSTITUTION);
      if (oldNodeForSubstitute != null) {
        currentChild = ((SNode) oldNodeForSubstitute);
      }
    }
    return NodeFactoryManager.createNode(myConcept, currentChild, myParentNode, myParentNode.getModel());
  }

  @Nullable
  @Override
  public IconResource getIcon(@NotNull String pattern) {
    return IconResourceUtil.getIconResourceForConcept(myConcept);
  }

  @Override
  public void select(@NotNull SNode createdNode, @NotNull String pattern) {
    EditorComponent editorComponent = ((EditorComponent) myEditorContext.getEditorComponent());
    EditorCell cell = editorComponent.findNodeCell(createdNode);
    if (cell != null) {
      EditorCell errorCell = CellFinderUtil.findFirstError(cell, true);
      if (errorCell != null) {
        editorComponent.changeSelectionWRTFocusPolicy(errorCell);
      } else {
        editorComponent.changeSelectionWRTFocusPolicy(cell);
      }
    }
  }

  @Nullable
  protected SNode getCurrentChild() {
    return myCurrentChild;
  }

  @NotNull
  protected SNode getParentNode() {
    return myParentNode;
  }

  @Override
  public EditorMenuTraceInfo getTraceInfo() {
    return myTraceInfo;
  }


  @NotNull
  protected CompletionItemInformation createInformation(String pattern) {
    return new CompletionItemInformation(null, myConcept, getMatchingText(pattern), getDescriptionText(pattern));
  }

  @Override
  public void customize(String pattern, EditorMenuItemStyle style) {
    if (myContext != null) {
      SubstituteMenuContextToEditorMenuItemCreatingCustomizationContext creatingContext =
          new SubstituteMenuContextToEditorMenuItemCreatingCustomizationContext(myContext, myConcept);
      SubstituteMenuContextToEditorMenuItemModifyingCustomizationContext
          modifyingContext = new SubstituteMenuContextToEditorMenuItemModifyingCustomizationContext(myContext);
      CompletionMenuItemCustomizationContext customizationContext = new CompletionMenuItemCustomizationContext(createInformation(pattern));
      EditorMenuItemCompositeCustomizationContext compositeContext = new EditorMenuItemCompositeCustomizationContext(creatingContext, modifyingContext, customizationContext);
      for (EditorMenuItemCustomizer customizer : myContext.getCustomizers()) {
        customizer.customize(style, compositeContext);
      }
    }
  }
}
