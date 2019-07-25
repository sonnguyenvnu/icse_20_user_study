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
package jetbrains.mps.nodeEditor;

import jetbrains.mps.errors.item.NodeReportItem;
import jetbrains.mps.errors.item.RuleIdFlavouredItem;
import jetbrains.mps.errors.item.RuleIdFlavouredItem.TypesystemRuleId;
import jetbrains.mps.ide.ThreadUtils;
import jetbrains.mps.openapi.navigation.EditorNavigator;
import jetbrains.mps.project.Project;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MPSErrorDialog extends JDialog {
  private static final int MIN_SIDE_PADDING = 30;

  private List<JButton> myButtons = new ArrayList<>();
  private boolean myIsInitialized = false;
  private JTextField myField;
  private final Window myOwner;
  private String myErrorString;
  private KeyListener myEscapeListener = new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        dispose();
        e.consume();
      }
    }
  };

  public MPSErrorDialog(Frame frame, String error, String title) {
    this(frame, error, title, true);
  }

  public MPSErrorDialog(Window window, String error, String title, boolean initializeUI) throws HeadlessException {
    super(window, title, Dialog.DEFAULT_MODALITY_TYPE);
    myOwner = window;
    init(error);
    if (initializeUI) {
      initializeUI();
      setVisible(true);
    }
  }

  static void showCellErrorDialog(Project project, Window window, HighlighterMessage message) {
    if (message == null || message.getReportItem() == null) {
      return;
    }
    final NodeReportItem herror = message.getReportItem();
    ThreadUtils.runInUIThreadNoWait(() -> {
      String s = message.getMessage();
      final MPSErrorDialog dialog = new MPSErrorDialog(window, s, message.getStatus().getPresentation(), false);
      List<TypesystemRuleId> ruleIds = new ArrayList<>(RuleIdFlavouredItem.FLAVOUR_RULE_ID.getCollection(herror));
      if (!ruleIds.isEmpty()) {
        final JButton button = new JButton();
        AbstractAction action = new GoToRuleAction("Go To Rule", ruleIds, dialog, button, project);
        button.setAction(action);
        dialog.addButton(button);
      }
      dialog.initializeUI();
      dialog.setVisible(true);
    });
  }

  private void init(String error) {
    myErrorString = error;
    setLayout(new BorderLayout());
    myField = new JTextField(error);
    myField.setEditable(false);
    myField.addKeyListener(myEscapeListener);
    JButton button = new JButton(new AbstractAction("OK") {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    getRootPane().setDefaultButton(button);
    myButtons.add(button);
  }

  public void initializeUI() {
    int textWidth = myField.getFontMetrics(myField.getFont()).stringWidth(myErrorString);
    JPanel panel = new JPanel(new GridLayout(1, myButtons.size()));
    for (JButton jButton : myButtons) {
      panel.add(jButton);
    }
    panel.doLayout();
    int buttonsWidth = (int) panel.getPreferredSize().getWidth();
    int minPanelWidth = Math.max(2 * MIN_SIDE_PADDING + buttonsWidth, 2 * MIN_SIDE_PADDING + textWidth);
    int calculatedButtonsPadding = (minPanelWidth - buttonsWidth) / 2;
    int calculatedTextPadding = (minPanelWidth - textWidth) / 2;
    panel.setBorder(new EmptyBorder(5, calculatedButtonsPadding, 15, calculatedButtonsPadding));
    myField.setBorder(new EmptyBorder(20, calculatedTextPadding, 5, calculatedTextPadding));
    add(myField, BorderLayout.CENTER);
    add(panel, BorderLayout.SOUTH);
    pack();
    setResizable(false);
    setLocation(myOwner.getX() + (myOwner.getWidth() - this.getWidth()) / 2,
        myOwner.getY() + (myOwner.getHeight() - this.getHeight()) / 2);
    myIsInitialized = true;
  }

  public void addButton(JButton button) {
    myButtons.add(button);
    myIsInitialized = false;
  }

  @Override
  public void setVisible(boolean b) {
    assert !b || myIsInitialized;
    super.setVisible(b);
  }

  private static class GoToRuleAction extends AbstractAction {
    private final List<TypesystemRuleId> myRuleIds;
    private final MPSErrorDialog myDialog;
    private final JButton myButton;
    private final Project myProject;

    public GoToRuleAction(String message, List<TypesystemRuleId> ruleIds, MPSErrorDialog dialog, JButton button, Project project) {
      super(message);
      myRuleIds = ruleIds;
      myDialog = dialog;
      myButton = button;
      myProject = project;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (myRuleIds.size() > 1) {
        JPopupMenu popupMenu = new JPopupMenu();
        List<TypesystemRuleId> ruleIds = new ArrayList<>(myRuleIds);
        while (ruleIds.size() > 1) {
          TypesystemRuleId ruleId = ruleIds.remove(ruleIds.size() - 1);
          popupMenu.add(new GoToRuleAction("Go To Rule " + ruleId, Collections.singletonList(ruleId), myDialog, myButton, myProject));
        }
        popupMenu.add(new GoToRuleAction("Go To Immediate Rule", Collections.singletonList(ruleIds.remove(0)), myDialog, myButton, myProject));
        popupMenu.show(myButton, 0, myButton.getHeight());
      } else {
        new EditorNavigator(myProject).shallSelect(true).open(myRuleIds.get(0).getSourceNode());
        myDialog.dispose();
      }
    }
  }
}
