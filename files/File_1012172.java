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
package jetbrains.mps.typesystem.uiActions;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.ui.ScrollPaneFactory;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.ide.ui.tree.MPSTree;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.ide.ui.tree.TextTreeNode;
import jetbrains.mps.ide.ui.tree.smodel.SNodeTreeNode;
import jetbrains.mps.nodeEditor.highlighter.EditorsHelper;
import jetbrains.mps.openapi.editor.Editor;
import jetbrains.mps.openapi.editor.EditorComponent;
import jetbrains.mps.project.Project;
import jetbrains.mps.util.annotation.ToRemove;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

/**
 * @deprecated not functional and will be removed
 */
@Deprecated
@ToRemove(version = 2019.1)
public class TypecheckerStateViewComponent extends JPanel {
  private static final Logger LOG = LogManager.getLogger(TypecheckerStateViewComponent.class);
  private final Project myProject;

  private SNode myNodeToSliceWith = null;

  public TypecheckerStateViewComponent(@NotNull Project mpsProject) {
    myProject = mpsProject;
    rebuild();
  }

  private void rebuild() {
    removeAll();
    setLayout(new GridBagLayout());

    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 1;
    gridBagConstraints.weighty = 0;
    gridBagConstraints.anchor = GridBagConstraints.NORTH;
    gridBagConstraints.fill = GridBagConstraints.BOTH;

    //upper panel
    JButton debugCurrentRootButton = new JButton(new AbstractAction("Debug Current Root") {
      public void actionPerformed(ActionEvent e) {
        com.intellij.openapi.project.Project project = ProjectHelper.toIdeaProject(myProject);
        Editor currentEditor = EditorsHelper.getSelectedEditors(FileEditorManager.getInstance(project)).get(0);
        if (currentEditor != null) {
          EditorComponent editorComponent = currentEditor.getCurrentEditorComponent();
          if (editorComponent != null) {
            final SNode currentRoot = editorComponent.getEditedNode();
            debugRoot(currentRoot);
          }
        }
      }
    });
    JPanel upperPanel = new JPanel();
    upperPanel.setLayout(new GridBagLayout());
    GridBagConstraints upperPanelConstraints = new GridBagConstraints();
    upperPanelConstraints.gridx = 0;
    upperPanelConstraints.gridy = 0;
    upperPanelConstraints.weightx = 0;
    upperPanelConstraints.weighty = 0;
    upperPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
    upperPanel.add(debugCurrentRootButton, upperPanelConstraints);

    String text = "no info collected";
    upperPanelConstraints.gridx = 1;
    upperPanel.add(new JLabel(text));

    upperPanelConstraints.weightx = 1;
    upperPanelConstraints.gridx = 2;
    upperPanel.add(new JPanel(), upperPanelConstraints);

    add(upperPanel, gridBagConstraints);

    JPanel innerPanel = new JPanel(new GridBagLayout());

    int y = 0;
    GridBagConstraints innerConstraints = new GridBagConstraints();

    if (myNodeToSliceWith != null) {
      //initial node type

      innerConstraints.gridy = y;
      y++;
      innerConstraints.weighty = 0;
      innerConstraints.weightx = 0;
      innerConstraints.fill = GridBagConstraints.NONE;
      innerConstraints.anchor = GridBagConstraints.NORTHWEST;

      innerConstraints.gridx = 0;
      innerPanel.add(new JLabel("initial type: "), innerConstraints);

      innerConstraints.gridx = 1;
      innerConstraints.gridwidth = GridBagConstraints.REMAINDER;
      SNodeTree initialTypeTree = new SNodeTree(null);
      innerPanel.add(initialTypeTree, innerConstraints);
      initialTypeTree.rebuildNow();

      //innerPanel.add(nodeTypePanel, gridBagConstraints);

      //slice items
      innerConstraints.gridwidth = 1;
    }

    innerConstraints.gridy = y;
    innerConstraints.weighty = 1;
    JPanel gauge = new JPanel();
    gauge.setBackground(Color.WHITE);
    innerPanel.add(gauge, innerConstraints);

    innerPanel.setBackground(Color.WHITE);
    JScrollPane scrollPane = ScrollPaneFactory.createScrollPane(innerPanel);
    scrollPane.setBackground(Color.WHITE);

    gridBagConstraints.weighty = 1;
    gridBagConstraints.gridy = 1;
    add(scrollPane, gridBagConstraints);

  }

  public void debugRoot(final SNode currentRoot) {

  }

  public class SNodeTree extends MPSTree {

    private SNode myNode;

    public SNodeTree(SNode node) {
      myNode = node;
    }

    protected MPSTreeNode rebuild() {
      if (myNode == null) {
        return new TextTreeNode("null");
      }
      return new SNodeTreeNode(myNode);
    }


  }
}
