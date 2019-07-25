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
package jetbrains.mps.ide.ui.filechoosers.treefilechooser;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileSystemTree;
import com.intellij.openapi.fileChooser.actions.FileChooserAction;
import com.intellij.openapi.vfs.VirtualFile;
import jetbrains.mps.ide.vfs.VirtualFileUtils;
import jetbrains.mps.project.AbstractModule;
import org.jetbrains.mps.openapi.module.SModule;
import jetbrains.mps.workbench.MPSDataKeys;

public final class GotoModuleDirectory extends FileChooserAction {
  private static final Logger LOG = Logger.getInstance("#com.intellij.openapi.fileChooser.actions.GotoModuleDirectory");

  @Override
  protected void actionPerformed(final FileSystemTree fileSystemTree, AnActionEvent e) {
    final VirtualFile path = getModulePath(e);
    LOG.assertTrue(path != null);
    fileSystemTree.select(path, () -> fileSystemTree.expand(path, null));
  }

  @Override
  protected void update(FileSystemTree fileSystemTree, AnActionEvent e) {
    final Presentation presentation = e.getPresentation();
    final VirtualFile path = getModulePath(e);
    presentation.setEnabled(path != null && fileSystemTree.isUnderRoots(path));
  }

  private static VirtualFile getModulePath(AnActionEvent e) {
    SModule module = e.getData(MPSDataKeys.CONTEXT_MODULE);
    if (!(module instanceof AbstractModule)) return null;
    final VirtualFile moduleDir = VirtualFileUtils.getProjectVirtualFile(((AbstractModule) module).getModuleSourceDir());
    return (moduleDir != null) ? validated(moduleDir.getParent()) : null;
  }

  private static VirtualFile validated(final VirtualFile file) {
    if (file == null || !file.isValid()) {
      return null;
    }
    return file;
  }

}
