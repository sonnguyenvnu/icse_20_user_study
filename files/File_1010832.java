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

import jetbrains.mps.lang.script.runtime.AbstractMigrationRefactoring;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.intentions.IntentionDescriptor;
import jetbrains.mps.openapi.intentions.IntentionExecutable;
import jetbrains.mps.openapi.intentions.Kind;
import jetbrains.mps.util.NameUtil;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.Collection;
import java.util.Collections;

public class MigrationRefactoringAdapter extends OldBaseIntentionFactory {
  private final AbstractMigrationRefactoring myRefactoring;
  private final SNodeReference myIntentionNodeReference;
  private final String myPresentation;

  /*package*/ MigrationRefactoringAdapter(AbstractMigrationRefactoring refactoring, @Nullable SNodeReference migrationReference) {
    myRefactoring = refactoring;
    myIntentionNodeReference = migrationReference;
    myPresentation = refactoring.getName();
  }

  /*package*/ AbstractMigrationRefactoring getRefactoring() {
    return myRefactoring;
  }

  @Override
  public boolean isApplicable(SNode node, EditorContext editorContext) {
    return myRefactoring.isApplicableInstanceNode(node);
  }

  @Override
  public boolean isAvailableInChildNodes() {
    return false;
  }

  @Override
  public Kind getKind() {
    return Kind.MIGRATION;
  }

  @Override
  public String getPersistentStateKey() {
    return myRefactoring.getClass().getName();
  }

  @Override
  public SNodeReference getIntentionNodeReference() {
    return myIntentionNodeReference;
  }

  @Override
  public String getPresentation() {
    return myPresentation;
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
      return "Migration: " + NameUtil.multiWordCapitalize(myRefactoring.getName());
    }

    @Override
    public void execute(SNode node, EditorContext editorContext) {
      myRefactoring.doUpdateInstanceNode(node);
    }

    @Override
    public IntentionDescriptor getDescriptor() {
      return MigrationRefactoringAdapter.this;
    }
  }
}
