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
package jetbrains.mps.ide.bookmark;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnActionEvent;
import jetbrains.mps.ide.bookmark.BookmarkManager.BookmarkListener;
import jetbrains.mps.ide.icons.IdeIcons;
import jetbrains.mps.ide.ui.tree.MPSTree;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.ide.ui.tree.TextTreeNode;
import jetbrains.mps.ide.ui.tree.smodel.NodeTargetProvider;
import jetbrains.mps.ide.ui.tree.smodel.SNodeTreeNode;
import jetbrains.mps.openapi.navigation.EditorNavigator;
import jetbrains.mps.project.Project;
import jetbrains.mps.smodel.ModelReadRunnable;
import jetbrains.mps.workbench.action.ActionUtils;
import jetbrains.mps.workbench.action.BaseAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.List;
import java.util.Map;

public class BookmarksTree extends MPSTree {
  private final BookmarkManager myBookmarkManager;
  private final Project myProject;

  public BookmarksTree(Project project, BookmarkManager bookmarkManager) {
    myBookmarkManager = bookmarkManager;
    myProject = project;

    myBookmarkManager.addBookmarkListener(new BookmarkListener() {
      @Override
      public void bookmarkAdded(int number, SNode node) {
        rebuildBookmarksTree();
      }

      @Override
      public void bookmarkRemoved(int number, SNode node) {
        rebuildBookmarksTree();
      }

      private void rebuildBookmarksTree() {
        BookmarksTree.this.rebuildLater();
      }
    });
  }

  @Override
  protected void doInit(MPSTreeNode node, Runnable nodeInitRunnable) {
    super.doInit(node, new ModelReadRunnable(myProject.getModelAccess(), nodeInitRunnable));
  }

  @Override
  protected void runRebuildAction(Runnable rebuildAction, boolean saveExpansion) {
    super.runRebuildAction(new ModelReadRunnable(myProject.getModelAccess(), rebuildAction), saveExpansion);
  }

  @Override
  protected ActionGroup createPopupActionGroup(final MPSTreeNode node) {
    if (node instanceof BookmarkNode) {
      BaseAction action = new BaseAction("Remove Bookmark") {
        @Override
        protected void doExecute(AnActionEvent e, Map<String, Object> _params) {
          ((BookmarkNode) node).removeBookmark();
        }
      };
      return ActionUtils.groupFromActions(action);
    } else if(!(node instanceof SNodeTreeNode)){
      BaseAction hierarchyAction = new BaseAction("Remove All Bookmarks") {
        @Override
        protected void doExecute(AnActionEvent e, Map<String, Object> _params) {
          myBookmarkManager.clearBookmarks();
        }
      };
      return ActionUtils.groupFromActions(hierarchyAction);
    }
    return null;
  }

  @Override
  protected MPSTreeNode rebuild() {
    MPSTreeNode root = new TextTreeNode("no bookmarks");
    root.setIcon(IdeIcons.DEFAULT_ICON);
    List<SNodeReference> nodePointers = myBookmarkManager.getAllNumberedBookmarks();
    boolean hasBookmarks = false;
    for (int i = 0; i < nodePointers.size(); i++) {
      final SNodeReference nodePointer = nodePointers.get(i);
      if (nodePointer != null && nodePointer.resolve(myProject.getRepository()) != null) {
        hasBookmarks = true;
        TextTreeNode textTreeNode = new MyTextTreeNodeNumbered(i);
        textTreeNode.setIcon(BookmarkManager.getIcon(i));
        textTreeNode.add(new SNodeTreeNode(nodePointer.resolve(myProject.getRepository())));
        root.add(textTreeNode);
      }
    }
    nodePointers = myBookmarkManager.getAllUnnumberedBookmarks();
    for (SNodeReference nodePointer : nodePointers) {
      if (nodePointer != null && nodePointer.resolve(myProject.getRepository()) != null) {
        hasBookmarks = true;
        TextTreeNode textTreeNode = new MyTextTreeNodeUnnumbered(nodePointer);
        textTreeNode.setIcon(BookmarkManager.getIcon(-1));
        textTreeNode.add(new SNodeTreeNode(nodePointer.resolve(myProject.getRepository())));
        root.add(textTreeNode);
      }
    }
    if (hasBookmarks) {
      root.setText("bookmarks");
    }
    return root;
  }

  public void gotoSelectedBookmark() {
    final BookmarkNode node = getSelectedBookmarkNode();
    if (node != null) {
      node.navigateToBookmark();
    }
  }

  public void removeSelectedBookmark() {
    BookmarkNode node = getSelectedBookmarkNode();
    if (node != null) {
      node.removeBookmark();
    }
  }

  private BookmarkNode getSelectedBookmarkNode() {
    MPSTreeNode selectedNode = (MPSTreeNode) getSelectionPath().getLastPathComponent();
    while (selectedNode != null) {
      if (selectedNode instanceof BookmarkNode) {
        return (BookmarkNode) selectedNode;
      }
      selectedNode = (MPSTreeNode) selectedNode.getParent();
    }
    return null;
  }

  @Override
  protected void doubleClick(@NotNull MPSTreeNode nodeToClick) {
    if (nodeToClick instanceof NodeTargetProvider) {
      final SNodeReference navigationTarget = ((NodeTargetProvider) nodeToClick).getNavigationTarget();
      if (navigationTarget != null) {
        new EditorNavigator(myProject).shallFocus(true).selectIfChild().open(navigationTarget);
        return;
      }
      // fall-through
    }
    super.doubleClick(nodeToClick);
  }

  private interface BookmarkNode {
    void navigateToBookmark();

    void removeBookmark();
  }

  private class MyTextTreeNodeNumbered extends TextTreeNode implements BookmarkNode {
    int myNumber;

    public MyTextTreeNodeNumbered(int i) {
      super("bookmark " + i);
      myNumber = i;
      setNodeIdentifier("bookmark" + i);
    }

    @Override
    public void removeBookmark() {
      myBookmarkManager.removeBookmark(myNumber);
    }

    @Override
    public void navigateToBookmark() {
      myBookmarkManager.navigateToBookmark(myNumber);
    }
  }

  private class MyTextTreeNodeUnnumbered extends TextTreeNode implements BookmarkNode {
    SNodeReference myNodePointer;

    @Override
    public void removeBookmark() {
      myBookmarkManager.removeUnnumberedBookmark(myNodePointer);
    }

    public MyTextTreeNodeUnnumbered(SNodeReference nodePointer) {
      super("bookmark");
      myNodePointer = nodePointer;
      setNodeIdentifier("bookmark_" + nodePointer.toString());
    }

    @Override
    public void navigateToBookmark() {
      new EditorNavigator(myProject).shallFocus(true).shallSelect(true).open(myNodePointer);
    }
  }
}
