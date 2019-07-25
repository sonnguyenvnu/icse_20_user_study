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
package jetbrains.mps.ide.projectPane.logicalview;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.projectView.impl.AbstractProjectViewPane;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.DumbService.DumbModeListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.util.ArrayUtil;
import com.intellij.util.messages.MessageBusConnection;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.ide.projectPane.BaseLogicalViewProjectPane;
import jetbrains.mps.ide.projectPane.ProjectPane;
import jetbrains.mps.ide.projectPane.ProjectPaneActionGroups;
import jetbrains.mps.ide.projectPane.ProjectPaneDnDListener;
import jetbrains.mps.ide.projectPane.logicalview.highlighting.ProjectPaneTreeHighlighter;
import jetbrains.mps.ide.ui.smodel.ConceptTreeNode;
import jetbrains.mps.ide.ui.smodel.PropertiesTreeNode;
import jetbrains.mps.ide.ui.smodel.ReferencesTreeNode;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.ide.ui.tree.module.ProjectModuleTreeNode;
import jetbrains.mps.ide.ui.tree.module.SModelsSubtree;
import jetbrains.mps.ide.ui.tree.smodel.NodeTargetProvider;
import jetbrains.mps.ide.ui.tree.smodel.PackageNode;
import jetbrains.mps.ide.ui.tree.smodel.SModelTreeNode;
import jetbrains.mps.ide.ui.tree.smodel.SModelTreeNode.LongModelNameText;
import jetbrains.mps.ide.ui.tree.smodel.SNodeTreeNode;
import jetbrains.mps.ide.ui.tree.smodel.SNodeTreeNode.NodeChildrenProvider;
import jetbrains.mps.make.IMakeNotificationListener;
import jetbrains.mps.make.IMakeNotificationListener.Stub;
import jetbrains.mps.make.IMakeService;
import jetbrains.mps.make.MakeNotification;
import jetbrains.mps.make.MakeServiceComponent;
import jetbrains.mps.openapi.navigation.EditorNavigator;
import jetbrains.mps.project.DevKit;
import jetbrains.mps.project.MPSProject;
import jetbrains.mps.project.Solution;
import jetbrains.mps.smodel.Generator;
import jetbrains.mps.smodel.Language;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.SModelStereotype;
import jetbrains.mps.smodel.SNodeUtil;
import jetbrains.mps.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeAccessUtil;
import org.jetbrains.mps.openapi.model.SNodeReference;

import javax.swing.tree.TreePath;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceAdapter;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DropTarget;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * GUESS: while {@link ProjectTree} is deemed for embedded UI components, e.g. in a dialog,
 * this class is intended solely for ProjectPane, thus supports DnD, highlighting (although this might
 * need move to ProjectPane, as it's project stuff and needs Idea's project Message bus), integration with
 * editor (activation, auto-select/expand), etc.
 */
