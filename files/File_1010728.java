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
package jetbrains.mps.nodeEditor.cellMenu;

import jetbrains.mps.lang.editor.menus.transformation.CompletionActionItemUtil;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.SubstituteAction;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemStyle;
import jetbrains.mps.openapi.editor.menus.EditorMenuTraceInfo;
import jetbrains.mps.openapi.editor.menus.transformation.CommandPolicy;
import jetbrains.mps.openapi.editor.menus.transformation.CompletionActionItem;
import jetbrains.mps.smodel.runtime.IconResource;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.module.SRepository;

public class CompletionActionItemAsSubstituteAction implements SubstituteAction {
  private final CompletionActionItem myActionItem;
  private final SNode mySourceNode;
  private final SRepository myRepository;

  public CompletionActionItemAsSubstituteAction(CompletionActionItem actionItem, SNode sourceNode, SRepository repository) {
    myActionItem = actionItem;
    mySourceNode = sourceNode;
    myRepository = repository;
  }

  public IconResource getIcon(String pattern) {
    return myActionItem.getIcon(pattern);
  }

  @Override
  public SNode getIconNode(String pattern) {
    final Object parameterObject = getParameterObject();
    if (parameterObject instanceof SNode) {
      return ((SNode) parameterObject);
    }
    return getOutputConcept();
  }

  @Override
  public boolean isReferentPresentation() {
    return false;
  }

  @Override
  public SNode getSourceNode() {
    return mySourceNode;
  }

  @Override
  public Object getParameterObject() {
    return CompletionActionItemUtil.getReferentNode(myActionItem);
  }

  @Override
  public SNode getOutputConcept() {
    final SAbstractConcept outputConcept = CompletionActionItemUtil.getWrappedOutputConcept(myActionItem);
    final SNodeReference sourceNode = outputConcept == null ? null : outputConcept.getSourceNode();
    if (myRepository != null && sourceNode != null) {
      return sourceNode.resolve(myRepository);
    }
    return null;
  }

  @Override
  public SNode getActionType(String pattern) {
    return myActionItem.getActionType(pattern);
  }

  @Override
  public SNode getActionType(String pattern, EditorCell contextCell) {
    return getActionType(pattern);
  }

  @Override
  public String getMatchingText(String pattern) {
    return myActionItem.getMatchingText(pattern);
  }

  @Override
  public String getVisibleMatchingText(String pattern) {
    final String visibleMatchingText = CompletionActionItemUtil.getVisibleMatchingText(myActionItem, pattern);
    return visibleMatchingText != null ? visibleMatchingText : getMatchingText(pattern);
  }

  @Override
  public String getDescriptionText(String pattern) {
    return myActionItem.getShortDescriptionText(pattern);
  }

  @Override
  public boolean canSubstituteStrictly(String pattern) {
    return myActionItem.canExecuteStrictly(pattern);
  }

  @Override
  public boolean canSubstitute(String pattern) {
    return myActionItem.canExecute(pattern);
  }

  @Override
  public SNode substitute(@Nullable EditorContext context, String pattern) {
    assert myActionItem.getCommandPolicy() == CommandPolicy.COMMAND_REQUIRED : "Cannot execute a substitute action outside of command";
    if (context != null) {
      EditorCell contextCell = context.getContextCell();
      if (contextCell instanceof jetbrains.mps.nodeEditor.cells.EditorCell) {
        ((jetbrains.mps.nodeEditor.cells.EditorCell) contextCell).synchronizeViewWithModel();
      }
    }
    myActionItem.execute(pattern);
    // myActionItem should change selection itself, so return null here
    return null;
  }

  public CompletionActionItem getItem(){
    return myActionItem;
  }

  @Override
  public EditorMenuTraceInfo getEditorMenuTraceInfo() {
    return myActionItem.getTraceInfo();
  }

  @Override
  public void customize(String pattern, EditorMenuItemStyle style) {
    myActionItem.customize(pattern, style);
  }
}
