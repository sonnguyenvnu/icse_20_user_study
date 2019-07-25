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
package jetbrains.mps.ide.ui.dialogs.properties.roots.editors;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.roots.ui.componentsList.components.ScrollablePanel;
import com.intellij.openapi.roots.ui.componentsList.layout.VerticalStackLayout;
import com.intellij.openapi.roots.ui.configuration.actions.IconWithTextAction;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.Splitter;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.awt.RelativePoint;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.roots.ToolbarPanel;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import jetbrains.mps.extapi.persistence.FileBasedModelRoot;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.ide.ui.dialogs.properties.PropertiesBundle;
import jetbrains.mps.ide.ui.dialogs.properties.roots.editors.ModelRootEntryContainer.ContentEntryEditorListener;
import jetbrains.mps.ide.vfs.VirtualFileUtils;
import jetbrains.mps.persistence.MementoImpl;
import jetbrains.mps.persistence.PersistenceRegistry;
import jetbrains.mps.project.AbstractModule;
import jetbrains.mps.project.FileBasedProject;
import jetbrains.mps.project.Project;
import jetbrains.mps.project.structure.model.ModelRootDescriptor;
import jetbrains.mps.project.structure.modules.ModuleDescriptor;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.util.PathManager;
import jetbrains.mps.vfs.FileSystemExtPoint;
import jetbrains.mps.vfs.IFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.persistence.Memento;
import org.jetbrains.mps.openapi.persistence.ModelRoot;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;
import org.jetbrains.mps.openapi.ui.persistence.ModelRootEntry;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.intellij.openapi.vfs.VfsUtilCore.isAncestor;

/**
 * UIComponent which contains all the module roots.
 * It is located in the module properties dialog.
 */
public class ModelRootContentEntriesEditor implements Disposable {

  private final static Logger LOG = LogManager.getLogger(ModelRootContentEntriesEditor.class);

  private final ModuleDescriptor myModuleDescriptor;
  private Project myProject;
  private final ModelRootEntryPersistence myRootEntryPersistence;
  private final List<ModelRootEntryContainer> myModelRootEntries = new ArrayList<>();
  private ModelRootEntryContainer myFocusedModelRootEntryContainer;
  private final MyContentEntryEditorListener myEditorListener = new MyContentEntryEditorListener();

  private JPanel myEditorsListPanel;
  private JBPanel myEditorPanel;
  private JBPanel myMainPanel;
  private IFile myDefaultFolder;

  public ModelRootContentEntriesEditor(ModuleDescriptor moduleDescriptor, Project p) {
    myModuleDescriptor = moduleDescriptor;
    myProject = p;
    // XXX I'm puzzled with mix of ModelRoot and ModelRootDescriptor in ModelRootEntryPersistence, shall stick to one
    //     i.e. basically have to decide whether we edit SModule or ModuleDescriptor.
    myRootEntryPersistence = new ModelRootEntryPersistence(PersistenceFacade.getInstance());
    for (ModelRootDescriptor descriptor : myModuleDescriptor.getModelRootDescriptors()) {
      ModelRootEntry entry = myRootEntryPersistence.getModelRootEntry(descriptor);
      if (entry != null) {
        Disposer.register(this, entry);
        ModelRootEntryContainer container = new ModelRootEntryContainer(entry);
        container.addContentEntryEditorListener(myEditorListener);
        myModelRootEntries.add(container);
      } else {
        LOG.warn(
            String.format("Can't create editor for '%s' model root type in module %s. Check that plugin, where this model root type is registered, is enabled.",
                          descriptor.getType(), moduleDescriptor.getModuleReference().getModuleName()));
      }
    }
    initUI();
  }

