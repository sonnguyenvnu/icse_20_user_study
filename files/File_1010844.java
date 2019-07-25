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

package jetbrains.mps.idea.core.facet.ui;

import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import jetbrains.mps.extapi.persistence.SourceRoot;
import jetbrains.mps.extapi.persistence.SourceRootKinds;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.ide.ui.dialogs.properties.roots.editors.ModelRootContentEntriesEditor;
import jetbrains.mps.ide.ui.dialogs.properties.roots.editors.ModelRootEntryContainer;
import jetbrains.mps.ide.vfs.VirtualFileUtils;
import jetbrains.mps.idea.core.facet.MPSConfigurationBean;
import jetbrains.mps.idea.core.ui.SModuleConfigurationTab;
import jetbrains.mps.persistence.DefaultModelRoot;
import org.jetbrains.mps.openapi.persistence.ModelRoot;
import org.jetbrains.mps.openapi.ui.persistence.ModelRootEntry.ModelRootEntryListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MPSFacetSourcesTab implements SModuleConfigurationTab {
  private JPanel myRootPanel;

  private FacetEditorContext myContext;
  private Disposable myParentDisposable;

  private ModelRootContentEntriesEditor myContentEntriesEditor;

  public MPSFacetSourcesTab(FacetEditorContext context, Disposable parentDisposable) {
    myContext = context;
    myParentDisposable = parentDisposable;
    myRootPanel = new JPanel(new BorderLayout());
    myRootPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
  }

  public JPanel getRootPanel() {
    return myRootPanel;
  }

  @Override
  public void onTabEntering() {
  }

  public void reset(MPSConfigurationBean data) {
    if(myContentEntriesEditor != null) {
      Disposer.dispose(myContentEntriesEditor);
      myContentEntriesEditor = null;
    }
    // here come the only justified use of cfgBean.getSolutionDescriptor(), there's no other way to access Facet's SD here at the moment
    myContentEntriesEditor = new ModelRootContentEntriesEditor(data.getSolutionDescriptor(), ProjectHelper.fromIdeaProject(myContext.getProject()));
    Disposer.register(myParentDisposable, myContentEntriesEditor);
    VirtualFile defaultFolder = myContext.getModule().getModuleFile() != null
      ? myContext.getModule().getModuleFile().getParent()
      : myContext.getProject().getBaseDir();
    myContentEntriesEditor.setDefaultFolder(VirtualFileUtils.toIFile(defaultFolder));
    myRootPanel.removeAll();
    myRootPanel.add(myContentEntriesEditor.getComponent(), BorderLayout.CENTER);

    //Watch for changes in model root source folders
    for(ModelRootEntryContainer container : myContentEntriesEditor.getModelRootsEntries()) {
      container.getModelRootEntry().addModelRootEntryListener(new ModelRootEntryListener() {
        @Override
        public void fireDataChanged() {
          final ModifiableRootModel modifiableRootModel = myContext.getModifiableRootModel();

          // NOTE: if an MPS source root is not under any of idea _content_ roots, it will not be
          // added to idea's source roots. It would require creating a new content root, rather
          // than just adding source root into existing content root. Seems like too much intrusion.

          // Fixme we don't remove source roots that we have added to idea module

          for (ModelRoot path : myContentEntriesEditor.getModelRoots()) {
            // FIXME shall deal with ModelRootDescriptor here
            if(path instanceof DefaultModelRoot) {
              for(SourceRoot mpsSourceRoot : ((DefaultModelRoot) path).getSourceRoots(SourceRootKinds.SOURCES)) {
                VirtualFile mpsSourceRootVFile = VirtualFileUtils.getProjectVirtualFile(mpsSourceRoot.getAbsolutePath());
                if (mpsSourceRootVFile == null) {
                  // not in project; strange but why not
                  continue;
                }
                for(ContentEntry contentEntry : modifiableRootModel.getContentEntries()) {
                  VirtualFile contentRoot = contentEntry.getFile();
                  if (contentRoot == null) {
                    // invalid content root
                    continue;
                  }
                  if (!VfsUtilCore.isUnder(mpsSourceRootVFile, Collections.singleton(contentRoot))) {
                    // a completely different content root
                    continue;
                  }
                  Set<VirtualFile> ideaSourceFolders = new HashSet<>(Arrays.asList(contentEntry.getSourceFolderFiles()));
                  if (VfsUtilCore.isUnder(mpsSourceRootVFile, ideaSourceFolders)) {
                    // we're covered, no need to worry
                    break;
                  }
                  //Just add new source/test folder - do not watch after delete
                  contentEntry.addSourceFolder(mpsSourceRootVFile, false);
                }
              }
            }
          }
        }
      });
    }
  }

  public void apply(MPSConfigurationBean data) {
    myContentEntriesEditor.apply();
  }

  public boolean isModified(MPSConfigurationBean data) {
    return myContentEntriesEditor.isModified();
  }
}
