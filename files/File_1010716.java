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
package jetbrains.mps.lang.editor.menus.transformation;

import jetbrains.mps.openapi.editor.menus.EditorMenuTraceInfo;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemStyle;
import jetbrains.mps.openapi.editor.menus.substitute.SubstituteMenuItem;
import jetbrains.mps.openapi.editor.menus.transformation.ActionItemBase;
import jetbrains.mps.openapi.editor.menus.transformation.CompletionActionItem;
import jetbrains.mps.smodel.runtime.IconResource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;

/**
 * @author simon
 */
public abstract class SubstituteMenuItemAsActionItem extends ActionItemBase implements CompletionActionItem {
  private final SubstituteMenuItem mySubstituteItem;

  public SubstituteMenuItemAsActionItem(SubstituteMenuItem substituteItem) {
    mySubstituteItem = substituteItem;
  }

  @Nullable
  @Override
  public String getLabelText(@NotNull String pattern) {
    return mySubstituteItem.getMatchingText(pattern);
  }

  @Nullable
  @Override
  public SNode getActionType(@NotNull String pattern) {
    return mySubstituteItem.getType(pattern);
  }

  @Nullable
  @Override
  public IconResource getIcon(String pattern) {
    return mySubstituteItem.getIcon(pattern);
  }

  @Nullable
  @Override
  public String getShortDescriptionText(@NotNull String pattern) {
    return mySubstituteItem.getDescriptionText(pattern);
  }

  @Override
  public boolean canExecute(@NotNull String pattern) {
    return mySubstituteItem.canExecute(pattern);
  }

  @Override
  public boolean canExecuteStrictly(@NotNull String pattern) {
    return mySubstituteItem.canExecuteStrictly(pattern);
  }

  protected SubstituteMenuItem getSubstituteItem() {
    return mySubstituteItem;
  }

  @Override
  public EditorMenuTraceInfo getTraceInfo() {
    return mySubstituteItem.getTraceInfo();
  }

  @Override
  public void customize(String pattern, EditorMenuItemStyle style) {
    mySubstituteItem.customize(pattern, style);
  }
}