  private AnAction getContentEntryActions() {
    final List<AddContentEntryAction> list = new ArrayList<>();
    // make sure that if title for an entry editor has not been specified, we don't treat underscores of a root type as mnemonics
    myRootEntryPersistence.foreachTypeAndName((type, name) -> list.add(new AddContentEntryAction(type, type.equals(name) ? name.replace('_', ' ') : name)));
    // may need to introduce weight into extpoint if by name sorting is not good enough
    list.sort(Comparator.comparing(a -> a.getTemplatePresentation().getText()));

    return new IconWithTextAction(
        PropertiesBundle.message("module.common.roots.add.title"),
        PropertiesBundle.message("module.common.roots.add.tip"),
        AllIcons.General.Add) {
      @Override
      public void actionPerformed(@NotNull final AnActionEvent e) {
        if (list.size() == 1) {
          list.get(0).actionPerformed(e);
          return;
        }
        final JBPopup popup = JBPopupFactory.getInstance().createListPopup(
            new BaseListPopupStep<AddContentEntryAction>(null, list) {
              @Override
              public Icon getIconFor(AddContentEntryAction aValue) {
                return aValue.getTemplatePresentation().getIcon();
              }

              @Override
              public boolean hasSubstep(AddContentEntryAction selectedValue) {
                return false;
              }

              @Override
              public boolean isMnemonicsNavigationEnabled() {
                // just in case title of a root entry editor has mnemonics specified
                return true;
              }

              @Override
              public PopupStep onChosen(final AddContentEntryAction selectedValue, final boolean finalChoice) {
                return doFinalStep(() -> selectedValue.actionPerformed(e));
              }

              @Override
              public int getMnemonicPos(AddContentEntryAction value) {
                // as long as isMnemonicsNavigationEnabled() == true, expect there could be a mnemonic in editor's title.
                // Presentation uses '_' to indicate mnemonics, while super.getMnemonicPos expects '&' or 0x1b (I adore your approach to mnemonics, IDEA guys!)
                // Therefore, here we delegate to presentation to tell position of a mnemonic identifier character (would get stripped off later in
                // PopupListElementRenderer, I believe). Note, for unknown reason mnemonic letter is not highlighted although works!
                return value.getTemplatePresentation().getDisplayedMnemonicIndex();
              }

              @Override
              @NotNull
              public String getTextFor(AddContentEntryAction value) {
                return value.getTemplatePresentation().getTextWithMnemonic();
              }
            });
        popup.show(new RelativePoint(myEditorsListPanel, new Point(0, 0)));
      }
    };
  }

  public final void initUI() {
    myMainPanel = new JBPanel(new BorderLayout());
    myMainPanel.setPreferredSize(new Dimension(300, 300));

    final JBPanel entriesPanel = new JBPanel(new BorderLayout());

    final DefaultActionGroup group = new DefaultActionGroup();
    group.add(getContentEntryActions());

    myEditorsListPanel = new ScrollablePanel(new VerticalStackLayout());
    myEditorsListPanel.setBackground(UIUtil.getListBackground());
    JScrollPane scrollPane = ScrollPaneFactory.createScrollPane(myEditorsListPanel);
    scrollPane.setPreferredSize(new Dimension(250, 300));
    entriesPanel.add(new ToolbarPanel(scrollPane, group), BorderLayout.CENTER);

    Splitter splitter = new Splitter(false);
    splitter.setHonorComponentsMinimumSize(true);
    myMainPanel.add(splitter, BorderLayout.CENTER);

    final JBPanel editorsPanel = new JBPanel(new GridBagLayout());
    splitter.setFirstComponent(editorsPanel);
    editorsPanel.add(entriesPanel,
                     new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, JBUI.emptyInsets(), 0, 0));

    final JBPanel editorPanel = new JBPanel(new BorderLayout());
    editorPanel.setBorder(BorderFactory.createEtchedBorder());
    myEditorPanel = new JBPanel(new BorderLayout());
    editorPanel.add(myEditorPanel, BorderLayout.CENTER);
    splitter.setSecondComponent(editorPanel);

    for (ModelRootEntryContainer entry : myModelRootEntries) {
      myEditorsListPanel.add(entry.getComponent());
    }

