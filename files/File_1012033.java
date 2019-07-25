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
package jetbrains.mps.ide.findusages.view;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.pom.Navigatable;
import com.intellij.ui.LayeredIcon;
import jetbrains.mps.icons.MPSIcons.Nodes;
import jetbrains.mps.ide.findusages.view.treeholder.tree.DataNode;
import jetbrains.mps.ide.findusages.view.treeholder.tree.DataTree;
import jetbrains.mps.ide.findusages.view.treeholder.tree.TextOptions;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.AbstractResultNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.BaseNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.CategoryNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.ModelNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.ModuleNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.NodeNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes.ResultsNodeData;
import jetbrains.mps.ide.findusages.view.treeholder.treeview.INodeRepresentator;
import jetbrains.mps.ide.findusages.view.treeholder.treeview.path.PathItemRole;
import jetbrains.mps.ide.ui.tree.MPSTree;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.project.Project;
import jetbrains.mps.smodel.ModelReadRunnable;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.coverage.gnu.trove.THashMap;

import javax.swing.Icon;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.Font;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

// XXX is there any reason to subclass MPSTree? What do we gain by that?
public class UsagesTree extends MPSTree {
  private DataTree myContents;
  private HashSet<PathItemRole> myResultPathProvider = new HashSet<>();

  private boolean myAdditionalInfoNeeded;
  private boolean myShowSearchedNodes;
  private boolean myGroupSearchedNodes;
  private boolean myShowPopupMenu;
  private int myIsAdjusting = 0;
  private boolean myAutoscroll = false;
  private Project myProject;
  private UsagesTreeNode myResultsSubtree;
  private INodeRepresentator<?> myPresentationProvider;

  public UsagesTree(Project project) {
    myProject = project;
    myAdditionalInfoNeeded = false;

    myResultPathProvider.add(PathItemRole.ROLE_MAIN_RESULTS);
    myResultPathProvider.add(PathItemRole.ROLE_TARGET_NODE);

    setRootVisible(false);

    addTreeSelectionListener(e -> {
      if (myAutoscroll) {
        openNewlySelectedNodeLink(e, false, false);
      }
    });
    setCellRenderer(new UsagesCellRenderer(this));
  }

  // XXX don't make this method public, INodeRepresentator is awful and needs refactoring, leave it as our implementation detail for now
  /*package*/ void setPresentationProvider(INodeRepresentator<?> presentationProvider) {
    myPresentationProvider = presentationProvider;
  }

  // XXX don't make this method public, INodeRepresentator is awful and needs refactoring, leave it as our implementation detail for now
  /*package*/ INodeRepresentator<?> getPresentationProvider() {
    return myPresentationProvider;
  }

  @Override
  public boolean isDisposed() {
    return super.isDisposed() || myProject.isDisposed();
  }

  /*package*/ void startAdjusting() {
    myIsAdjusting++;
  }

