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
package jetbrains.mps.ide.typesystem.trace;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.ui.JBColor;
import jetbrains.mps.ide.ui.tree.MPSTree;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.ide.util.ColorAndGraphicsUtil;
import jetbrains.mps.newTypesystem.state.NodeMaps;
import jetbrains.mps.newTypesystem.state.State;
import jetbrains.mps.newTypesystem.state.blocks.Block;
import jetbrains.mps.newTypesystem.state.blocks.BlockKind;
import jetbrains.mps.newTypesystem.state.blocks.InequalityBlock;
import jetbrains.mps.nodeEditor.DefaultEditorMessage;
import jetbrains.mps.nodeEditor.EditorComponent;
import jetbrains.mps.nodeEditor.EditorMessage;
import jetbrains.mps.nodeEditor.NodeHighlightManager;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.message.EditorMessageOwner;
import jetbrains.mps.openapi.editor.message.SimpleEditorMessage;
import jetbrains.mps.openapi.editor.style.StyleRegistry;
import jetbrains.mps.openapi.navigation.EditorNavigator;
import jetbrains.mps.project.Project;
import jetbrains.mps.util.Pair;
import jetbrains.mps.workbench.MPSDataKeys;
import jetbrains.mps.workbench.action.ActionUtils;
import jetbrains.mps.workbench.action.BaseAction;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TypeSystemStateTree extends MPSTree implements DataProvider {
  private final Project myProject;
  private State myState;
  private EditorComponent myEditorComponent;
  private NodeHighlightManager myHighlightManager;
  private EditorMessageOwner myMessageOwner;

  public TypeSystemStateTree(Project mpsProject, State state, EditorComponent editorComponent) {
    myProject = mpsProject;
    myState = state;
    myEditorComponent = editorComponent;
    this.myHighlightManager = editorComponent.getHighlightManager();
    this.myMessageOwner = new EditorMessageOwner() {
    };
    this.getSelectionModel().addTreeSelectionListener(new TypeSystemStateTree.EditorMessageUpdater());
    this.rebuildNow();
    expandAll();
  }

  public void resetState(State state) {
    myState = state;
    rebuildNow();
    expandAll();
    clearSelection();
  }

  public void updateState(State state) {
    HashSet<String> existing = null;
    if (myState == state) {
      existing = new HashSet<>();
      collectExisting(getRootNode(), existing);
    }
    myState = state;
    rebuildNow();
    expandAll();
    if (existing != null) {
      List<TreePath> newNodes = new ArrayList<>();
      collectNew(new TreePath(getRootNode()), existing, newNodes);
      setSelectionPaths(newNodes.toArray(new TreePath[0]));
    }
  }

  @Override
  public void dispose() {
    clearHighlighting();
    super.dispose();
  }

  @Override
  protected MPSTreeNode rebuild() {
    clearHighlighting();
    return createNode();
  }

  private void collectExisting(MPSTreeNode node, Collection<String> existing) {
    for (int idx = 0; idx < node.getChildCount(); idx++) {
      TreeNode child = node.getChildAt(idx);
      if (child instanceof MPSTreeNode) {
        existing.add(child.toString());
        collectExisting(((MPSTreeNode) child), existing);
      }
    }
  }

  private void collectNew(TreePath path, Collection<String> existing, Collection<TreePath> newNodes) {
    Object lastPathComponent = path.getLastPathComponent();
    if (lastPathComponent instanceof MPSTreeNode) {
      MPSTreeNode node = ((MPSTreeNode) lastPathComponent);
      for (int idx = 0; idx < node.getChildCount(); idx++) {
        TreeNode child = node.getChildAt(idx);
        if (child instanceof MPSTreeNode) {
          TreePath childPath = path.pathByAddingChild(child);
          if (!(existing.contains(child.toString()))) {
            newNodes.add(childPath);
          }
          collectNew(childPath, existing, newNodes);
        }
      }
    }
  }

  private TypeSystemStateTreeNode createNode() {
    TypeSystemStateTreeNode result = new TypeSystemStateTreeNode("Type system state");
    result.add(new TypeSystemStateTreeNode("Solving inequalities in process: " + myState.getInequalities().isSolvingInProcess()));
    TypeSystemStateTreeNode[] nodes = {createInequalitiesNode(), createNode("Comparable", myState.getBlocks(BlockKind.COMPARABLE), null), createNode(
        "When concrete", myState.getBlocks(BlockKind.WHEN_CONCRETE), null), createNode("Errors", myState.getNodeMaps().getErrorListPresentation(),
        JBColor.RED), createNode("Check-only equations", myState.getBlocks(BlockKind.CHECK_EQUATION), null), createEquationsNode()};
    for (TypeSystemStateTreeNode node : nodes) {
      if (node.children().hasMoreElements()) {
        result.add(node);
      }
    }
    return result;
  }

  private TypeSystemStateTreeNode createNode(String category, List<String> entries, Color color) {
    TypeSystemStateTreeNode result = new TypeSystemStateTreeNode(category);
    if (color != null) {
      result.setColor(color);
    }
    for (String string : entries) {
      result.add(new TypeSystemStateTreeNode(string));
    }
    return result;
  }

  private TypeSystemStateTreeNode createNode(String category, Set<Block> entries, Color color) {
    TypeSystemStateTreeNode result = new TypeSystemStateTreeNode(category + " (" + entries.size() + ")");
    if (color == null) {
      color = Color.LIGHT_GRAY;
    }
    result.setColor(color);
    for (Block block : entries) {
      result.add(new BlockTreeNode(block, myState, myEditorComponent));
    }
    return result;
  }

  private TypeSystemStateTreeNode createInequalitiesNode() {
    TypeSystemStateTreeNode result = new TypeSystemStateTreeNode("Inequalities by groups");
    Set<String> nodePresentations = new HashSet<>();
    for (Map.Entry<Set<SNode>, Set<InequalityBlock>> entry : myState.getInequalities().getInequalityGroups(
        myState.getBlocks(BlockKind.INEQUALITY)).entrySet()) {
      Set<SNode> key = entry.getKey();
      TypeSystemStateTreeNode current;
      if (key.isEmpty() || entry.getValue().size() <= 1) {
        current = result;
      } else {
        current = new TypeSystemStateTreeNode(key.toString());
      }
      nodePresentations.clear();
      for (InequalityBlock block : entry.getValue()) {
        BlockTreeNode node = new BlockTreeNode(block, myState, myEditorComponent);
        String presentation = node.toString();
        if (!(nodePresentations.contains(presentation))) {
          current.add(node);
          nodePresentations.add(presentation);
        }
      }
      if (result != current) {
        result.add(current);
      }
    }
    return result;
  }

  private TypeSystemStateTreeNode createEquationsNode() {
    TypeSystemStateTreeNode result = new TypeSystemStateTreeNode("Equations");
    for (Map.Entry<SNode, Set<SNode>> equationGroup : myState.getEquations().getEquationGroups()) {
      result.add(new EquationTreeNode(equationGroup.getKey(), equationGroup.getValue(), myState, myEditorComponent));
    }
    return result;
  }

  private void clearHighlighting() {
    myHighlightManager.clearForOwner(myMessageOwner);
  }

  private void highlightNodesWithTypes(final Collection<? extends MPSTreeNode> treeNodes) {
    clearHighlighting();
    myProject.getModelAccess().runReadAction(() -> {
      NodeMaps maps = myState.getNodeMaps();
      List<EditorMessage> messages = new ArrayList<>();
      for (MPSTreeNode treeNode : treeNodes) {
        TypeSystemStateTreeNode stateNode = (TypeSystemStateTreeNode) treeNode;
        List<SNode> vars = stateNode.getVariables();
        if (null == vars) {
          continue;
        }
        for (SNode var : vars) {
          SNode node = check_x8yvv7_a0a0d0c0a0a0a0b0t(maps, var);
          if (node != null && node.getModel() != null) {
            EditorCell nodeCell = myEditorComponent.findNodeCell(node);
            if (nodeCell != null) {
              messages.add(new TypeEditorMessage(nodeCell, String.valueOf(var)));
            }
          }
        }
        if (messages.size() > 0) {
          myHighlightManager.mark(messages);
        }
      }
    });
  }

  @Override
  protected ActionGroup createPopupActionGroup(final MPSTreeNode treeNode) {
    final TypeSystemStateTreeNode stateNode = (TypeSystemStateTreeNode) treeNode;
    final DefaultActionGroup group = ActionUtils.groupFromActions();
    myProject.getModelAccess().runReadAction(() -> {
      NodeMaps maps = myState.getNodeMaps();
      List<SNode> vars = stateNode.getVariables();
      if (null == vars) {
        return;
      }
      for (SNode var : vars) {
        SNode node = check_x8yvv7_a0a0d0a0a0a0d0u(maps, var);
        if (node != null && node.getModel() != null) {
          final SNodeReference pointer = new jetbrains.mps.smodel.SNodePointer(node);
          group.add(new BaseAction("Go to node with type " + var) {
            @Override
            public void doExecute(AnActionEvent e, Map<String, Object> _params) {
              new EditorNavigator(myProject).shallFocus(true).shallSelect(true).open(pointer);
            }
          });
        }
      }
    });
    return group;
  }

  @Override
  @Nullable
  public Object getData(@NonNls String id) {
    TypeSystemStateTreeNode currentNode = (TypeSystemStateTreeNode) this.getCurrentNode();
    if (id.equals(MPSDataKeys.RULE_MODEL_AND_ID.getName())) {
      String ruleModel = currentNode.getRuleModel();
      String ruleId = currentNode.getRuleId();
      if (ruleModel == null || ruleId == null) {
        return null;
      }
      return new Pair<>(ruleModel, ruleId);
    }
    return null;
  }

  private class TypeEditorMessage extends DefaultEditorMessage {
    private EditorCell myCell;

    public TypeEditorMessage(EditorCell cell, String message) {
      super(cell.getSNode(), StyleRegistry.getInstance().getSimpleColor(Color.blue), message, myMessageOwner);
      this.myCell = cell;
    }

    @Override
    public EditorCell getCell(EditorComponent component) {
      return myCell;
    }

    @Override
    public boolean acceptCell(EditorCell cell, EditorComponent component) {
      return myCell == cell;
    }

    @Override
    protected void paintWithColor(Graphics graphics, EditorCell cell, Color color) {
      int x = cell.getX() + cell.getLeftInset();
      int y = cell.getY() + cell.getTopInset();
      int width = cell.getWidth() - cell.getLeftInset() - cell.getRightInset() - 1;
      int height = cell.getHeight() - cell.getTopInset() - cell.getBottomInset() - 1;

      graphics.setColor(color);
      ColorAndGraphicsUtil.drawDashedRect(graphics, x, y, width, height);
    }

    @Override
    public boolean isBackground() {
      return true;
    }

    @Override
    public boolean sameAs(SimpleEditorMessage that) {
      return super.sameAs(that) && this.equals(that);
    }

    @Override
    public boolean equals(Object that) {
      if (that == null) {
        return false;
      }
      if (this == that) {
        return true;
      }
      if (that.getClass() != TypeSystemStateTree.TypeEditorMessage.class) {
        return false;
      }
      return this.myCell.equals(((TypeSystemStateTree.TypeEditorMessage) that).myCell);
    }

    @Override
    public int hashCode() {
      return myCell.hashCode() * 37;
    }
  }

  private class EditorMessageUpdater implements TreeSelectionListener {
    public EditorMessageUpdater() {
    }

    @Override
    public void valueChanged(TreeSelectionEvent event) {
      List<MPSTreeNode> selection = new ArrayList<>();
      TreePath[] selectionPaths = getSelectionPaths();
      if (selectionPaths == null) {
        clearHighlighting();
        return;
      }
      for (TreePath path : selectionPaths) {
        if (((TreeSelectionModel) event.getSource()).isPathSelected(path)) {
          MPSTreeNode selected = (MPSTreeNode) path.getLastPathComponent();
          selection.add(selected);
        }
      }
      highlightNodesWithTypes(selection);
    }
  }

  private static SNode check_x8yvv7_a0a0d0c0a0a0a0b0t(NodeMaps checkedDotOperand, SNode var) {
    if (null != checkedDotOperand) {
      return checkedDotOperand.getNode(var);
    }
    return null;
  }

  private static SNode check_x8yvv7_a0a0d0a0a0a0d0u(NodeMaps checkedDotOperand, SNode var) {
    if (null != checkedDotOperand) {
      return checkedDotOperand.getNode(var);
    }
    return null;
  }
}
