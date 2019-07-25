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
package jetbrains.mps.ide.editor.icons;

import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileAdapter;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.VirtualFilePropertyEvent;
import jetbrains.mps.nodefs.MPSNodeVirtualFile;
import jetbrains.mps.nodefs.NodeVirtualFileSystem;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * FIXME: MPSNodesVirtualFileSystem listens to node deletion and rename, why doesn't it send out file changed events as well, why do we
 *        need this distinct component? Does IDEA listen to file changes or it's indeed our responsibility to update editors on VF change?
 * XXX Why it's distinct from NodeFileIconProvider?
 */
public class NodeIconUpdater extends AbstractProjectComponent {
  private final FileEditorManagerEx myFileEditorManagerEx;
  private final NodeVirtualFileSystem myNodeVFS = (NodeVirtualFileSystem) VirtualFileManager.getInstance().getFileSystem(NodeVirtualFileSystem.PROTOCOL);
  private final VirtualFileListener myFileListener;

  public NodeIconUpdater(Project project, FileEditorManagerEx fileEditorManager) {
    super(project);
    myFileEditorManagerEx = fileEditorManager;
    // TODO Would be more effective to be an ApplicationComponent and listen to bulk changes (BulkFileListener)
    // however, there's no way to find out MPSProject from MPSNodeVirtualFile at the moment, and without a project
    // can't access FileEditorManagerEx.
    myFileListener = new VirtualFileAdapter() {
      @Override
      public void propertyChanged(@NotNull VirtualFilePropertyEvent event) {
        refresh(event.getFile());
      }

      @Override
      public void contentsChanged(@NotNull VirtualFileEvent event) {
        refresh(event.getFile());
      }

      @Override
      public void fileDeleted(@NotNull VirtualFileEvent event) {
        refresh(event.getFile());
      }
    };
  }

  @Override
  public void projectOpened() {
    myNodeVFS.addVirtualFileListener(myFileListener);
  }

  @Override
  public void projectClosed() {
    myNodeVFS.removeVirtualFileListener(myFileListener);
  }

  void refresh(VirtualFile vf) {
    if (false == vf instanceof MPSNodeVirtualFile) {
      return;
    }
    if (Arrays.asList(myFileEditorManagerEx.getOpenFiles()).contains(vf)) {
      myFileEditorManagerEx.updateFilePresentation(vf);
    }
  }
}
