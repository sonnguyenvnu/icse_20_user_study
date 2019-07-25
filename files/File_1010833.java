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
package jetbrains.mps.intentions;

import jetbrains.mps.errors.MessageStatus;
import jetbrains.mps.errors.item.EditorQuickFix;
import jetbrains.mps.errors.item.RuleIdFlavouredItem.TypesystemRuleId;
import jetbrains.mps.nodeEditor.checking.QuickFixRuntimeEditorWrapper;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.intentions.IntentionDescriptor;
import jetbrains.mps.openapi.intentions.IntentionExecutable;
import jetbrains.mps.openapi.intentions.Kind;
import jetbrains.mps.util.SNodeOperations;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.Collection;
import java.util.Collections;

import static jetbrains.mps.errors.item.RuleIdFlavouredItem.FLAVOUR_RULE_ID;

public class QuickFixAdapter extends OldBaseIntentionFactory {
  private EditorQuickFix myQuickFix;
  private final MessageStatus myStatus;

  public QuickFixAdapter(@NotNull EditorQuickFix quickFix, @NotNull MessageStatus status) {
    myQuickFix = quickFix;
    myStatus = status;
  }

  @Override
  public String getPersistentStateKey() {
    return myQuickFix.toPredicate(myQuickFix.getIdFlavours()).serialize();
  }

  @Override
  public boolean isApplicable(SNode node, EditorContext editorContext) {
    /*Quick fixes are added "manually" by typesystem rules.
    * Having a quick fix in messages already means that is is applicable.
    * So, return true.*/
    return true;
  }

  @Override
  public boolean isAvailableInChildNodes() {
    return true;
  }

  @Override
  public Kind getKind() {
    switch (myStatus) {
      case OK: return Kind.QUICKFIX;
      case WARNING: return Kind.QUICKFIX;
      case ERROR: return Kind.ERROR;
    }
    return Kind.NORMAL;
  }

  @Override
  public SNodeReference getIntentionNodeReference() {
    Collection<TypesystemRuleId> typesystemRuleIds = FLAVOUR_RULE_ID.getCollection(myQuickFix);
    if (typesystemRuleIds.size() == 1) {
      return typesystemRuleIds.iterator().next().getSourceNode();
    }
    return null;
  }

  @Override
  public String getPresentation() {
    return myQuickFix.getClass().getName();
  }

  @Override
  public boolean isSurroundWith() {
    return false;
  }

  @Override
  public Collection<IntentionExecutable> instances(SNode node, EditorContext editorContext) {
    return Collections.singleton(new Executable());
  }

  private class Executable implements IntentionExecutable {
    @Override
    public String getDescription(SNode node, EditorContext editorContext) {
      return myQuickFix.getDescription(editorContext.getRepository());
    }

    @Override
    public void execute(SNode node, EditorContext editorContext) {
      EditorCell selectedCell = editorContext.getSelectedCell();
      boolean restoreCaretPosition = selectedCell != null && SNodeOperations.isAncestor(node, selectedCell.getSNode());
      QuickFixRuntimeEditorWrapper.getInstance(myQuickFix).execute(editorContext, restoreCaretPosition);
    }

    @Override
    public IntentionDescriptor getDescriptor() {
      return QuickFixAdapter.this;
    }
  }
}
