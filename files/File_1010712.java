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
package jetbrains.mps.lang.editor.menus.transformation;

import jetbrains.mps.editor.runtime.completion.CompletionItemInformation;
import jetbrains.mps.editor.runtime.completion.CompletionMenuItemCustomizationContext;
import jetbrains.mps.editor.runtime.menus.EditorMenuItemCompositeCustomizationContext;
import jetbrains.mps.editor.runtime.menus.EditorMenuItemModifyingCustomizationContext;
import jetbrains.mps.nodeEditor.cellMenu.AbstractNodeSubstituteInfo;
import jetbrains.mps.nodeEditor.cellMenu.BaseCompletionActionItem;
import jetbrains.mps.nodeEditor.cellMenu.CompletionItemCustomizationUtil;
import jetbrains.mps.nodeEditor.cells.EditorCell_Label;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.menus.EditorMenuTraceInfo;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemCustomizationContext;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemCustomizer;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemStyle;
import jetbrains.mps.openapi.editor.menus.transformation.ActionItemBase;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuContext;
import jetbrains.mps.smodel.CopyUtil;
import jetbrains.mps.smodel.presentation.NodePresentationUtil;
import jetbrains.mps.smodel.runtime.IconResource;
import jetbrains.mps.smodel.runtime.IconResourceUtil;
import jetbrains.mps.typechecking.TypecheckingFacade;
import jetbrains.mps.typesystem.inference.TypeChecker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeAccessUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class ReferenceTransformationMenuItem extends ActionItemBase implements BaseCompletionActionItem {
  @NotNull
  private final SNode myNode;

  @NotNull
  private final SReferenceLink myLink;
  private final SNode myTargetNode;

  @NotNull
  private final EditorContext myEditorContext;
  private final EditorMenuTraceInfo myTraceInfo;

  @Nullable
  private final TransformationMenuContext myContext;

  public ReferenceTransformationMenuItem(@NotNull SReferenceLink link, SNode targetNode, @NotNull TransformationMenuContext context) {
    myNode = context.getNode();
    myLink = link;
    myTargetNode = targetNode;
    myEditorContext = context.getEditorContext();
    myTraceInfo = context.getEditorMenuTrace().getTraceInfo();
    myContext = context;
  }

  public ReferenceTransformationMenuItem(@NotNull SNode node, @NotNull EditorContext editorContext, @NotNull SReferenceLink link, SNode targetNode,
                                         @Nullable EditorMenuTraceInfo traceInfo) {
    myNode = node;
    myLink = link;
    myTargetNode = targetNode;
    myEditorContext = editorContext;
    myTraceInfo = traceInfo;
    myContext = null;
  }

  @Nullable
  protected String getVisibleText(@NotNull String pattern) {
    return NodePresentationUtil.visibleMatchingText(myTargetNode, myNode);
  }

  @Nullable
  @Override
  public IconResource getIcon(@NotNull String pattern) {
    return IconResourceUtil.getIconResourceForNode(myTargetNode);
  }


  @Nullable
  @Override
  public String getLabelText(String pattern) {
    return NodePresentationUtil.matchingText(myTargetNode, myNode);
  }

  @Nullable
  @Override
  public EditorMenuTraceInfo getTraceInfo() {
    return myTraceInfo;
  }

  @Nullable
  @Override
  public String getShortDescriptionText(@NotNull String pattern) {
    return "^" + NodePresentationUtil.descriptionText(myTargetNode, myNode);
  }

  @Nullable
  @Override
  public SNode getActionType(@NotNull String pattern) {
    HashMap<SNode, SNode> mapping = new HashMap<>();
    CopyUtil.copy(Arrays.asList(myNode.getContainingRoot()), mapping);
    SNode sourceNodeCopy = mapping.get(myNode);
    SNode nodeToEquate = myNode;
    TypeChecker typeChecker = TypeChecker.getInstance();
    while (nodeToEquate != null && typeChecker.getTypeOf(nodeToEquate) == null) {
      nodeToEquate = nodeToEquate.getParent();
    }
    if (nodeToEquate == null) {
      return null;
    }
    SNode nodeToEquateCopy = mapping.get(nodeToEquate);
    if (nodeToEquateCopy.getParent() == null) {
      // why?..
      return null;
    }
    SNodeAccessUtil.setReferenceTarget(sourceNodeCopy, myLink, myTargetNode);
    AbstractNodeSubstituteInfo.getModelForTypechecking().addRootNode(nodeToEquateCopy);
    try {
      return TypecheckingFacade.getFromContext().getTypeOf(nodeToEquateCopy);
    } finally {
      AbstractNodeSubstituteInfo.getModelForTypechecking().removeRootNode(nodeToEquateCopy);
    }
  }

  @Override
  public void execute(@NotNull String pattern) {
    SNodeAccessUtil.setReferenceTarget(myNode, myLink, myTargetNode);
    myEditorContext.flushEvents();
    EditorCell selectedCell = myEditorContext.getSelectedCell();
    if (selectedCell instanceof EditorCell_Label && ((EditorCell_Label) selectedCell).isEditable()) {
      EditorCell_Label cell = (EditorCell_Label) selectedCell;
      cell.end();
    }
  }

  SNode getTargetNode() {
    return myTargetNode;
  }

  @Override
  public void customize(String pattern, EditorMenuItemStyle style) {
    if (myContext != null) {
      TransformationMenuContextToEditorMenuItemCustomizationContext
          context = new TransformationMenuContextToEditorMenuItemCustomizationContext(myContext, null, myLink);
      EditorMenuItemCompositeCustomizationContext compositeContext = getCompositeContext(pattern, context);
      for (EditorMenuItemCustomizer customizer : myContext.getCustomizers()) {
        customizer.customize(style, compositeContext);
      }
    } else {
      EditorMenuItemModifyingCustomizationContext
          context = new EditorMenuItemModifyingCustomizationContext(myNode, null, null, myLink);
      CompletionItemCustomizationUtil.customize(getCompositeContext(pattern, context), style, myEditorContext.getRepository());
    }
  }

  @NotNull
  private EditorMenuItemCompositeCustomizationContext getCompositeContext(String pattern,
                                                                          EditorMenuItemCustomizationContext context) {
    CompletionItemInformation completionItemInformation =
        new CompletionItemInformation(myTargetNode, null, getMatchingText(pattern), getShortDescriptionText(pattern));
    return new EditorMenuItemCompositeCustomizationContext(context, new CompletionMenuItemCustomizationContext(completionItemInformation));
  }
}
