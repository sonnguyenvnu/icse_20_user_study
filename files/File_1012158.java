/*
 * Copyright 2003-2015 JetBrains s.r.o.
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
package jetbrains.mps.ide.typesystem.trace;

import com.intellij.icons.AllIcons;
import com.intellij.icons.AllIcons.Actions;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Splitter;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.UIUtil;
import jetbrains.mps.icons.MPSIcons.Nodes;
import jetbrains.mps.ide.tools.BaseTool;
import jetbrains.mps.ide.ui.tree.MPSTree;
import jetbrains.mps.newTypesystem.state.State;
import jetbrains.mps.nodeEditor.EditorComponent;
import jetbrains.mps.project.Project;
import jetbrains.mps.workbench.action.ActionUtils;
import jetbrains.mps.workbench.action.BaseAction;
import org.jetbrains.mps.openapi.model.SNode;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;

public class TypeSystemTracePanel extends JPanel implements Disposable {
  private TypeSystemTraceTree myTraceTree;
  private TypeSystemStateTree myStateTree;
  private JPanel myButtons;
  private EditorComponent myEditorComponent;
  private BaseTool myTool;
  private MPSTree myDetailsTree;
  private TypecheckingContextTracker myContextTracker;
  private Project myProject;

  public TypeSystemTracePanel(BaseTool tool) {
    this.setLayout(new BorderLayout());
    myButtons = new JPanel(new BorderLayout());
    this.setMinimumSize(new Dimension(700, 700));
    ApplicationManager.getApplication().invokeLater(() -> {
      JComponent buttonsPanel = ActionManager.getInstance().createActionToolbar(ActionPlaces.TYPE_HIERARCHY_VIEW_TOOLBAR, createButtonsGroup(), true).getComponent();
      myButtons.add(buttonsPanel, BorderLayout.WEST);
    });
    myTool = tool;
  }

  @Override
  public Color getBackground() {
    return Color.WHITE;
  }

  public void showTraceForNode(Project mpsProject, SNode node, EditorComponent editorComponent) {
    cleanUp();
    myProject = mpsProject;
    myEditorComponent = editorComponent;
    myTraceTree = new TypeSystemTraceTree(mpsProject, node, this, editorComponent);
    // TODO: refactor into a separate class 
    this.myDetailsTree = myTraceTree.getDetailsTree();
    myStateTree = new TypeSystemStateTree(mpsProject, myTraceTree.getState(), editorComponent);
    JBScrollPane traceScrollPane = new JBScrollPane(myTraceTree);
    traceScrollPane.setPreferredSize(new Dimension(400, 700));
    JBScrollPane detailsScrollPane = new JBScrollPane(myDetailsTree);
    detailsScrollPane.setPreferredSize(new Dimension(400, 200));
    JBScrollPane stateScrollPane = new JBScrollPane(myStateTree);
    stateScrollPane.setPreferredSize(new Dimension(400, 900));

    Splitter leftHSplitPane = new Splitter(true, 0.8f);
    leftHSplitPane.setFirstComponent(traceScrollPane);
    leftHSplitPane.setSecondComponent(detailsScrollPane);
    leftHSplitPane.getDivider().setBackground(UIUtil.getPanelBackground());
    leftHSplitPane.getDivider().setOpaque(true);

    Splitter vSplitPane = new Splitter(false, 0.65f);
    vSplitPane.setFirstComponent(leftHSplitPane);
    vSplitPane.setSecondComponent(stateScrollPane);
    vSplitPane.getDivider().setBackground(UIUtil.getPanelBackground());
    vSplitPane.getDivider().setOpaque(true);


    this.removeAll();
    this.add(vSplitPane);
    this.add(myButtons, BorderLayout.NORTH);
    this.setVisible(true);
  }

  public void resetState(State state) {
    myStateTree.resetState(state);
  }

  public void updateState(State state) {
    myStateTree.updateState(state);
  }

  @Override
  public void dispose() {
    cleanUp();
  }

  public void cleanUp() {
    if (myTraceTree != null) {
      Disposer.dispose(myTraceTree);
      this.myTraceTree = null;
    }
    if (myStateTree != null) {
      Disposer.dispose(myStateTree);
      this.myStateTree = null;
    }
    if (myDetailsTree != null) {
      Disposer.dispose(myDetailsTree);
      this.myDetailsTree = null;
    }
  }
  protected DefaultActionGroup createButtonsGroup() {

    return ActionUtils.groupFromActions(
        new BaseAction("Close", "Close type system trace tool", Actions.Cancel) {
          @Override
        protected void doExecute(AnActionEvent e, Map<String, Object> _params) {
            myTool.setAvailable(false);
          }
        },
/*      Let's always show applied rules

        new MyToggleAction("Show Apply Rule", "Show apply rule operations in trace", Nodes.Rule) {
          @Override
          boolean initialState() {
            return TraceSettings.isShowApplyRuleOperations();
          }

          @Override
          void stateChanged(boolean newState) {
            TraceSettings.setShowApplyRuleOperations(newState);
          }
        },
*/
/*      Generation mode is not currently supported

        new ToggleAction("Generation Mode", "Show trace in generation mode", Nodes.Generator) {
          @Override
          public boolean isSelected(AnActionEvent e) {
            return TraceSettings.isGenerationMode();
          }

          @Override
          public void setSelected(AnActionEvent e, boolean state) {
            TraceSettings.setGenerationMode(state);
          }
        },
 */
        new MyToggleAction("Trace for Selected Node", "Show trace for selected node", Nodes.Node) {
          @Override
          boolean initialState() {
            return TraceSettings.isTraceForSelectedNode();
          }

          @Override
          void stateChanged(boolean newState) {
            TraceSettings.setTraceForSelectedNode(newState);
          }
        },
