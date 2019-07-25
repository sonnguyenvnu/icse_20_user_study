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
package jetbrains.mps.idea.core.editor;

import jetbrains.mps.ide.editor.NodeEditorFactoryBase;
import jetbrains.mps.openapi.editor.Editor;
import jetbrains.mps.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;

/**
 * danilla 5/17/13
 */
public class IdeaMPSEditorOpenHandler extends NodeEditorFactoryBase {
  private final Project myProject;

  public IdeaMPSEditorOpenHandler(Project project) {
    myProject = project;
  }

  @Override
  public boolean canCreate(@NotNull Context context) {
    return true;
  }

  @Override
  public Editor create(@NotNull Context context) {
    return new IdeaNodeEditor(myProject, context.getNode());
  }

  @Override
  public SNode getBaseNode(@NotNull SNode node) {
    // FIXME: what's this?
    return node;
  }
}