  /*package*/ void finishAdjusting() {
    myIsAdjusting--;
    rebuildLater();
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
  public void rebuildNow() {
    UsagesTree.super.rebuildNow();
    for (int i = 0; i < getRootNode().getChildCount(); i++) {
      Object[] path = {getRootNode(), getRootNode().getChildAt(i)};
      TreePath treePath = new TreePath(path);
      expandPath(treePath);
    }
  }

  public void setContents(DataTree contents, Set<PathItemRole> pathProvider) {
    myContents = contents;
    myResultPathProvider.clear();
    myResultPathProvider.addAll(pathProvider);
    if (myIsAdjusting == 0) {
      rebuildLater();
    }
  }

  public void setContents(DataTree contents) {
    myContents = contents;
    if (myIsAdjusting == 0) {
      rebuildLater();
    }
  }

  public void setResultPathProvider(Set<PathItemRole> resultPathProvider) {
    myResultPathProvider.clear();
    myResultPathProvider.addAll(resultPathProvider);
    if (myIsAdjusting == 0) {
      rebuildLater();
    }
  }

  public void setAdditionalInfoNeeded(boolean additionalInfoNeeded) {
    myAdditionalInfoNeeded = additionalInfoNeeded;
    if (myIsAdjusting == 0) {
      rebuildLater();
    }
  }

  public boolean isAdditionalInfoNeeded() {
    return myAdditionalInfoNeeded;
  }

  public void setShowSearchedNodes(boolean showSearchedNodes) {
    myShowSearchedNodes = showSearchedNodes;
    if (myIsAdjusting == 0) {
      rebuildLater();
    }
  }

  public boolean isShowSearchedNodes() {
    return myShowSearchedNodes;
  }

  public void setGroupSearchedNodes(boolean groupSearchedNodes) {
    myGroupSearchedNodes = groupSearchedNodes;
    if (myIsAdjusting == 0) {
      rebuildLater();
    }
  }

  public boolean isGroupSearchedNodes() {
    return myGroupSearchedNodes;
  }

  public boolean isShowPopupMenu() {
    return myShowPopupMenu;
  }

  public void setShowPopupMenu(boolean showPopupMenu) {
    myShowPopupMenu = showPopupMenu;
  }

  @Override
  protected UsagesTreeNode rebuild() {
    myResultsSubtree = null;
    UsagesTreeNode root = new UsagesTreeNode();
    if (myContents == null || !myContents.getTreeRoot().hasChildren()) {
      // FIXME refactor UsagesTree construction so that it doesn't try to show tree before any content supplied.
      // Now the tree is rebuilt on view options change (UsagesTreeComponent#setComponentsViewOptions())
      return root;
    }
    if (myShowSearchedNodes) {
      HashSet<PathItemRole> searchedNodesPathProvider = new HashSet<>();
      searchedNodesPathProvider.add(PathItemRole.ROLE_MAIN_SEARCHED_NODES);

      BaseNodeData searchedNodesRoot = myContents.getSearchSubtree();
      if (DataTree.getNodeDataStream(searchedNodesRoot).anyMatch(NodeNodeData.class::isInstance)) {
        if (myGroupSearchedNodes) {
          searchedNodesPathProvider.add(PathItemRole.ROLE_ROOT);
          searchedNodesPathProvider.add(PathItemRole.ROLE_ROOT_TO_TARGET_NODE);
        }
        searchedNodesPathProvider.add(PathItemRole.ROLE_TARGET_NODE);
      } else if (DataTree.getNodeDataStream(searchedNodesRoot).anyMatch(ModelNodeData.class::isInstance)) {
        if (myGroupSearchedNodes) {
          searchedNodesPathProvider.add(PathItemRole.ROLE_MODULE);
        }
        searchedNodesPathProvider.add(PathItemRole.ROLE_MODEL);
      } else {
        searchedNodesPathProvider.add(PathItemRole.ROLE_MODULE);
      }
      root.add(buildTree(searchedNodesRoot, searchedNodesPathProvider));
    }
    myResultsSubtree = buildTree(myContents.getResultsSubtree(), myResultPathProvider);
    root.add(myResultsSubtree);

    return root;
  }

  public <T extends BaseNodeData> Stream<T> streamResults(Class<T> nodeDataKind, Predicate<? super T> condition) {
    if (myResultsSubtree == null) {
      return Stream.empty();
    }
    return myResultsSubtree.getNodeDataStream().filter(nodeDataKind::isInstance).map(nodeDataKind::cast).filter(condition);
  }

  //this is not recursive
  //use only for top-level nodes
  private UsagesTreeNode buildTree(BaseNodeData root, HashSet<PathItemRole> nodeCategories) {
    UsagesTreeNode child = newTreeNode(root);
    buildSubtreeStructure(child, root, nodeCategories);

    buildCounters(child);
    sortByCaption(child.getChildren(), new Comparator<UsagesTreeNode>() {
      private boolean isIgnored(UsagesTreeNode node) {
        // need to keep order of non-root nodes as they seen in an editor (see MPS-6113)
        BaseNodeData data = node.getUsageData();
        return (data instanceof NodeNodeData) && !((NodeNodeData) data).isRootNode();
      }

      @Override
      public int compare(UsagesTreeNode o1, UsagesTreeNode o2) {
        boolean i1 = isIgnored(o1);
        boolean i2 = isIgnored(o2);
        if (i1 || i2) {
          return i1 ? (i2 ? 0 : -1) : 1;
        }
        String s1 = o1.getUsageData().getCaption();
        String s2 = o2.getUsageData().getCaption();
        return s1.compareTo(s2);
      }
    });
    if (getPresentationProvider() != null) {
      // XXX INodeRepresentation may override text for certain elements, let's give it a chance
      // though this is not something I'd like to do, just can not refactor every piece of this mess at once
      // we need to keep this as long as DataTree.build() can not evaluate proper text at construction time
      // FIXME introduce default presentation provider to give category/result nodes to reflect actual counter state. Even though we buildCounters() properly
      //       we don't show them unless there's non-null presentation provider
      setUIProperties(child);
    }

    return child;
  }

  // XXX here we imply children list is actual collection of tree elements, not a copy.
  private static void sortByCaption(List<? extends UsagesTreeNode> children, Comparator<UsagesTreeNode> comparator) {
    children.sort(comparator);
    for (UsagesTreeNode child : children) {
      sortByCaption(child.getChildren(), comparator);
    }
  }

  private UsagesTreeNode newTreeNode(BaseNodeData data) {
    UsagesTreeNode node = new UsagesTreeNode(data);
    Icon icon = data.getIcon(() -> myProject.getRepository());
    if (data.isResultNode()) {
      final LayeredIcon result = new LayeredIcon(2);
      result.setIcon(icon, 0);
      result.setIcon(Nodes.UsagesResultOverlay, 1);
      node.setIcon(result);
    } else {
      node.setIcon(icon);
    }

    // XXX this code vividly illustrates the issue with BaseNodeData, which duplicates stuff otherwise kept in MPSTreeNode
    //     alternatively, it shows there's no need in MPSTreeNode, BaseNodeData could serve as tree model.
    node.setText(data.getCaption());
    node.setAdditionalText(data.getAdditionalInfo());
    return node;
  }

  private void buildSubtreeStructure(UsagesTreeNode parent, BaseNodeData data, HashSet<PathItemRole> nodeCategories) {
    LinkedList<BaseNodeData> childrenQueue = new LinkedList<>();
    data.children().forEach(childrenQueue::add);
    int i = 0;
    while (i < childrenQueue.size()) {
      final BaseNodeData child = childrenQueue.get(i);
      if (nodeCategories.contains(child.getRole()) || data.isPathTail()) {
        // regular, visible child element, just move on to the next one
        i++;
      } else {
        // we are not going to visualize this one, replace with its children
        childrenQueue.remove(i); // don't change i value!
        child.children().forEach(childrenQueue::add);
      }
    }
    // next, we me merge 'same' children (so that when e.g. a module is reported under different categories, as a result and as a path element to a result node,
    // we see not only the first module, but result node under it as well).
    // FIXME it's newTreeNode() that picks proper icon. If a data.isResultNode()==true comes once there's already a UTN for a path node, with
    //       isResultNode() == false, we won't get updated icon!
    //       Though I can introduce appropriate code into UTN, I hate the need to do it again and again (see BaseNodeData.setIsPathTail_internal() uses)
    //       This need seems to be due to categories as roots of the data tree, which I'd like to get fixed first
    THashMap<Object, UsagesTreeNode> seenChild = new THashMap<>();
    // buildSubtreeStructure, below, may be invoked for re-used parent with some children already present, don't want to duplicate these, too.
    for (UsagesTreeNode existingChild : parent.getChildren()) {
      seenChild.put(existingChild.getUsageData().getIdObject(), existingChild);
    }
    for(BaseNodeData c : childrenQueue) {
      UsagesTreeNode treeChild = seenChild.get(c.getIdObject());
      if (treeChild == null) {
        treeChild = newTreeNode(c);
        seenChild.put(c.getIdObject(), treeChild);
      }
      buildSubtreeStructure(treeChild, c, nodeCategories);
      parent.add(treeChild);
    }
  }

  private int buildCounters(UsagesTreeNode root) {
    int num = 0;
    for (UsagesTreeNode child : root.getChildren()) {
      num += buildCounters(child);
    }

    root.setSubresultsCount(num);

    if (root.getUsageData().isResultNode()) {
      num++;
    }

    return num;
  }

  // XXX makes sense only when myPresentationProvider != null;
  private void setUIProperties(UsagesTreeNode root) {
    // FIXME why do we need to do it here, why not in UsageTreeNode rendering code?
    //       we show counters if UsagesTreeNode has children, it's sort of information we can not get at construction time
    //       XXX what about renewPresentation/doUpdatePresentation() - perhaps, could utilize onAdd() event if subtree is built completely
    //       before adding to MPSTree(UsagesTree). I don't want to use renewPresentation() here as it sends out event for each node, which is too much
    if (root.getUsageData() instanceof CategoryNodeData) {
      // TextOptions arguments are from original setUIProperties()
      TextOptions to = new TextOptions(myAdditionalInfoNeeded, !root.isLeaf(), root.getSubresultsCount());
      // used to be in CategoryNodeData.getText
      // CategoryNodeData.myCategory == BaseNodeData.caption, hence getPlainText
      final String text = myPresentationProvider.getCategoryText(to, root.getUsageData().getCaption(), root.getUsageData().isResultsSection());
      if (text != null) {
        root.setText(text);
        // assume INodeRepresentator could use count in caption, if needed
        root.showCounter(false);
        // not every INodeRepresentator.getResultsText uses <strong>, but I don't care
        root.setFontStyle(Font.BOLD);
      }
    } else if (root.getUsageData() instanceof ResultsNodeData) {
      // used to be in ResultsNodeData.getText
      final String text = myPresentationProvider.getResultsText(new TextOptions(myAdditionalInfoNeeded, !root.isLeaf(), root.getSubresultsCount()));
      if (text != null) {
        root.setText(text);
        // assume INodeRepresentator could use count in caption, if needed
        root.showCounter(false);
        // not every INodeRepresentator.getResultsText uses <strong>, but I don't care
        root.setFontStyle(Font.BOLD);
      }
    }

    for (UsagesTreeNode tn : root.getChildren()) {
      setUIProperties(tn);
    }
  }

  @Override
  public UsagesTreeNode getCurrentNode() {
    return (UsagesTreeNode) super.getCurrentNode();
  }

  public UsagesTreeNode[] getCurrentNodes() {
    return getSelectedNodes(UsagesTreeNode.class, null);
  }

  /*package*/ void setCurrentNodesExclusion(boolean isExcluded) {
    Set<BaseNodeData> nodes = new HashSet<>();

    //we need to traverse UI tree nodes here as some child nodes of a UI node can correspond to non-child nodes of its data node
    for (UsagesTreeNode node : getCurrentNodes()) {
      Enumeration e = node.breadthFirstEnumeration();
      while (e.hasMoreElements()) {
        UsagesTreeNode n = ((UsagesTreeNode) e.nextElement());
        nodes.add(n.getUsageData());
      }
    }
    myContents.setExcluded(nodes, isExcluded);
  }

  /*package*/ void setCurrentNodesOnlyExclusion() {
    myContents.setExcluded(Collections.singleton(myContents.getTreeRoot()), true);
    setCurrentNodesExclusion(false);
  }

  @Override
  protected ActionGroup createPopupActionGroup(MPSTreeNode node) {
    if (myShowPopupMenu) {
      return (ActionGroup) ActionManager.getInstance().getAction("jetbrains.mps.ui.usage.tree.popup");
    }
    return null;
  }

  // FIXME please, refactor
  /*package*/ void openCurrentNodeLink(boolean inProjectIfPossible, boolean focus) {
    goByNodeLink(getCurrentNode(), inProjectIfPossible, focus);
  }

  private void openNewlySelectedNodeLink(TreeSelectionEvent e, boolean inProjectIfPossible, boolean focus) {
    TreePath path = e.getNewLeadSelectionPath();
    if (path == null) {
      return;
    }
    Object treeNode = path.getLastPathComponent();
    if (treeNode instanceof UsagesTreeNode) {
      goByNodeLink((UsagesTreeNode) treeNode, inProjectIfPossible, focus);
    }
  }

  public void setAutoscroll(boolean autoscroll) {
    myAutoscroll = autoscroll;

    if (getCurrentNode() != null) {
      goByNodeLink(getCurrentNode(), false, false);
    }
  }

  public boolean isAutoscroll() {
    return myAutoscroll;
  }

  @Override
  protected void doubleClick(@NotNull MPSTreeNode nodeToClick) {
    if (nodeToClick instanceof UsagesTreeNode) {
      final BaseNodeData ud = ((UsagesTreeNode) nodeToClick).getUsageData();
      if (ud != null && ud.isPathTail()) {
        goByNodeLink((UsagesTreeNode) nodeToClick, false, true);
      }

    } else {
      super.doubleClick(nodeToClick);
    }
  }

  private void goByNodeLink(@Nullable UsagesTreeNode treeNode, boolean inProjectIfPossible, boolean focus) {
    if (treeNode != null && treeNode.getUsageData() instanceof AbstractResultNodeData) {
      ((AbstractResultNodeData) treeNode.getUsageData()).navigate(myProject, inProjectIfPossible, focus);
    }
  }


  @Nullable
  public Navigatable toNavigatable(DefaultMutableTreeNode treeNode) {
    // FIXME this is provisional code while I refactor UsagesView to get ready for new SearchState.
    // BaseNodeData would cease and the code shall be different (no instanceof + cast).
    if (treeNode instanceof UsagesTreeNode && treeNode.getChildCount() == 0 && treeNode.getUserObject() != null) {
      UsagesTreeNode usageNode = (UsagesTreeNode) treeNode;
      final BaseNodeData data = usageNode.getUsageData();
      return toNavigatable(data);
    }
    return null;
  }

  private Navigatable toNavigatable(final BaseNodeData data) {
    if (!(data instanceof AbstractResultNodeData)) {
    return null;
  }
    return new Navigatable() {
      @Override
      public void navigate(boolean requestFocus) {
        // Show nodes directly in editor instead of project pane
        boolean useProjectTree = !(data instanceof NodeNodeData);
        if (data instanceof ModelNodeData || data instanceof ModuleNodeData) {
          // Leave focus in UsagesView or it became unusable
          requestFocus = false;
        }
        ((AbstractResultNodeData) data).navigate(myProject, useProjectTree, requestFocus);
      }

      @Override
      public boolean canNavigate() {
        return true;
      }

      @Override
      public boolean canNavigateToSource() {
        return true;
      }
    };
  }



  public static final class UsagesTreeNode extends MPSTreeNode {
    private int mySubresultsCount = 0;
    private boolean myShowCounter;

    public UsagesTreeNode() {
      setNodeIdentifier("");
    }

    public UsagesTreeNode(BaseNodeData data) {
      super(data);
      setNodeIdentifier(data.getIdObject() instanceof String ? (String) data.getIdObject() : data.getCaption());
      setToggleClickCount(data.isPathTail() ? -1 : 2);
      showCounter(data.isResultsSection());
    }

    @Override
    public List<UsagesTreeNode> getChildren() {
      return ((List) super.getChildren());
    }

    @Override
    protected void updateErrorState() {
      //disable for
    }

    @Nullable
    public BaseNodeData getUsageData() {
      return super.getUserObject() instanceof BaseNodeData ? (BaseNodeData) super.getUserObject() : null;
    }

    /*package*/ int getSubresultsCount() {
      return mySubresultsCount;
    }

    /*package*/ void setSubresultsCount(int subresultsCount) {
      mySubresultsCount = subresultsCount;
    }

    /**
     * @deprecated use {@link #getUsageData()} instead. No reason to expose our failure to build proper API and data structures (yes, DataNode hierarchy was a mistake)
     */
    @Override
    @Deprecated
    @ToRemove(version = 2019.2)
    public DataNode getUserObject() {
      return new DataNode(getUsageData());
    }


    // flatten elements associated with the node and its children, recursively.
    /*package*/ Stream<BaseNodeData> getNodeDataStream() {
      return Stream.concat(Stream.of(getUsageData()), getChildren().stream().flatMap(UsagesTreeNode::getNodeDataStream));
    }

    // provisional, to mimic legacy behavior, when some NodeData didn't show regular counter but included it into caption
    /*package*/ boolean showCounter() {
      return myShowCounter;
    }
    /*package*/ void showCounter(boolean b) {
      myShowCounter = b;
    }
  }
}