/*      Always show type expansion

        new MyToggleAction("Show types expansion", "Show types expansion", AllIcons.Nodes.Folder) {
          @Override
          boolean initialState() {
            return TraceSettings.isShowTypesExpansion();
          }

          @Override
          void stateChanged(boolean newState) {
            TraceSettings.setShowTypesExpansion(newState);
          }
        },
*/
/*      Never show block dependencies
        new MyToggleAction("Show block dependencies", "Show block dependencies in trace", MPSIcons.General.Block) {
          @Override
          boolean initialState() {
            return TraceSettings.isShowBlockDependencies();
          }

          @Override
          void stateChanged(boolean newState) {
            TraceSettings.setShowBlockDependencies(newState);
          }
        },
 */
        new BaseAction("Refresh", "Refresh", Actions.Refresh) {
          @Override
        protected void doExecute(AnActionEvent e, Map<String, Object> _params) {
            myProject.getModelAccess().runReadInEDT(() -> refresh());
          }
        },
        new BaseAction("Next error", "Navigate to next error in trace", AllIcons.General.Error) {
          @Override
        protected void doExecute(AnActionEvent e, Map<String, Object> _params) {
            myTraceTree.goToNextError();
          }
        });
  }

  public void refresh() {
    if (myEditorComponent == null || myProject == null) {
      return;
    }
    final SNode selectedNode = myEditorComponent.getSelectedNode();
    if (selectedNode == null) {
      return;
    }
    showTraceForNode(myProject, selectedNode, myEditorComponent);
    this.validate();
  }

  protected abstract class MyToggleAction extends ToggleAction {
    public MyToggleAction(String text, String description, Icon icon) {
      super(text, description, icon);
      mySelected = initialState();
    }

    @Override
    public boolean isSelected(AnActionEvent e) {
      return mySelected;
    }

    @Override
    public void setSelected(AnActionEvent e, boolean state) {
      mySelected = state;
      stateChanged(state);
      if (myTraceTree != null) {
        myTraceTree.rebuildNow();
        myTraceTree.expandAll();
      }
    }

    abstract boolean initialState();

    abstract void stateChanged(boolean newState);

    private boolean mySelected;
  }

}
