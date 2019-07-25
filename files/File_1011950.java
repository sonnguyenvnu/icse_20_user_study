/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.ide.editor;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import jetbrains.mps.fileTypes.MPSFileTypeFactory;
import jetbrains.mps.ide.vfs.IdeaFileSystem;
import jetbrains.mps.nodefs.MPSNodeVirtualFile;
import jetbrains.mps.nodefs.NodeVirtualFileSystem;
import jetbrains.mps.openapi.editor.EditorState;
import jetbrains.mps.project.MPSProject;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.SModelFileTracker;
import jetbrains.mps.util.Computable;
import jetbrains.mps.util.FileUtil;
import jetbrains.mps.vfs.IFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.module.SRepository;

public class MPSFileNodeEditorProvider implements FileEditorProvider, DumbAware {
  private static final Logger LOG = LogManager.getLogger(MPSFileNodeEditorProvider.class);

  private static final String CLASS = "class";

  @Override
  public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
    return file instanceof MPSNodeVirtualFile || file.getFileType() == MPSFileTypeFactory.MPS_ROOT_FILE_TYPE;
  }

  @Override
  @NotNull
  public FileEditor createEditor(@NotNull Project project, @NotNull final VirtualFile file) {
    final MPSProject mpsProject = project.getComponent(MPSProject.class);
    if (file instanceof MPSNodeVirtualFile) {
      return new MPSFileNodeEditor(mpsProject, (MPSNodeVirtualFile) file);
    }

    SRepository repository = mpsProject.getRepository();
    NodeFileComputable nodeFileComputable = new NodeFileComputable(repository, file, mpsProject.getFileSystem());
    MPSNodeVirtualFile mpsNodeVirtualFile = new ModelAccessHelper(repository).runReadAction(nodeFileComputable);
    return mpsNodeVirtualFile != null ? new MPSFileNodeEditor(mpsProject, mpsNodeVirtualFile) :
           new MPSFileNodeEditor(mpsProject, repository, nodeFileComputable);
  }

  @Override
  public void disposeEditor(@NotNull FileEditor editor) {
    Disposer.dispose(editor);
  }

  @Override
  @NotNull
  public FileEditorState readState(@NotNull Element sourceElement, @NotNull Project project, @NotNull VirtualFile file) {
    String className = sourceElement.getAttributeValue(CLASS);
    if (className == null) {
      return FileEditorState.INSTANCE;
    }

    try {
      Class cls = Class.forName(className);
      EditorState instance = (EditorState) cls.newInstance();
      instance.load(sourceElement);
      MPSEditorStateWrapper result = new MPSEditorStateWrapper();
      result.setEditorState(instance);
      return result;
    } catch (ClassNotFoundException e) {
      //do nothing - class is not there anymore
    } catch (Throwable t) {
      LOG.error(null, t);
    }

    return FileEditorState.INSTANCE;
  }

  @Override
  public void writeState(@NotNull FileEditorState state, @NotNull Project project, @NotNull Element targetElement) {
    if (!(state instanceof MPSEditorStateWrapper)) {
      return;
    }

    MPSEditorStateWrapper wrapper = (MPSEditorStateWrapper) state;
    EditorState editorState = wrapper.getEditorState();
    targetElement.setAttribute(CLASS, editorState.getClass().getName());
    editorState.save(targetElement);
  }

  @Override
  @NotNull
  @NonNls
  public String getEditorTypeId() {
    return "MPSFileEditor";
  }

  @Override
  @NotNull
  public FileEditorPolicy getPolicy() {
    return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
  }

  static class NodeFileComputable implements Computable<MPSNodeVirtualFile> {
    private final SRepository myRepository;
    private final VirtualFile myFile;
    private final String myNameToMatch;
    private IdeaFileSystem myFileSystem;

    NodeFileComputable(SRepository repository, VirtualFile file, IdeaFileSystem fileSystem) {
      myRepository = repository;
      myFile = file;
      myFileSystem = fileSystem;
      myNameToMatch = FileUtil.getNameWithoutExtension(file.getName());
    }

    @Override
    public MPSNodeVirtualFile compute() {
      if (!myFileSystem.canConvert(myFile)){
        return null;
      }
      SModel model = SModelFileTracker.getInstance(myRepository).findModel(myFileSystem.fromVirtualFile(myFile));
      if (model == null) {
        return null;
      }

      for (SNode node : model.getRootNodes()) {
        if (myNameToMatch.equals(node.getName()) || myNameToMatch.equals(node.getNodeId().toString())) {
          return NodeVirtualFileSystem.getInstance().getFileFor(myRepository, node);
        }
      }
      return null;
    }
  }
}
