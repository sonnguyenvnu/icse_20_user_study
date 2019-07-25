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

package jetbrains.mps.idea.core.facet.ui;

import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.ide.util.BrowseFilesListener;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.SourceFolder;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.FieldPanel;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.InsertPathAction;
import jetbrains.mps.idea.core.MPSBundle;
import jetbrains.mps.idea.core.facet.MPSConfigurationBean;
import jetbrains.mps.idea.core.ui.SModuleConfigurationTab;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MPSFacetPathsTab implements SModuleConfigurationTab {
    private JPanel myRootPanel;
    private JRadioButton myUseTransientOutputFolder;
    private JRadioButton myUseModuleSourceFolderRadioButton;
    private JRadioButton myUseCustomFolderRadioButton;
    private JComboBox myModuleReferenceCombo;
    private JLabel mySourceFolderLabel;
    private JLabel myOutputFolderLabel;
    private CommitableFieldPanel myFieldPanel;
    private JPanel myGeneratorOutputPathPanel;

    private FacetEditorContext myContext;

    public MPSFacetPathsTab(FacetEditorContext context) {
        ActionListener radioButtonHandler = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myModuleReferenceCombo.setEnabled(isUseModuleSourceFolder());
                mySourceFolderLabel.setEnabled(isUseModuleSourceFolder());
                myFieldPanel.setEnabled(!isUseModuleSourceFolder() && !isUseTransientOutputFolder());
                myOutputFolderLabel.setEnabled(!isUseModuleSourceFolder() && !isUseTransientOutputFolder());
            }
        };
        myUseTransientOutputFolder.addActionListener(radioButtonHandler);
        myUseModuleSourceFolderRadioButton.addActionListener(radioButtonHandler);
        myUseCustomFolderRadioButton.addActionListener(radioButtonHandler);
        myContext = context;
    }

    private boolean isUseTransientOutputFolder() {
        return myUseTransientOutputFolder.isSelected();
    }

    private boolean isUseModuleSourceFolder() {
        return myUseModuleSourceFolderRadioButton.isSelected();
    }

    private String getGeneratorOutputPath() {
        return isUseModuleSourceFolder() && myModuleReferenceCombo.getItemCount() > 0 ? (String) myModuleReferenceCombo.getSelectedItem() : myFieldPanel.getText();
    }

    public JPanel getRootPanel() {
        return myRootPanel;
    }

    public void reset(MPSConfigurationBean data) {
        if (data.isUseTransientOutputFolder()) {
            myUseTransientOutputFolder.doClick();
        } else if (data.isUseModuleSourceFolder()) {
            myUseModuleSourceFolderRadioButton.doClick();
        } else {
            myUseCustomFolderRadioButton.doClick();
        }
        myFieldPanel.setText(data.getGeneratorOutputPath());
    }

    public void apply(MPSConfigurationBean data) {
        data.setUseTransientOutputFolder(isUseTransientOutputFolder());
        data.setUseModuleSourceFolder(isUseModuleSourceFolder());
        data.setGeneratorOutputPath(getGeneratorOutputPath());
    }

    public boolean isModified(MPSConfigurationBean data) {
        if (data.isUseTransientOutputFolder() != isUseTransientOutputFolder()) {
            return true;
        }
        if (data.isUseModuleSourceFolder() != isUseModuleSourceFolder()) {
            return true;
        }
        if (!data.getGeneratorOutputPath().equals(getGeneratorOutputPath())) {
            return true;
        }
        return false;
    }

    public void onTabEntering() {
        String oldSelection = null;
        if (isUseModuleSourceFolder()) {
            oldSelection = myModuleReferenceCombo.getItemCount() == 0 ? myFieldPanel.getText() : (String) myModuleReferenceCombo.getSelectedItem();
        }

        boolean hasOldItem = false;
        myModuleReferenceCombo.removeAllItems();
        for (ContentEntry entry : myContext.getRootModel().getContentEntries()) {
            for (SourceFolder sourceFolder : entry.getSourceFolders()) {
                if (sourceFolder.getFile() == null) {
                    continue;
                }
                String path = sourceFolder.getFile().getPath();
                myModuleReferenceCombo.addItem(path);
                if (path.equals(oldSelection)) {
                    hasOldItem = true;
                }
            }
        }
        limitWidth(myModuleReferenceCombo);

        if (oldSelection != null) {
            if (hasOldItem) {
                myModuleReferenceCombo.setSelectedItem(oldSelection);
            } else {
                myUseCustomFolderRadioButton.doClick();
                myFieldPanel.setText(oldSelection);
            }
        }
    }

    private void limitWidth(JComponent component) {
        Dimension preferredSize = component.getPreferredSize();
        if (preferredSize.getWidth() > 250) {
            preferredSize.setSize(250, preferredSize.getHeight());
            component.setPreferredSize(preferredSize);
        }
    }

    private void createUIComponents() {
        initGeneratorOutputPathPanel();

        initFieldPanel();
    }

    private void initFieldPanel() {
        final JTextField textField = new JTextField();
        final FileChooserDescriptor outputPathsChooserDescriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
        outputPathsChooserDescriptor.setHideIgnored(false);
        InsertPathAction.addTo(textField, outputPathsChooserDescriptor);
        FileChooserFactory.getInstance().installFileCompletion(textField, outputPathsChooserDescriptor, true, null);
        final Runnable commitRunnable = new Runnable() {
            public void run() {
//            if (!getModel().isWritable()) {
//              return;
//            }
//            final String path = textField.getText().trim();
//            if (path.length() == 0) {
//              commitPathRunnable.saveUrl(null);
//            }
//            else {
//              // should set only absolute paths
//              String canonicalPath;
//              try {
//                canonicalPath = FileUtil.resolveShortWindowsName(path);
//              }
//              catch (IOException e) {
//                canonicalPath = path;
//              }
//              commitPathRunnable.saveUrl(VfsUtil.pathToUrl(FileUtil.toSystemIndependentName(canonicalPath)));
//            }
            }
        };

        textField.getDocument().addDocumentListener(new DocumentAdapter() {
            protected void textChanged(DocumentEvent e) {
                commitRunnable.run();
            }
        });

        myFieldPanel = new CommitableFieldPanel(textField, null, null, new BrowseFilesListener(textField, MPSBundle.message("facet.paths.tab.generator.output.select.generator.output.folder"), "", outputPathsChooserDescriptor) {
            public void actionPerformed(ActionEvent e) {
                super.actionPerformed(e);
                commitRunnable.run();
            }
        }, null, commitRunnable);
    }

    private void initGeneratorOutputPathPanel() {
        myGeneratorOutputPathPanel = new JPanel();
        myGeneratorOutputPathPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), IdeBorderFactory.createTitledBorder(MPSBundle.message("facet.paths.tab.generator.output.title"))));
    }

    public static class CommitableFieldPanel extends FieldPanel {
        private final Runnable myCommitRunnable;

        public CommitableFieldPanel(final JTextField textField,
                                    String labelText,
                                    final String viewerDialogTitle,
                                    ActionListener browseButtonActionListener,
                                    final Runnable documentListener,
                                    final Runnable commitPathRunnable) {
            super(textField, labelText, viewerDialogTitle, browseButtonActionListener, documentListener);
            myCommitRunnable = commitPathRunnable;
        }

        public void commit() {
            myCommitRunnable.run();
        }
    }
}
