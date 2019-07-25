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
package jetbrains.mps.ide.devkit.generator;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import com.intellij.ui.content.ContentManagerAdapter;
import com.intellij.ui.content.ContentManagerEvent;
import jetbrains.mps.generator.GenerationTrace;
import jetbrains.mps.ide.generator.TransientModelsComponent;
import jetbrains.mps.ide.icons.IdeIcons;
import jetbrains.mps.ide.tools.BaseProjectTool;
import jetbrains.mps.ide.tools.CloseAction;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.util.Computable;
import jetbrains.mps.workbench.action.ActionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

public class GenerationTracerViewTool extends BaseProjectTool {
  private NoTabsComponent myNoTabsComponent;

  private List<GenerationTracerView> myTracerViews = new ArrayList<>();
  private ContentManagerAdapter myContentListener;
  private final TransientModelsComponent myTransientModelsOwner;
  private boolean myAutoscroll;


  public GenerationTracerViewTool(Project project, TransientModelsComponent transientModels) {
    super(project, "Generation Tracer", null, IdeIcons.DEFAULT_ICON, ToolWindowAnchor.BOTTOM, false, true);
    myTransientModelsOwner = transientModels;
    myNoTabsComponent = new NoTabsComponent(this);
  }

  //////
  public boolean hasTracingData() {
    Computable<Boolean> r = () -> {
      // FIXME not quite nice code
      return myTransientModelsOwner.getModules().iterator().hasNext();
    };
    return new ModelAccessHelper(myTransientModelsOwner.getRepository()).runReadAction(r);
  }
  public boolean hasTraceInputData(SModelReference modelReference) {
    return myTransientModelsOwner.getTrace(modelReference) != null;
  }
  public boolean hasTracebackData(SModelReference modelReference) {
    return myTransientModelsOwner.getTrace(modelReference) != null;
  }
  public boolean showTraceInputData(@NotNull SNode node) {
    int index = getTabIndex(GenerationTracerView.Kind.TraceForward, node.getReference());
    if (index > -1) {
      selectIndex(index);
      openToolLater(true);
      return true;
    }

    TraceNodeUI tracerNode = buildForwardTrace(node);
    if (tracerNode == null) {
      return false;
    }
    showTraceView(GenerationTracerView.Kind.TraceForward, tracerNode, node);
    return true;
  }

  public boolean showTracebackData(SNode node) {
    int index = getTabIndex(GenerationTracerView.Kind.TraceBackward, node.getReference());
    if (index > -1) {
      selectIndex(index);
      openToolLater(true);
      return true;
    }
    TraceNodeUI tracerNode = buildBackwardTrace(node);
    if (tracerNode == null) {
      return false;
    }
    showTraceView(GenerationTracerView.Kind.TraceBackward, tracerNode, node);
    return true;
  }

  //////////////////

  @Override
  protected void createTool(boolean early) {
    StartupManager.getInstance(getProject()).runWhenProjectIsInitialized(() -> {
      showNoTabsComponent();
      setTracingDataIsAvailable(hasTracingData());
      setAvailable(false);
    });
  }

  protected void doRegister() {
    super.doRegister();
    myContentListener = new ContentManagerAdapter() {
      public void contentRemoved(ContentManagerEvent event) {
        final boolean removedNoTabsTab = event.getContent().getComponent() == myNoTabsComponent;
        //noTabs component could be removed
        if (!removedNoTabsTab) {
          myTracerViews.remove(event.getIndex());
        }
        if (getContentManager().getContentCount() == 0) {
          showNoTabsComponent();
          if (removedNoTabsTab) {
            makeUnavailableLater();
          }
        }
      }
    };

    getContentManager().addContentManagerListener(myContentListener);
  }

  @Override
  protected void doUnregister() {
    final ContentManager contentManager = getContentManager();
    if (myContentListener != null && contentManager != null && !contentManager.isDisposed()) {
      contentManager.removeContentManagerListener(myContentListener);
    }
    myContentListener = null;
    super.doUnregister();
  }

  private void showNoTabsComponent() {
    ContentManager manager = getContentManager();
    manager.removeAllContents(true);
    addContent(myNoTabsComponent, "", null, false);
  }

  private void closeTab(int index) {
    //noinspection ConstantConditions
    getContentManager().removeContent(getContentManager().getContent(index), true);
  }

  public void closeAll() {
    getContentManager().removeAllContents(true);
  }

  void selectIndex(int index) {
    ContentManager manager = getContentManager();
    //noinspection ConstantConditions
    manager.setSelectedContent(manager.getContent(index));
  }

