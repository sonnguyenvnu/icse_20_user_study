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
package jetbrains.mps.ide.devkit.cellExplorer.cellsTree;

import jetbrains.mps.icons.MPSIcons.CellExplorer;
import jetbrains.mps.ide.devkit.cellExplorer.CellExplorerTool;
import jetbrains.mps.ide.devkit.cellExplorer.MPSTreeWithAction;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.ide.ui.tree.TextTreeNode;
import jetbrains.mps.nodeEditor.EditorComponent;
import jetbrains.mps.openapi.editor.cells.CellTraversalUtil;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.update.UpdaterListener;
import jetbrains.mps.openapi.editor.update.UpdaterListenerAdapter;
import jetbrains.mps.smodel.ModelReadRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.ModelAccess;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.Collections;
import java.util.List;

public class CellsTree extends MPSTreeWithAction {
  private final UpdaterListener mySynchronizationListener = new MyUpdaterListenerAdapter();
  private EditorComponent myCurrentEditor;

  public CellsTree() {
    setRootVisible(true);
  }

  @Override
  protected void doInit(MPSTreeNode node, Runnable nodeInitRunnable) {
    // myCurrentEditor could not be null, otherwise we won't get to CellNode.init()
    super.doInit(node, new ModelReadRunnable(myCurrentEditor.getEditorContext().getRepository().getModelAccess(), nodeInitRunnable));
  }

  @Override
  protected void runRebuildAction(Runnable rebuildAction, boolean saveExpansion) {
    if (myCurrentEditor == null) {
      super.runRebuildAction(rebuildAction, saveExpansion);
    } else {
      ModelAccess modelAccess = myCurrentEditor.getEditorContext().getRepository().getModelAccess();
      super.runRebuildAction(new ModelReadRunnable(modelAccess, rebuildAction), saveExpansion);
    }
  }

  @Override
  protected MPSTreeNode rebuild() {
    if (myCurrentEditor == null || myCurrentEditor.getRootCell() == null) {
      TextTreeNode rv = new TextTreeNode("No editor selected");
      rv.setIcon(CellExplorer.CellExplorer);
      return rv;
    } else {
      return new CellNode(myCurrentEditor.getRootCell());
    }
  }

  public void setCurrentEditor(EditorComponent cellEditor) {
    if (myCurrentEditor == cellEditor) return;
    if (myCurrentEditor != null) {
      myCurrentEditor.getUpdater().removeListener(mySynchronizationListener);
    }

    myCurrentEditor = cellEditor;

    if (myCurrentEditor != null) {
      myCurrentEditor.getUpdater().addListener(mySynchronizationListener);
    }

    rebuildNow();
  }

  @Override
  public void dispose() {
    setCurrentEditor(null);
    super.dispose();
  }

  public void showCellInTree(EditorCell cell) {
    EditorComponent cellEditor = (EditorComponent) cell.getEditorComponent();
    setCurrentEditor(cellEditor);

    MPSTreeNode current = findCellNode(cell);
    if (current == null) {
      CellExplorerTool.LOG.warn("Can't find cell in tree");
      return;
    }
    selectNode(current);
  }

  private MPSTreeNode findCellNode(EditorCell cell) {
    if (cell == null) return null;

    List<EditorCell> path = getEditorCellPath(cell);

    MPSTreeNode current = getRootNode();
    if (current.getUserObject() != path.get(0)) return null;

    for (int i = 1; i < path.size(); i++) {
      if (!current.isInitialized()) current.init();
      current = current.findExactChildWith(path.get(i));
      if (current == null) return null;
    }

    return current;
  }

  @NotNull
  private static List<EditorCell> getEditorCellPath(EditorCell cell) {
    List<EditorCell> path = CellTraversalUtil.getParents(cell, true);
    Collections.reverse(path);
    return path;
  }

  @Nullable
  public EditorCell getCellByPath(@Nullable TreePath path) {
    if (path == null) return null;

    Object[] nodes = path.getPath();
    for (int i = nodes.length - 1; i >= 0; i--) {
      Object node = nodes[i];
      if (!(node instanceof DefaultMutableTreeNode)) continue;

      Object userObject = ((DefaultMutableTreeNode) node).getUserObject();
      if (userObject instanceof EditorCell) {
        return ((EditorCell) userObject);
      }
    }

    return null;
  }

  private class MyUpdaterListenerAdapter extends UpdaterListenerAdapter {
    private CellNode myCachedPropertyCellNode = null;

    @Override
    public void cellSynchronizedWithModel(EditorCell cell) {
      if (cell == null) {
        return;
      }
      CellNode cellNode;
      if (myCachedPropertyCellNode != null && cell == myCachedPropertyCellNode.getUserObject()) {
        cellNode = myCachedPropertyCellNode;
      } else {
        cellNode = (CellNode) findCellNode(cell);
        myCachedPropertyCellNode = cellNode;
      }
      if (cellNode == null) {
        return;
      }
      cellNode.renewPresentation();
      // XXX used to be updateSubTree(), but CellNode doesn't override doUpdate, so effectively it's just
      //    TreeModel.nodeStructureChanged() notification, and update() does exactly that
      cellNode.update();
      repaint();
    }

    @Override
    public void editorUpdated(jetbrains.mps.openapi.editor.EditorComponent editorComponent) {
      rebuildLater();
    }
  }
}