public class ProjectPaneTree extends ProjectTree implements NodeChildrenProvider, ProjectModuleTreeNode.ModuleNodeChildrenProvider {
  private ProjectPane myProjectPane;
  private KeyAdapter myKeyListener = new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getModifiers() != 0 || e.getKeyCode() != KeyEvent.VK_ENTER) {
        return;
      }

      TreePath selPath = getSelectionPath();
      if (selPath != null && selPath.getLastPathComponent() instanceof MPSTreeNode) {
        // reuse method for double click
        doubleClick((MPSTreeNode) selPath.getLastPathComponent());
        e.consume();
      }
    }
  };
  private final ProjectPaneTreeHighlighter myHighlighter;
  private final TreeStructureUpdate myStructureUpdate;
  private final IMakeNotificationListener myMakeListener;

  public ProjectPaneTree(ProjectPane projectPane, Project project) {
    super(ProjectHelper.fromIdeaProject(project));
    myProjectPane = projectPane;

    final MPSProject mpsProject = ProjectHelper.fromIdeaProject(project);
    myHighlighter = new ProjectPaneTreeHighlighter(this, mpsProject);
    myHighlighter.init();
    myStructureUpdate = new TreeStructureUpdate(this);
    myStructureUpdate.init();
    //enter can't be listened using keyboard actions because in this case tree's UI receives it first and just expands a node
    addKeyListener(myKeyListener);

    //drag support is alive while the tree is alive
    DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, new MyDragGestureListener());
    new DropTarget(this, new ProjectPaneDnDListener(this, new MyTransferable(null).getTransferDataFlavors()[0]));

    MessageBusConnection connection = project.getMessageBus().connect();
    Disposer.register(this, connection);
    connection.subscribe(DumbService.DUMB_MODE, new DumbModeListener() {
      @Override
      public void enteredDumbMode() {
        // there used to be update both on enter and exit of the dumb mode, however, I don't see a reason to
        // do it twice. Moreover, there's guard condition in TreeUpdateVisitor that waits for dumb mode to complete.
      }

      @Override
      public void exitDumbMode() {
        myHighlighter.dumbUpdate();
      }
    });
    mpsProject.getComponent(MakeServiceComponent.class).get().addListener(myMakeListener = new Stub() {
      @Override
      public void sessionOpened(MakeNotification notification) {
        myHighlighter.pause();
      }

      @Override
      public void sessionClosed(MakeNotification notification) {
        myHighlighter.resume();
      }
    });
  }

  @Override
  public void rebuildNow() {
    super.rebuildNow();
    // FIXME do it through PPTH or any other way, but don't add dumbUpdate ro runRebuildAction() which is in use for refresh
    //       of a single tree node.
    // gen status is tracked on model level. If there are no model nodes shown yet, generation status for module and
    // namespace nodes shall be updated. Other alternative is to do it in ModuleNodeListener#attach() or in
    // ProjectPaneTreeHighlighter#moduleNodeAdded. MNL at the moment doesn't track gen status notifications, so it's odd to put
    // update there. PPTH#moduleNodeAdded() is decent alternative and perhaps the right thing to do, however, I decided
    // to give it a 'single-shot' approach, to re-highlight a tree once rebuild is over, which seems reasonable.
    myHighlighter.dumbUpdate();
  }

  @Override
  public void dispose() {
    getProject().getComponent(MakeServiceComponent.class).get().removeListener(myMakeListener);
    myStructureUpdate.dispose();
    myHighlighter.dispose();
    removeKeyListener(myKeyListener);
    super.dispose();
  }

  @Override
  protected void doubleClick(@NotNull MPSTreeNode nodeToClick) {
    if (nodeToClick instanceof NodeTargetProvider) {
      final SNodeReference navigationTarget = ((NodeTargetProvider) nodeToClick).getNavigationTarget();
      if (navigationTarget != null) {
        new EditorNavigator(getProject()).shallFocus(true).selectIfChild().open(navigationTarget);
        return;
      }
      // fall-through
    }
    super.doubleClick(nodeToClick);
  }

  @Override
  protected void autoscroll(@NotNull MPSTreeNode nodeToClick) {
    if (nodeToClick instanceof NodeTargetProvider) {
      final SNodeReference navigationTarget = ((NodeTargetProvider) nodeToClick).getNavigationTarget();
      if (navigationTarget != null) {
        new EditorNavigator(getProject()).shallFocus(false).selectIfChild().open(navigationTarget);
        return;
      }
      // fall-through
    }
    super.autoscroll(nodeToClick);
  }

  @Override
  public boolean isAutoOpen() {
    return myProjectPane.getProjectView().isAutoscrollToSource(myProjectPane.getId());
  }

  @Override
  protected String getPopupMenuPlace() {
    return ActionPlaces.PROJECT_VIEW_POPUP;
  }

  @Override
  protected ActionGroup createPopupActionGroup(final MPSTreeNode node) {
    return new ModelAccessHelper(getProject().getModelAccess()).runReadAction(() -> ProjectPaneActionGroups.getActionGroup(node));
  }

  @Override
  public void populate(SNodeTreeNode treeNode) {
    if (myProjectPane.showNodeStructure()) {
      SNode n = treeNode.getSNode();
      if (n == null || n.getModel() == null) {
        return;
      }

      treeNode.add(new ConceptTreeNode(n));
      treeNode.add(new PropertiesTreeNode(n));
      treeNode.add(new ReferencesTreeNode(n));

      for (SNode child : n.getChildren()) {
        treeNode.add(treeNode.createChildTreeNode(child));
      }
    }
  }

  @Override
  public boolean populate(MPSTreeNode treeNode, Language language) {
    return false;
  }

  @Override
  public boolean populate(MPSTreeNode treeNode, Solution solution) {
    if (myProjectPane.isDescriptorModelInSolutionVisible()) {
      // XXX would like to do smth like: solution.getModel(GenericDescriptorModelProvider.myDescriptorModelId)
      @SuppressWarnings("SimplifyOptionalCallChains") // yes, I deliberately avoid ifPresent()
      final SModel descriptorModel = solution.getModels().stream().filter(SModelStereotype::isDescriptorModel).findFirst().orElse(null);
      if (descriptorModel != null) {
        treeNode.add(new SModelTreeNode(descriptorModel, new LongModelNameText()));
      }
      // fall through, shall add regular Solution content
    }
    return false;
  }

  @Override
  public boolean populate(MPSTreeNode treeNode, Generator generator) {
    if (myProjectPane.isDescriptorModelInGeneratorVisible()) {
      return false;
    }
    Predicate<SModel> isDescriptorModel = SModelStereotype::isDescriptorModel;
    new SModelsSubtree(treeNode).create(generator.getModels().stream().filter(isDescriptorModel.negate()).collect(Collectors.toList()));
    return true;
  }

  @Override
  public boolean populate(MPSTreeNode treeNode, DevKit devkit) {
    return false;
  }

  private class MyTransferable implements Transferable {
    private final String mySupportedFlavor = "MPSNodeToMoveFlavor";
    private Object myObject;

    public MyTransferable(Object o) {
      myObject = o;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
      DataFlavor dataFlavor = null;
      try {
        Class aClass = MyTransferable.class;
        dataFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=" + aClass.getName(),
                                    mySupportedFlavor, aClass.getClassLoader());
      } catch (ClassNotFoundException ignored) {
      }
      return new DataFlavor[]{dataFlavor};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
      DataFlavor[] flavors = getTransferDataFlavors();
      return ArrayUtil.find(flavors, flavor) != -1;
    }

    @NotNull
    @Override
    public Object getTransferData(DataFlavor flavor) {
      return myObject;
    }
  }

  private class MyDragSourceListener extends DragSourceAdapter {
    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
      dsde.getDragSourceContext().setCursor(null);
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
      dsde.getDragSourceContext().setCursor(null);
    }
  }

  private class MyDragGestureListener implements DragGestureListener {
    @Override
    public void dragGestureRecognized(final DragGestureEvent dge) {
      if ((dge.getDragAction() & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
        return;
      }
      ProjectView projectView = ProjectView.getInstance(myProjectPane.getProject());
      if (projectView == null) {
        return;
      }
      final AbstractProjectViewPane currentPane = projectView.getCurrentProjectViewPane();
      if (!(currentPane instanceof BaseLogicalViewProjectPane)) {
        return;
      }

      final List<Pair<SNodeReference, String>> result = new ArrayList<>();

      getProject().getModelAccess().runReadAction(() -> {
        for (SNode node : myProjectPane.getSelectedSNodes()) {
          result.add(new Pair<>(new jetbrains.mps.smodel.SNodePointer(node), ""));
        }
        SModel contextDescriptor = myProjectPane.getContextModel();
        if (contextDescriptor != null) {
          for (PackageNode treeNode : myProjectPane.getSelectedTreeNodes(PackageNode.class)) {
            String searchedPack = treeNode.getFullPackage();
            if (treeNode.getChildCount() == 0 || searchedPack == null) {
              continue;
            }
            for (final SNode node : contextDescriptor.getRootNodes()) {
              String nodePack = SNodeAccessUtil.getProperty(node, SNodeUtil.property_BaseConcept_virtualPackage);
              if (nodePack == null) {
                continue;
              }
              if (!nodePack.startsWith(searchedPack)) {
                continue;
              }

              StringBuilder basePack = new StringBuilder();
              String firstPart = treeNode.getPackage();
              String secondPart = "";
              String prefix = searchedPack + ".";
              if (nodePack.startsWith(prefix)) {
                secondPart = nodePack.substring(prefix.length());
              }
              basePack.append(firstPart);
              if (!firstPart.isEmpty() && !secondPart.isEmpty()) {
                basePack.append('.');
              }
              basePack.append(secondPart);
              result.add(new Pair<>(new jetbrains.mps.smodel.SNodePointer(node), basePack.toString()));
            }
          }
        }
      });
      if (result.isEmpty()) {
        return;
      }

      try {
        dge.startDrag(DragSource.DefaultMoveNoDrop, new MyTransferable(result), new MyDragSourceListener());
      } catch (InvalidDnDOperationException ignored) {
      }
    }
  }
}
