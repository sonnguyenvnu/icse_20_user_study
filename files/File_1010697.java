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
package jetbrains.mps.smodel.action;

import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.SubstituteAction;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemStyle;
import jetbrains.mps.openapi.editor.menus.EditorMenuTraceInfo;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;

/**
 * Igor Alshannikov
 * Jan 30, 2006
 */
public class NodeSubstituteActionWrapper implements SubstituteAction {
  private SubstituteAction mySubstituteAction;

  public NodeSubstituteActionWrapper(SubstituteAction substituteAction) {
    mySubstituteAction = substituteAction;
  }

  @Override
  public SNode getSourceNode() {
    return mySubstituteAction.getSourceNode();
  }

  @Override
  public SNode getOutputConcept() {
    return mySubstituteAction.getOutputConcept();
  }

  @Override
  public SNode getActionType(String pattern) {
    return mySubstituteAction.getActionType(pattern);
  }

  @Override
  public SNode getIconNode(String pattern) {
    return mySubstituteAction.getIconNode(pattern);
  }

  @Override
  public boolean isReferentPresentation() {
    return mySubstituteAction.isReferentPresentation();
  }

  @Override
  public String getMatchingText(String pattern) {
    return mySubstituteAction.getMatchingText(pattern);
  }

  @Override
  public String getVisibleMatchingText(String pattern) {
    return mySubstituteAction.getVisibleMatchingText(pattern);
  }

  @Override
  public String getDescriptionText(String pattern) {
    return mySubstituteAction.getDescriptionText(pattern);
  }

  @Override
  public boolean canSubstituteStrictly(String pattern) {
    return mySubstituteAction.canSubstituteStrictly(pattern);
  }

  @Override
  public boolean canSubstitute(String pattern) {
    return mySubstituteAction.canSubstitute(pattern);
  }

  @Override
  public SNode substitute(@Nullable EditorContext context, String pattern) {
    return mySubstituteAction.substitute(context, pattern);
  }

  @Override
  public SNode getActionType(String pattern, EditorCell contextCell) {
    return mySubstituteAction.getActionType(pattern, contextCell);
  }

  @Override
  public Object getParameterObject() {
    return mySubstituteAction.getParameterObject();
  }

  @Override
  public EditorMenuTraceInfo getEditorMenuTraceInfo() {
    return mySubstituteAction.getEditorMenuTraceInfo();
  }

  @Override
  public void customize(String pattern, EditorMenuItemStyle style) {
    mySubstituteAction.customize(pattern, style);
  }
}