    selectEntry(myModelRootEntries.size() > 0 ? myModelRootEntries.get(0) : null);
  }

  private void selectEntry(ModelRootEntryContainer entry) {
    try {
      if (entry != null && entry.equals(myFocusedModelRootEntryContainer)) {
        return;
      }

      if (myFocusedModelRootEntryContainer != null) {
        myFocusedModelRootEntryContainer.setFocused(false);
      }

      if (entry == null) {
        myFocusedModelRootEntryContainer = null;
        myEditorPanel.removeAll();
        return;
      }

      entry.setFocused(true);
      myEditorPanel.removeAll();
      myEditorPanel.add(entry.getEditor().createComponent(), BorderLayout.CENTER);
      myFocusedModelRootEntryContainer = entry;
    } finally {
      myMainPanel.updateUI();
    }
  }

  private void deleteEntry(ModelRootEntryContainer entry) {
    if (myModelRootEntries.contains(entry)) {
      myEditorsListPanel.remove(entry.getComponent());
      int idx = myModelRootEntries.indexOf(entry);
      myModelRootEntries.remove(entry);
      if (myFocusedModelRootEntryContainer.equals(entry)) {
        selectEntry(myModelRootEntries.size() > 0 ?
                    myModelRootEntries.get(Math.max(idx - 1, 0))
                                                  : null);
      } else {
        myMainPanel.updateUI();
      }
    }
  }

  public boolean isModified() {
    List<ModelRootDescriptor> newSet = getDescriptors();
    Collection<ModelRootDescriptor> modelRootDescriptors = myModuleDescriptor.getModelRootDescriptors();
    return !(modelRootDescriptors.containsAll(newSet) && newSet.containsAll(modelRootDescriptors));
  }

  public void apply() {
    myModuleDescriptor.getModelRootDescriptors().clear();
    myModuleDescriptor.getModelRootDescriptors().addAll(getDescriptors());
  }

  private List<ModelRootDescriptor> getDescriptors() {
    List<ModelRootDescriptor> descriptors = new LinkedList<>();
    for (ModelRootEntryContainer container : myModelRootEntries) {
      Memento memento = new MementoImpl();
      container.getModelRoot().save(memento);
      descriptors.add(new ModelRootDescriptor(container.getModelRoot().getType(), memento));
    }
    return descriptors;
  }

  // FIXME it's odd we go to ModelRoot when in fact we have to edit ModelRootDescriptor, remove usages of this method
  //       as I understand, confusion comes from the fact we've got a module with ModelRoots and it's easy to extract relevant data from the ModelRoot instance
  //       However, SModule and Model Root are 'published/registered' projections of what we are going to modify, therefore we shall stick to editing of
  //       respective descriptors
  public Collection<ModelRoot> getModelRoots() {
    List<ModelRoot> modelRoots = new LinkedList<>();
    for (ModelRootEntryContainer container : myModelRootEntries) {
      modelRoots.add(container.getModelRoot());
    }
    return modelRoots;
  }

  public Collection<ModelRootEntryContainer> getModelRootsEntries() {
    return myModelRootEntries;
  }

  public JComponent getComponent() {
    return myMainPanel;
  }

  @Override
  public void dispose() {
  }

  /**
   * Set default folder for FileBasedModel root content dir if module is not in repository yet
   */
  public final void setDefaultFolder(IFile defaultFolder) {
    myDefaultFolder = defaultFolder;
  }

  private class AddContentEntryAction extends IconWithTextAction implements DumbAware {
    private final String myType;

    // type is identity, while name is to get presented to end user and may contain IDEA's mnemonics
    AddContentEntryAction(@NotNull String type, String name) {
      super(name);
      myType = type;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
      ModelRoot modelRoot = PersistenceRegistry.getInstance().getModelRootFactory(myType).create();
      ModelRootEntry entry = myRootEntryPersistence.getModelRootEntry(modelRoot);
      Disposer.register(ModelRootContentEntriesEditor.this, entry);
      if (entry.getModelRoot() instanceof FileBasedModelRoot) {
        if (!checkAndAddFBModelRoot(entry)) {
          return;
        }
      }
      ModelRootEntryContainer container = new ModelRootEntryContainer(entry);
      container.addContentEntryEditorListener(myEditorListener);
      myModelRootEntries.add(container);
      myEditorsListPanel.add(container.getComponent());
      selectEntry(container);
      myEditorsListPanel.revalidate();
      myEditorsListPanel.repaint();
    }

    private boolean checkAndAddFBModelRoot(ModelRootEntry entry) {
      Set<VirtualFile> candidatesForIntersection = new HashSet<>();
      for (ModelRootEntryContainer existingEntryContainer : myModelRootEntries) {
        if (entry.getClass().equals(existingEntryContainer.getModelRootEntry().getClass())) {
          FileBasedModelRoot existingModelRoot = (FileBasedModelRoot) existingEntryContainer.getModelRootEntry().getModelRoot();
          VirtualFile vFile = VirtualFileUtils.getVirtualFile(existingModelRoot.getContentRoot());
          if (vFile == null){
            LOG.error("Can't find file for model root. This root will not be checked for intersection with others. Path: " + existingModelRoot.getContentRoot());
            continue;
          }
          candidatesForIntersection.add(vFile);
        }
      }
      FileChooserDescriptor fileChooserDescriptor = new FileChooserDescriptor(false, true, true, false, true, false);
      fileChooserDescriptor.setTitle("Choose Root Folder for New Model Root");

      IFile contentRoot = getInitialPath();
      VirtualFile chosen = null;
      while (chosen == null) {
        VirtualFile contentRootVFile = VirtualFileUtils.getProjectVirtualFile(contentRoot);
        VirtualFile[] files = FileChooser.chooseFiles(fileChooserDescriptor, null, null, contentRootVFile);
        if (files.length == 0) {
          return false;
        } else {
          assert files.length == 1; // internal contract of the <code>FileChooser</code>
          chosen = files[0];
          for (VirtualFile candidate : candidatesForIntersection) {
            if (doIntersect(chosen, candidate)) {
              Messages.showWarningDialog(myMainPanel,
                                         MessageFormat.format(
                                             "<html>Can''t create new model root, it intersects with the existing model root:<br><b>{0}</b><br><br>Please, choose another folder.</html>",
                                             StringUtil.escapeXml(String.valueOf(candidate))),
                                         "Model Roots Intersection");
              chosen = null;
              break;
            }
          }
        }
      }

      contentRoot = VirtualFileUtils.toIFile(chosen);
      assert contentRoot != null; // : #toIFile method contract
      ((FileBasedModelRoot) entry.getModelRoot()).setContentRoot(contentRoot.getPath());
      return true;
    }

    private boolean doIntersect(VirtualFile chosen, VirtualFile candidate) {
      return isAncestor(candidate, chosen, true) || isAncestor(candidate, chosen, false);
    }
  }

  private IFile getInitialPath() {
    if (myDefaultFolder != null) {
      return myDefaultFolder;
    }
    SModule module = new ModelAccessHelper(myProject.getRepository()).runReadAction(() -> myProject.getRepository().getModule(myModuleDescriptor.getId()));
    assert module != null : "Trying to edit settings of a module " + myModuleDescriptor.getNamespace() + ", which is not in repository";

    if (module instanceof AbstractModule) {
      AbstractModule am = (AbstractModule) module;
      IFile sourceDir = am.getModuleSourceDir();
      return sourceDir != null ? sourceDir : am.getDescriptorFile().getParent();
    }

    return FileSystemExtPoint.getFS().getFile(PathManager.getUserDir());
  }

  private final class MyContentEntryEditorListener implements ContentEntryEditorListener {
    @Override
    public void focused(ModelRootEntryContainer entry) {
      selectEntry(entry);
    }

    @Override
    public void delete(ModelRootEntryContainer entry) {
      deleteEntry(entry);
    }

    @Override
    public void dataChanged(ModelRootEntryContainer entry) {
    }
  }
}
