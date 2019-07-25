/*
 * Copyright 2003-2017 JetBrains s.r.o.
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
package jetbrains.mps.ide.library;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import jetbrains.mps.library.BaseLibraryManager;
import jetbrains.mps.library.Library;
import jetbrains.mps.util.ToStringComparator;

import javax.swing.AbstractAction;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class LibraryManagerPreferences {
  private BaseLibraryManager myManager;
  private JPanel myMainPanel = new JPanel(new BorderLayout());
  private DefaultListModel<Library> myListModel = new DefaultListModel<>();
  private JList<Library> myLibrariesList = new JBList<>(myListModel);

  private boolean myChanged;

  public LibraryManagerPreferences(BaseLibraryManager manager) {
    myManager = manager;
    //TODO: List of libraries does not include predefined ones. Do we need to include them to the list?

    myLibrariesList.setCellRenderer(new DefaultListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Library library = (Library) value;
        setText("<html><b>" + library.getName() + "</b> (" + library.getPath() + ")");
        return this;
      }
    });
    ToolbarDecorator decorator = ToolbarDecorator.createDecorator(myLibrariesList);
    decorator.disableUpDownActions();
    decorator.setAddAction(anActionButton -> LibraryManagerPreferences.this.add())
             .setRemoveAction(anActionButton -> LibraryManagerPreferences.this.remove())
             .setEditAction(anActionButton -> LibraryManagerPreferences.this.edit());

    myMainPanel.add(decorator.createPanel(), BorderLayout.CENTER);

    updateModel(false);
  }


  private void updateModel(final boolean updateManager) {
    Library oldSelection = myLibrariesList.getSelectedValue();
    List<Library> libraries = new ArrayList<>(myManager.getUILibraries());
    libraries.sort(new ToStringComparator());
    myListModel.clear();
    for (Library l : libraries) {
      myListModel.addElement(l);
    }

    if (oldSelection != null) {
      myLibrariesList.setSelectedValue(oldSelection, true);
    }

    if (updateManager) {
      myManager.getInitializer().update();
    }
  }

  private void remove() {
    myManager.remove(myListModel.get(myLibrariesList.getSelectedIndex()));
    updateModel(true);
    myChanged = true;
  }


  private void edit() {
    Library l = myListModel.get(myLibrariesList.getSelectedIndex());

    FileChooserDescriptor descriptor = new FileChooserDescriptor(false, true, true, true, false, false);
    final VirtualFile result = FileChooser.chooseFile(descriptor, myMainPanel, null, LocalFileSystem.getInstance().findFileByPath(l.getPath()));

    if (result == null) {
      return;
    }

    l.setPath(result.getPath());

    updateModel(true);
    myChanged = true;
  }

  private void add() {
    String name = Messages.showInputDialog(myMainPanel, "Enter a Library name", "New Library", null);

    if (name == null) {
      return;
    }

    FileChooserDescriptor descriptor = new FileChooserDescriptor(false, true, true, true, false, false);
    final VirtualFile result = FileChooser.chooseFile(descriptor, myMainPanel, null, null);

    if (result == null) {
      return;
    }

    myManager.addLibrary(name).setPath(result.getPath());
    updateModel(true);

    myChanged = true;
  }

  public String getName() {
    return "Libraries";
  }

  public Icon getIcon() {
    return null;
  }

  public JComponent getComponent() {
    return myMainPanel;
  }

  public boolean validate() {
    return true;
  }

  public void commit() {

  }

  public boolean isModified() {
    return myChanged;
  }

  public void reset() {

  }
}
