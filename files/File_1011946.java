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
package jetbrains.mps.ide.editor;

import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.JBUI;
import jetbrains.mps.ide.ThreadUtils;
import jetbrains.mps.nodeEditor.EditorComponent;
import jetbrains.mps.nodeEditor.EditorPanelManagerImpl;
import jetbrains.mps.nodeEditor.InspectorTool;
import jetbrains.mps.nodeEditor.MementoPersistence;
import jetbrains.mps.nodeEditor.NodeEditorComponent;
import jetbrains.mps.nodeEditor.configuration.EditorConfigurationBuilder;
import jetbrains.mps.openapi.editor.Editor;
import jetbrains.mps.openapi.editor.EditorComponentState;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.EditorState;
import jetbrains.mps.openapi.editor.extensions.EditorExtensionUtil;
import jetbrains.mps.project.Project;
import jetbrains.mps.util.EqualUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseNodeEditor implements Editor {
  private static final Logger LOG = LogManager.getLogger(BaseNodeEditor.class);

  private NodeEditorComponent myEditorComponent;
  private final JComponent myComponent = new EditorPanel();
  private final JComponent myEditorPanel = new JPanel();
  protected final Project myProject;
  private JComponent myReplace = null;
  private SNodeReference myCurrentlyEditedNode = null;
  protected final Map<TaskType, PrioritizedTask> myType2TaskMap = new HashMap<>();
  private boolean mySelected;

  public BaseNodeEditor(@NotNull Project mpsProject) {
    myProject = mpsProject;
    myEditorPanel.setLayout(new BorderLayout());
    myEditorPanel.setBorder(new EmptyBorder(JBUI.emptyInsets()));
    myComponent.add(myEditorPanel, BorderLayout.CENTER);
    showEditor();
  }

  public abstract List<Document> getAllEditedDocuments();

  public JComponent getComponent() {
    return myComponent;
  }

  protected JComponent getEditorPanel() {
    return myEditorPanel;
  }

  @Override
  public NodeEditorComponent getCurrentEditorComponent() {
    return myEditorComponent;
  }

  @Override
  public EditorContext getEditorContext() {
    return myEditorComponent == null ? null : myEditorComponent.getEditorContext();
  }

  @Override
  public SNodeReference getCurrentlyEditedNode() {
    return myCurrentlyEditedNode;
  }

  protected void editNode(@NotNull final SNodeReference nodeToEdit, @Nullable final SNodeReference nodeToSelect) {
    assert myEditorComponent != null;
    executeInEDT(new PrioritizedTask(TaskType.EDIT_NODE, myType2TaskMap) {
      @Override
      public void performTask() {
        SNode node = nodeToEdit.resolve(myProject.getRepository());
        if (node == null) {
          return;
        }
        myEditorComponent.editNode(node);
        SNode toSelect = nodeToSelect == null ? null : nodeToSelect.resolve(myProject.getRepository());
        if (toSelect != null) {
          myEditorComponent.getEditorContext().selectWRTFocusPolicy(toSelect, false); // XXX findNodeCell(, true)? to reveal even folded?
        }
      }
    });
    myCurrentlyEditedNode = nodeToEdit;
  }

  protected void executeInEDT(PrioritizedTask task) {
    // XXX I'm not sure this is the right approach (used to be ModelAccess.isInEDT()) -
    // callers expect model read, and check for EDT only is sort of an implicit knowledge about reads/writes.
    // Why not runReadInEDT always?
    if (myProject.getModelAccess().canRead() && ThreadUtils.isInEDT()) {
      task.run();
    } else {
      myProject.getModelAccess().runReadInEDT(task);
    }
  }

  @Override
  public void dispose() {
    setEditorComponent(null);
  }

  @Override
  public boolean isTabbed() {
    return false;
  }

  protected abstract Object getData(@NonNls String dataId);

  private class EditorPanel extends JPanel implements DataProvider {
    private EditorPanel() {
      setLayout(new BorderLayout());
      setBorder(new EmptyBorder(JBUI.emptyInsets()));
    }

    @Override
    @Nullable
    public Object getData(@NonNls String dataId) {
      if (dataId.equals(MPSEditorDataKeys.MPS_EDITOR.getName())) {
        return BaseNodeEditor.this;
      }
      Object data = BaseNodeEditor.this.getData(dataId);
      if (data != null) {
        return data;
      }
      NodeEditorComponent editorComponent = getCurrentEditorComponent();
      return editorComponent == null ? null : editorComponent.getData(dataId);
    }
  }

  protected void showEditor() {
    if (myReplace != null) {
      myEditorPanel.remove(myReplace);
      myReplace = null;
    }
    NodeEditorComponent editorComponent =
        new NodeEditorComponent(myProject.getRepository(), new EditorConfigurationBuilder().editorPanelManager(new EditorPanelManagerImpl(myProject)));
    EditorExtensionUtil.extendUsingProject(editorComponent, myProject);
    setEditorComponent(editorComponent);
  }

  protected void showComponent(JComponent replace) {
    setEditorComponent(null);

    if (myReplace != null) {
      myEditorPanel.remove(myReplace);
      myReplace = null;
    }

    myReplace = replace;
    myEditorPanel.add(myReplace, BorderLayout.CENTER);
    myComponent.validate();
  }

  private void pauseOrResumeHighlighter() {
    if (myEditorComponent == null) {
      return;
    }
    myEditorComponent.getHighlighter().setPaused(!mySelected);
  }

  private void setEditorComponent(NodeEditorComponent editorComponent) {
    if (myEditorComponent == editorComponent) {
      return;
    }

    if (myEditorComponent != null) {
      // Remove old editor component
      myEditorPanel.remove(myEditorComponent.getExternalComponent());
      ThreadUtils.runInUIThreadNoWait(myEditorComponent::dispose);
      myEditorComponent = null;
      myCurrentlyEditedNode = null;
    }

    myEditorComponent = editorComponent;

    if (myEditorComponent != null) {
      // Add new editor component
      JComponent externalComponent = myEditorComponent.getExternalComponent();
      //HACK to avoid strange gray border in ScrollPane after empty aspect tab
      if (externalComponent.getComponent(0) instanceof JBScrollPane) {
        ((JBScrollPane) externalComponent.getComponent(0)).setBorder(new EmptyBorder(JBUI.emptyInsets()));
        ((JBScrollPane) externalComponent.getComponent(0)).getInsets().set(0, 0, 0, 0);
      }
      myEditorPanel.add(externalComponent, BorderLayout.CENTER);
      myComponent.validate();
      pauseOrResumeHighlighter();
    }
  }

  //---state---

  @Override
  public EditorState saveState() {
    BaseEditorState state = new BaseEditorState();
    saveState(state);
    return state;
  }

  protected void saveState(BaseEditorState state) {
    EditorContext editorContext = getEditorContext();
    if (editorContext != null) {
      state.memento = editorContext.getEditorComponentState();
      NodeEditorComponent editorComponent = getCurrentEditorComponent();
      if (editorComponent != null) {
        state.isEditorFocused = editorComponent.getFocusTracker().getEffectiveFocusState();
        EditorComponent inspector = editorComponent.getInspector();
        if (inspector != null) {
          state.inspectorMemento = inspector.getEditorContext().getEditorComponentState();
          state.isInspectorFocused = !state.isEditorFocused && inspector.getFocusTracker().getEffectiveFocusState();
        }
      }
    }
  }

  @Override
  public void loadState(@NotNull EditorState state) {
    if (!(state instanceof BaseEditorState)) {
      return;
    }

    final BaseEditorState s = (BaseEditorState) state;
    final EditorContext editorContext = getEditorContext();
    final NodeEditorComponent editorComponent = getCurrentEditorComponent();
    if (s.memento == null || editorContext == null || editorComponent == null) {
      return;
    }
    final IdeFocusManager focusManager = myProject.getComponent(IdeFocusManager.class);

    executeInEDT(new PrioritizedTask(TaskType.EDITOR_MEMENTO, myType2TaskMap) {
      @Override
      public void performTask() {
        if (editorComponent.isDisposed()) {
          return;
        }
        editorContext.restoreEditorComponentState(s.memento);

        editorComponent.getFocusTracker().setEffectiveFocusState(s.isEditorFocused);
        if (s.isEditorFocused && focusManager != null) {
          focusManager.requestFocus(editorComponent, true);
        }
      }
    });
    if (s.inspectorMemento == null) {
      return;
    }
    final EditorComponent inspectorEditorComponent = editorComponent.getInspector();
    if (inspectorEditorComponent == null) {
      LOG.error("No inspector - memento will not be restored");
      return;
    }
    final EditorContext inspectorEditorContext = inspectorEditorComponent.getEditorContext();
    executeInEDT(new PrioritizedTask(TaskType.INSPECTOR_MEMENTO, myType2TaskMap) {
      @Override
      public void performTask() {
        inspectorEditorContext.restoreEditorComponentState(s.inspectorMemento);
        inspectorEditorComponent.getFocusTracker().setEffectiveFocusState(s.isInspectorFocused);
        if (s.isInspectorFocused && focusManager != null) {
          InspectorTool inspectorTool = myProject.getComponent(InspectorTool.class);
          if (inspectorTool != null && inspectorTool.isAvailable()) {
            inspectorTool.activate();
          }
          focusManager.requestFocus(inspectorEditorComponent, true);
        }
      }
    });
  }

  @Override
  public void selectNotify() {
    setSelected(true);
  }

  @Override
  public void deselectNotify() {
    setSelected(false);
  }

  private void setSelected(boolean selected) {
    mySelected = selected;
    pauseOrResumeHighlighter();
  }

  public abstract static class PrioritizedTask implements Runnable {
    private final TaskType myType;
    private final Map<TaskType, PrioritizedTask> myType2TaskMap;

    public PrioritizedTask(TaskType type, Map<TaskType, PrioritizedTask> type2TaskMap) {
      synchronized (type2TaskMap) {
        myType = type;
        myType2TaskMap = type2TaskMap;
        myType2TaskMap.put(myType, this);
      }
    }

    @Override
    public void run() {
      synchronized (myType2TaskMap) {
        if (myType2TaskMap.get(myType) != this) {
          return;
        }
        myType2TaskMap.remove(myType);
      }
      performTask();
    }

    protected abstract void performTask();
  }

  public enum TaskType {
    EDIT_NODE,
    EDITOR_MEMENTO,
    INSPECTOR_MEMENTO,
    UPDATE_PROPERTIES
  }

  public static class BaseEditorState implements EditorState {
    private static final String MEMENTO = "memento";
    private static final String INSPECTOR_MEMENTO = "inspectorMemento";
    private static final String FOCUSED_COMPONENT_EDITOR = "editorFocused";
    private static final String FOCUSED_COMPONENT_INSPECTOR = "inspectorFocused";

    private EditorComponentState memento;
    private boolean isEditorFocused;
    private EditorComponentState inspectorMemento;
    private boolean isInspectorFocused;

    @Override
    public void save(Element e) {
      if (memento != null) {
        Element mementoElem = new Element(MEMENTO);
        MementoPersistence.saveMemento(memento, mementoElem);
        e.addContent(mementoElem);
      }
      if (inspectorMemento != null) {
        Element mementoElem = new Element(INSPECTOR_MEMENTO);
        MementoPersistence.saveMemento(inspectorMemento, mementoElem);
        e.addContent(mementoElem);
      }

      if (isEditorFocused) {
        e.setAttribute(FOCUSED_COMPONENT_EDITOR, Boolean.TRUE.toString());
      }
      if (isInspectorFocused) {
        e.setAttribute(FOCUSED_COMPONENT_EDITOR, Boolean.TRUE.toString());
      }
    }

    @Override
    public void load(Element e) {
      Element mementoElem = e.getChild(MEMENTO);
      if (mementoElem != null) {
        memento = MementoPersistence.loadMemento(mementoElem);
      }
      Element inspectorMementoElem = e.getChild(INSPECTOR_MEMENTO);
      if (inspectorMementoElem != null) {
        inspectorMemento = MementoPersistence.loadMemento(inspectorMementoElem);
      }

      isEditorFocused = Boolean.parseBoolean(e.getAttributeValue(FOCUSED_COMPONENT_EDITOR));
      isInspectorFocused = Boolean.parseBoolean(e.getAttributeValue(FOCUSED_COMPONENT_INSPECTOR));
    }

    @Override
    public void clearSessionState() {
      if (memento != null) {
        memento.clearSessionState();
      }
      if (inspectorMemento != null) {
        inspectorMemento.clearSessionState();
      }
    }

    @Override
    public int hashCode() {
      int result = memento != null ? memento.hashCode() : 0;
      result = 31 * result + (inspectorMemento != null ? inspectorMemento.hashCode() : 0);
      return result;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      BaseEditorState that = (BaseEditorState) o;
      return EqualUtil.equals(that.memento, memento) && EqualUtil.equals(that.inspectorMemento, inspectorMemento) &&
             that.isEditorFocused == isEditorFocused && that.isInspectorFocused == isInspectorFocused;
    }
  }
}