  int getTabIndex(GenerationTracerView.Kind kind, SNodeReference node) {
    int index = 0;
    for (GenerationTracerView tracerView : myTracerViews) {
      if (tracerView.isViewFor(kind, node)) {
        return index;
      }
      index++;
    }
    return -1;
  }
  boolean isAutoscroll() {
    return myAutoscroll;
  }
  void autoscrollsChanged(boolean b) {
    if (myAutoscroll != b) {
      myAutoscroll = b;
      for (GenerationTracerView tracerView : myTracerViews) {
        tracerView.setAutoscrollToSource(b);
      }
    }
  }
  void close(GenerationTracerView view) {
    closeTab(myTracerViews.indexOf(view));
  }

  void showTraceView(GenerationTracerView.Kind viewToken, TraceNodeUI tracerNode, SNode node) {
    GenerationTracerView tracerView = new GenerationTracerView(this, node.getReference(), viewToken, tracerNode);
    myTracerViews.add(tracerView);
    Icon i = Icons.getIcon(tracerView.isForwardTraceView() ? TraceNodeUI.Kind.INPUT : TraceNodeUI.Kind.OUTPUT, node);
    Content content = addContent(tracerView.getComponent(), node.getPresentation(), i, true);
    getContentManager().setSelectedContent(content);

    Content noTabsContent = getContentManager().getContent(myNoTabsComponent);
    if (noTabsContent != null) {
      getContentManager().removeContent(noTabsContent, true);
    }

    openToolLater(true);
  }

  public void setTracingDataIsAvailable(final boolean dataPresent) {
    ApplicationManager.getApplication().invokeLater(() -> myNoTabsComponent.setDataIsAvailable(dataPresent));
  }

  @Override
  public Project getProject() {
    return super.getProject(); // public for GenerationTracerView
  }

  TraceNodeUI buildForwardTrace(SNode node) {
    final GenerationTrace ngt = myTransientModelsOwner.getTrace(node.getModel().getReference());
    if (ngt == null) {
      return null;
    }
    TraceNodeUI newTrace = new TraceNodeUI("New gen tracer", Icons.COLLECTION, node.getReference());
    // XXX for now, we assume template source models reside in the same repository as the transient/origin node, this in
    // not generally true. Likely shall be project repository (if different than that of transient modules) or the one with deployed languages
    for (TraceNodeUI n : TraceBuilderUI.buildTrace(ngt, node, node.getModel().getRepository())) {
      newTrace.addChild(n);
    }
    return newTrace;
  }
  TraceNodeUI buildBackwardTrace(SNode node) {
    final GenerationTrace ngt = myTransientModelsOwner.getTrace(node.getModel().getReference());
    if (ngt == null) {
      return null;
    }
    TraceNodeUI newTrace = new TraceNodeUI("New gen tracer", Icons.COLLECTION, node.getReference());
    for (TraceNodeUI n : TraceBuilderUI.buildBackTrace(ngt, node, node.getModel().getRepository())) {
      newTrace.addChild(n);
    }
    return newTrace;
  }

  public static class NoTabsComponent extends JPanel {
    JPanel myLabelsPanel = new JPanel();

    public NoTabsComponent(final GenerationTracerViewTool tool) {
      setLayout(new BorderLayout());

      JPanel mainPanel = new JPanel(new GridBagLayout());
      myLabelsPanel.setLayout(new BoxLayout(myLabelsPanel, BoxLayout.Y_AXIS));
      GridBagConstraints c = new GridBagConstraints();
      c.anchor = GridBagConstraints.CENTER;
      mainPanel.add(myLabelsPanel, c);
      add(mainPanel, BorderLayout.CENTER);

      ApplicationManager.getApplication().invokeLater(() -> {
        ActionGroup group = ActionUtils.groupFromActions(new CloseAction(tool));

        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, group, false);
        add(toolbar.getComponent(), BorderLayout.WEST);
      });
    }

    public void setDataIsAvailable(boolean state) {
      myLabelsPanel.removeAll();

      String[] strings;
      if (state) {
        strings = new String[]{"Tracing data is available.", "To view trace/traceback data use generated node's popup menu."};
      } else {
        strings = new String[]{"No data available.", "To use the 'generation tracer' generate model with the 'save transient models' option."};
      }

      for (String string : strings) {
        JLabel label = new JLabel(string);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        myLabelsPanel.add(label);
      }
    }
  }
}
