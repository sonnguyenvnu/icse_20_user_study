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
package jetbrains.mps.ide.ui.tree;

import com.intellij.ide.dnd.aware.DnDAwareTree;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.ui.DoubleClickListener;
import com.intellij.ui.TreeUIHelper;
import com.intellij.util.ui.tree.WideSelectionTreeUI;
import com.intellij.util.ui.update.MergingUpdateQueue;
import com.intellij.util.ui.update.Update;
import jetbrains.mps.RuntimeFlags;
import jetbrains.mps.ide.ThreadUtils;
import jetbrains.mps.util.annotation.ToRemove;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.AbstractAction;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.plaf.TreeUI;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class MPSTree extends DnDAwareTree implements Disposable {
  public static final String PATH = "path";

  protected static final Logger LOG = LogManager.getLogger(MPSTree.class);

  public static final String TREE_PATH_SEPARATOR = "/";

  private int myTooltipManagerRecentInitialDelay;
  private boolean myAutoExpandEnabled = true;
  private boolean myAutoOpen = false;
  private boolean myLoadingDisabled;

  private Set<MPSTreeNode> myExpandingNodes = new HashSet<>();

  private List<MPSTreeNodeListener> myTreeNodeListeners = new ArrayList<>();

  // todo: make unique name
  private MergingUpdateQueue myQueue = new MergingUpdateQueue("MPS Tree Rebuild Later Watcher Queue", 500, true, null);
  private final Object myUpdateId = new Object();

  private boolean myDisposed = false;

  protected MPSTree() {
    // TreeModel instance shall be the same during lifetime of the MPSTree instance
    // otherwise TreeModelListener instances attached to the model get lost
    super(new DefaultTreeModel(null));

    new MPSTreeSpeedSearch(this);

    ToolTipManager.sharedInstance().registerComponent(this);

    largeModel = true;

    TreeUIHelper.getInstance().installToolTipHandler(this);

    setCellRenderer(new NewMPSTreeCellRenderer());

    addTreeWillExpandListener(new MyTreeWillExpandListener());
    addTreeExpansionListener(new MyTreeExpansionListener());

    new DoubleClickListener() {
      @Override
      protected boolean onDoubleClick(MouseEvent e) {
        MPSTreeNode nodeToClick = getOpenableNode(e);
        if (nodeToClick == null) {
          return false;
        }

        doubleClick(nodeToClick);
        return true;
      }
    }.installOn(this);

    addMouseListener(new MyMouseAdapter());
    getModel().addTreeModelListener(new TreeModelListener() {
      @Override
      public void treeNodesChanged(TreeModelEvent e) {
        Object[] changedChildren = e.getChildren();
        if (changedChildren == null) {
          return;
        }
        for (Object c : changedChildren) {
          if (c instanceof MPSTreeNode) {
            MPSTreeNode mpsTreeNode = ((MPSTreeNode) c);
            // 1. we use updatePresentation(), not renewPresentation() here on purpose. Latter is odd as it combines actual
            //    presentation update with notification dispatch, and I don't see a reason to react to node change with yet another ancestor node change
            // 2. To my best knowledge, there's no other updates for MPSTreeNode at the moment, however, hope to get structure updates piped through
            //    the queue some day, and therefore would be nice to distinguish (and even Update.canEat()) structure updates from presentation.
            // 3. Re-use of runRebuildAction is to get same model read access as during tree build. The name
            //    of the method is unfortunate, however I don't want to introduce yet another method to wrap Runnable into MA
            //    Would be great to combine RA of doInit, rebuild and presentation update into single method.
            // 4. saveExpansion == false as updatePresentation is not supposed to affect tree structure
            Runnable updateCode = () -> runRebuildAction(mpsTreeNode::updatePresentation, false);
            myQueue.queue(new SafeUpdate(new Object[]{c, "presentation"}, updateCode, LOG));
          }
        }
      }

      @Override
      public void treeNodesInserted(TreeModelEvent e) {
        // no idea yet it shall react here or not
      }

      @Override
      public void treeNodesRemoved(TreeModelEvent e) {
        // no idea yet it shall react here or not
      }

      @Override
      public void treeStructureChanged(TreeModelEvent e) {
        // no idea yet it shall react here or not
      }
    });

    registerKeyboardAction(new MyDoubleClickAction(), KeyStroke.getKeyStroke("F4"), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    registerKeyboardAction(new MyRefreshAction(), KeyStroke.getKeyStroke("F5"), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    clear();
  }

  /**
   * Initialization sequence common for each node initialized in the tree.
   * Shall invoke {@link MPSTreeNode#doInit()} to perform actual initialization, does this through appropriate runnable
   * supplied to {@link #doInit(MPSTreeNode, Runnable)}},  which is left protected for sub-classes
   * that may add extra utility stuff, like progress indication.
   * Primary responsibility is guards to prevent endless initialization
   */
  /*package*/
  final void performInit(final MPSTreeNode node) {
    assert ThreadUtils.isInEDT();
    if (myExpandingNodes.contains(node)) {
      return;
    }

    myExpandingNodes.add(node);
    try {
      doInit(node, () -> node.doInit());
    } finally {
      myExpandingNodes.remove(node);
    }
  }

  /**
   * Extra initialization stuff, by default adds progress indication (conditional, myLoadingDisabled and node.isLoadingEnabled).
   * Sub-classes may override to provide extra initialization stuff (shall invoke nodeInitRunnable if do not delegate to super)
   * or to wrap nodeInitRunnable into proper access code (e.g. model read if tree node initialization might demand access to model).
   * <p>
   * Note, if tree nodes do not initialize lazily with access to a data model, there's no need to override the method and to wrap nodeInitRunnable with
   * data model access control (e.g. model read)
   */
  protected void doInit(MPSTreeNode node, Runnable nodeInitRunnable) {
    TextTreeNode progressNode = null;
    if (!myLoadingDisabled && node.isLoadingEnabled()) {
      progressNode = new TextTreeNode("loading...");
      node.add(progressNode);
      getModel().nodeStructureChanged(node);
      expandPath(new TreePath(progressNode.getPath()));

      Graphics g = getGraphics();
      if (g != null && g.getClipBounds() != null) {
        paint(g);
      }
    }

    nodeInitRunnable.run();

    if (!myLoadingDisabled && node.isLoadingEnabled() && node.hasChild(progressNode)) {
      // node initialization code (nodeInitRunnable, node.doInit()) may remove all the children
      node.remove(progressNode);
    }

    // initialization of a node is supposed to update its children, notify structure had likely changed
    getModel().nodeStructureChanged(node);
  }

  public void addTreeNodeListener(MPSTreeNodeListener listener) {
    myTreeNodeListeners.add(listener);
  }

  public void removeTreeNodeListener(MPSTreeNodeListener listener) {
    myTreeNodeListeners.remove(listener);
  }

  public void fireBeforeTreeDisposed() {
    for (MPSTreeNodeListener listener : new HashSet<>(myTreeNodeListeners)) {
      listener.beforeTreeDisposed(this);
    }
  }

  void fireTreeNodeUpdated(MPSTreeNode node) {
    for (MPSTreeNodeListener listener : new HashSet<>(myTreeNodeListeners)) {
      listener.treeNodeUpdated(node, this);
    }
  }

  void fireTreeNodeAdded(MPSTreeNode node) {
    for (MPSTreeNodeListener listener : new HashSet<>(myTreeNodeListeners)) {
      listener.treeNodeAdded(node, this);
    }
  }

  void fireTreeNodeRemoved(MPSTreeNode node) {
    for (MPSTreeNodeListener listener : new HashSet<>(myTreeNodeListeners)) {
      listener.treeNodeRemoved(node, this);
    }
  }

  void myMouseReleased(MouseEvent e) {
    if (e.isPopupTrigger()) {
      showPopup(e.getX(), e.getY(), e);
    }
  }

  void myMousePressed(final MouseEvent e) {
    IdeFocusManager.findInstanceByComponent(this).requestFocus(this, true);

    if (e.getButton() == 0) {
      // This is a workaround for handling context menu button
      TreePath path = getSelectionPath();
      if (path == null) {
        return;
      }
      int rowNum = getRowForPath(path);
      Rectangle r = getRowBounds(rowNum);
      showPopup(r.x, r.y, e);
      return;
    }

    if (e.isPopupTrigger()) {
      showPopup(e.getX(), e.getY(), e);
      return;
    }

    TreePath path = getClosestPathForLocation(e.getX(), e.getY());
    if (path == null) {
      return;
    }

    MPSTreeNode nodeToClick = getOpenableNode(e);
    if (nodeToClick != null) {
      if ((e.getClickCount() == 1 && isAutoOpen())) {
        autoscroll(nodeToClick);
      }
    }
  }

  private void myMouseClicked(MouseEvent e) {
    if (e.isPopupTrigger()) {
      showPopup(e.getX(), e.getY(), e);
    }
  }

  private MPSTreeNode getOpenableNode(MouseEvent e) {
    MPSTreeNode node = getNodeFromPath(getClosestPathForLocation(e.getX(), e.getY()));

    if (node == null) {
      return null;
    }
    if (!node.canBeOpened()) {
      return null;
    }

    return node;
  }

  @Nullable
  private MPSTreeNode getNodeFromPath(@Nullable TreePath path) {
    if (path == null) {
      return null;
    }
    Object lastPathComponent = path.getLastPathComponent();

    if (!(lastPathComponent instanceof MPSTreeNode)) {
      return null;
    }
    return (MPSTreeNode) lastPathComponent;
  }

  /**
   * Gives owning tree a chance to process double-click event.
   * By default, delegates to {@link MPSTreeNode#doubleClick()}
   */
  protected void doubleClick(@NotNull MPSTreeNode nodeToClick) {
    nodeToClick.doubleClick();
  }

  /**
   * Single point to dispatch auto scrolling event.
   * By default, delegates to {@link MPSTreeNode#autoscroll()} ()}
   */
  protected void autoscroll(@NotNull MPSTreeNode nodeToClick) {
    nodeToClick.autoscroll();
  }

  // if we navigate to a node which is MPSTreeNode#isAutoExpandable() == true,
  // it's automatically expanded, unless myAutoExpandEnabled == false
  // XXX is there any case but select node in the runnable? Perhaps, could
  // get better api (e.g. selectWithoutExpansion(MPSTreeNode)?)
  public void runWithoutExpansion(Runnable r) {
    try {
      myAutoExpandEnabled = false;
      r.run();
    } finally {
      myAutoExpandEnabled = true;
    }
  }

  public boolean isAutoOpen() {
    return myAutoOpen;
  }

  public void setAutoOpen(boolean autoOpen) {
    myAutoOpen = autoOpen;
  }

  @Override
  public String getToolTipText(MouseEvent event) {
    TreePath path = getPathForLocation(event.getX(), event.getY());
    MPSTreeNode node = getNodeFromPath(path);
    if (node != null) {
      return node.getTooltipText();
    }
    return null;
  }

  protected JPopupMenu createDefaultPopupMenu() {
    return null;
  }

  protected final JPopupMenu createPopupMenu(final MPSTreeNode node) {
    ActionGroup actionGroup = createPopupActionGroup(node);
    if (actionGroup == null) {
      return null;
    }
    return ActionManager.getInstance().createActionPopupMenu(getPopupMenuPlace(), actionGroup).getComponent();
  }

  protected String getPopupMenuPlace() {
    return ActionPlaces.UNKNOWN;
  }

  protected ActionGroup createPopupActionGroup(final MPSTreeNode node) {
    return null;
  }

  private boolean insideTreeItemsArea(int y) {
    Rectangle rowBounds = getRowBounds(getRowCount() - 1);
    if (rowBounds == null) {
      return false;
    }
    double lastItemBottomLine = rowBounds.getMaxY();
    return y <= lastItemBottomLine;
  }

  private boolean isWideSelectionUI() {
    TreeUI ui = getUI();
    return ui instanceof WideSelectionTreeUI && ((WideSelectionTreeUI) ui).isWideSelection();
  }

  private void showPopup(int x, int y, MouseEvent e) {
    if (!insideTreeItemsArea(y)) {
      return;
    }

    TreePath closestPath = getClosestPathForLocation(x, y);
    TreePath path;
    if (isWideSelectionUI()) {
      path = closestPath;
    } else {
      path = getPathForLocation(x, y);
    }

    // Always select the closest path, even if the tree does not have wide selection, or the node doesn't have a menu.
    if (closestPath != null && !isPathSelected(closestPath)) {
      setSelectionPath(path);
    }

    final MPSTreeNode node = getNodeFromPath(path);
    if (node != null) {
      JPopupMenu menu = createPopupMenu(node);
      if (menu != null) {
        menu.show(this, x, y);
        e.consume();
        return;
      }
    }

    JPopupMenu defaultMenu = createDefaultPopupMenu();
    if (defaultMenu == null) {
      return;
    }
    defaultMenu.show(this, x, y);
    e.consume();
  }

  /**
   * @deprecated Poor contract (despite being in MPSTree context, the comparator is expected to sort user objects, not MPSTreeNode), single use
   */
  @Nullable
  @Deprecated
  @ToRemove(version = 2019.1)
  public Comparator<Object> getChildrenComparator() {
    return null;
  }

  protected abstract MPSTreeNode rebuild();

  public void expandAll() {
    MPSTreeNode node = getRootNode();
    expandAll(node);
  }

  public void collapseAll() {
    MPSTreeNode node = getRootNode();
    collapseAll(node);
  }

  public void selectFirstLeaf() {
    List<MPSTreeNode> path = new ArrayList<>();
    MPSTreeNode current = getRootNode();

    while (true) {
      path.add(current);
      if (current.getChildCount() == 0) {
        break;
      }
      current = (MPSTreeNode) current.getChildAt(0);
    }

    setSelectionPath(new TreePath(path.toArray()));
  }

  public void expandRoot() {
    expandPath(new TreePath(new Object[]{getRootNode()}));
    getRootNode().init();
  }

  public void expandAll(MPSTreeNode node) {
    boolean wasLoadingDisabled = myLoadingDisabled;
    myLoadingDisabled = true;
    try {
      expandAllImpl(node);
    } finally {
      myLoadingDisabled = wasLoadingDisabled;
    }
  }

  private void expandAllImpl(MPSTreeNode node) {
    expandPath(new TreePath(node.getPath()));
    for (MPSTreeNode c : node.getChildren()) {
      expandAllImpl(c);
    }
  }

  public void collapseAll(MPSTreeNode node) {
    boolean wasAutoExpandEnabled = myAutoExpandEnabled;
    try {
      myAutoExpandEnabled = false;
      collapseAllImpl(node);
    } finally {
      myAutoExpandEnabled = wasAutoExpandEnabled;
    }
  }

  private void collapseAllImpl(MPSTreeNode node) {
    for (MPSTreeNode c : node.getChildren()) {
      collapseAllImpl(c);
    }
    if (node.isInitialized()) {
      super.collapsePath(new TreePath(node.getPath()));
    }
  }

  public void selectNode(TreeNode node) {
    List<TreeNode> nodes = new ArrayList<>();
    while (node != null) {
      nodes.add(0, node);
      node = node.getParent();
    }
    if (nodes.size() == 0) {
      return;
    }
    TreePath path = new TreePath(nodes.toArray());
    setSelectionPath(path);
    scrollRowToVisible(getRowForPath(path));
  }

  /**
   * An extension mechanism to control data model access (e.g. model read) when creating a tree.
   * If you need need model read during rebuild, override with {@code super.runRebuildAction(new ModelReadRunnable(properModelAccess, rebildAction), saveExpansion);}
   * FIXME Besides, controls whether active expansion paths are preserved, although there are only 2 uses of the method, and both specify {@code true}.
   * <p/>
   * During rebuild action, nodes are crteated without 'Loading...' placeholder even if they clain they'd like it.
   * <p/>
   * {@code rebuildAction()} is sort of implementation detail when there are {@link #rebuildNow()} and {@link #rebuildLater()}, therefore is protected.
   * There's little value in the method, though, perhaps will get merged into rebuildNow() eventually (with smth like
   * {@code protected Runnable wrapDataAccess(Runnable)} to facilitate model read wrap.
   * <p/>
   * This method expects EDT thread.
   *
   * @param rebuildAction code that rebuilds a tree (or part thereof)
   * @param saveExpansion {@code true} to indicate expanded path and selection is preserved
   */
  protected void runRebuildAction(final Runnable rebuildAction, final boolean saveExpansion) {
    if (RuntimeFlags.isTestMode()) {
      return;
    }
    if (!ThreadUtils.isInEDT()) {
      throw new RuntimeException("Rebuild now can be only called from UI thread");
    }


    myLoadingDisabled = true;
    try {
      Runnable restoreExpansion = null;
      if (saveExpansion) {
        final List<String> expansion = getExpandedPaths();
        final List<String> selection = getSelectedPaths();
        restoreExpansion = () -> {
          expandPaths(expansion);
          selectPaths(selection);
        };
      }
      rebuildAction.run();
      if (restoreExpansion != null) {
        runWithoutExpansion(restoreExpansion);
      }
    } finally {
      myLoadingDisabled = false;
    }
  }

  // XXX Rename to scheduleRebuild()?
  public void rebuildLater() {
    myQueue.queue(new SafeUpdate(myUpdateId, () -> {
      // myQueue is attached to EDT
      if (MPSTree.this.isDisposed()) {
        return;
      }
      rebuildNow();
    }, LOG));
  }

  public void rebuildNow() {
    if (!ThreadUtils.isInEDT()) {
      throw new RuntimeException("Rebuild now can be only called from UI thread");
    }
    assert !isDisposed() : "Trying to reconstruct disposed tree. Try finding \"later\" in stacktrace";

    runRebuildAction(() -> {
      setAnchorSelectionPath(null);
      setLeadSelectionPath(null);

      MPSTreeNode root = rebuild();
      setRootNode(root);
    }, true);
  }

  public void clear() {
    setRootNode(new TextTreeNode("Empty"));
  }

  @Override
  public DefaultTreeModel getModel() {
    // we explicitly set DefaultTreeModel during construction of MPSTree,
    // this method serves the purpose of convenient cast and as a reminder not to change
    // TreeModel during lifecycle of MPSTree as it used to be. Same TreeModel instance is important
    // to keep set of listeners attached to the tree model.
    return (DefaultTreeModel) super.getModel();
  }

  private void setRootNode(@Nullable MPSTreeNode root) {
    final Object oldRoot = getModel().getRoot();
    if (oldRoot instanceof MPSTreeNode) {
      ((MPSTreeNode) oldRoot).removeThisAndChildren();
      ((MPSTreeNode) oldRoot).setTree(null);
    }

    if (root != null) {
      root.setTree(this);
      root.addThisAndChildren();
    }

    getModel().setRoot(root);
  }

  private String pathToString(TreePath path) {
    StringBuilder result = new StringBuilder();
    for (int i = 1; i < path.getPathCount(); i++) {
      MPSTreeNode node = (MPSTreeNode) path.getPathComponent(i);
      result.append(TREE_PATH_SEPARATOR);
      result.append(node.getNodeIdentifier().replaceAll(TREE_PATH_SEPARATOR, "-"));
    }
    if (result.length() == 0) {
      return TREE_PATH_SEPARATOR;
    }
    return result.toString();
  }

  public TreeNode findNodeWith(Object userObject) {
    MPSTreeNode root = getRootNode();
    return findNodeWith(root, userObject);
  }

  public MPSTreeNode getRootNode() {
    return (MPSTreeNode) getModel().getRoot();
  }

  public MPSTreeNode getCurrentNode() {
    return getNodeFromPath(getLeadSelectionPath());
  }

  public void setCurrentNode(@NotNull MPSTreeNode node) {
    TreePath path = new TreePath(node.getPath());
    setSelectionPath(path);
    this.scrollPathToVisible(path);
  }

  private MPSTreeNode findNodeWith(MPSTreeNode root, Object userObject) {
    if (Objects.equals(root.getUserObject(), userObject)) {
      return root;
    }
    if (!(root.isInitialized() || root.hasInfiniteSubtree())) {
      root.init();
    }
    for (MPSTreeNode child : root.getChildren()) {
      MPSTreeNode result = findNodeWith(child, userObject);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  private TreePath listToPath(List<String> pathComponents) {
    return getTreePath(pathComponents, false);
  }

  private TreePath stringToPath(String pathString) {
    List<String> components = Arrays.asList(pathString.split(TREE_PATH_SEPARATOR));

    return getTreePath(components, true);
  }

  @Nullable
  private TreePath getTreePath(List<String> components, boolean escapePathSep) {
    List<Object> path = new ArrayList<>();
    MPSTreeNode current = getRootNode();

    current.init();

    path.add(current);

    for (Iterator<String> it = components.iterator(); it.hasNext(); ) {
      String component = it.next();
      assert current.isInitialized() : String.format("For tree path %s at element %s parent node %s(%s) is not initialized", components, component, current,
                                                     current.getClass());
      if (component == null || component.length() == 0) {
        continue;
      }
      boolean found = false;
      for (int i = 0; i < current.getChildCount(); i++) {
        MPSTreeNode node = (MPSTreeNode) current.getChildAt(i);
        String treeNodeId =
            escapePathSep ? node.getNodeIdentifier().replaceAll(TREE_PATH_SEPARATOR, "-") :
            node.getNodeIdentifier();
        if (treeNodeId.equals(component)) {
          current = node;
          path.add(current);
          if (!current.isInitialized() && it.hasNext()) {
            current.init();
          }
          found = true;
          break;
        }
      }
      if (!found) {
        return null;
      }
    }
    return new TreePath(path.toArray());
  }

  protected void expandPathsRaw(List<List<String>> paths) {
    for (List<String> path : paths) {
      TreePath treePath = listToPath(path);
      if (treePath != null) {
        ensurePathInitialized(treePath);
        expandPath(treePath);
      }
    }
  }

  protected void expandPaths(List<String> paths) {
    for (String path : paths) {
      TreePath treePath = stringToPath(path);
      if (treePath != null) {
        ensurePathInitialized(treePath);
        expandPath(treePath);
      }
    }
  }

  private void ensurePathInitialized(TreePath path) {
    for (Object item : path.getPath()) {
      MPSTreeNode node = (MPSTreeNode) item;
      node.init();
    }
  }

  protected void selectPaths(List<String> paths) {
    List<TreePath> treePaths = new ArrayList<>();
    for (String path : paths) {
      treePaths.add(stringToPath(path));
    }
    setSelectionPaths(treePaths.toArray(new TreePath[0]));
  }

  protected void selectPathsRaw(List<List<String>> paths) {
    List<TreePath> treePaths = new ArrayList<>();
    for (List<String> path : paths) {
      treePaths.add(listToPath(path));
    }
    setSelectionPaths(treePaths.toArray(new TreePath[0]));
  }

  // TODO: refactor TreeState to include these instead of the old format
  public List<List<String>> getExpandedPathsRaw() {
    List<List<String>> result = new ArrayList<>();
    Enumeration<TreePath> expanded = getExpandedDescendants(new TreePath(new Object[]{getModel().getRoot()}));
    if (expanded == null) {
      return result;
    }
    while (expanded.hasMoreElements()) {
      List<String> path = new ArrayList<>();
      TreePath expandedPath = expanded.nextElement();
      if (expandedPath.getLastPathComponent() == getModel().getRoot()) {
        continue;
      }
      for (int i = 1; i < expandedPath.getPathCount(); i++) {
        MPSTreeNode node = (MPSTreeNode) expandedPath.getPathComponent(i);
        path.add(node.getNodeIdentifier());
      }
      result.add(path);
    }
    return result;
  }

  private List<String> getExpandedPaths() {
    List<String> result = new ArrayList<>();
    Enumeration<TreePath> expanded = getExpandedDescendants(new TreePath(new Object[]{getModel().getRoot()}));
    if (expanded == null) {
      return result;
    }
    while (expanded.hasMoreElements()) {
      TreePath path = expanded.nextElement();
      String pathString = pathToString(path);
      if (result.contains(pathString)) {
        LOG.warn("two expanded paths have the same string representation");
      }
      result.add(pathString);
    }
    return result;
  }

  // TODO: refactor TreeState to include these instead of the old format
  public List<List<String>> getSelectedPathsRaw() {
    List<List<String>> result = new ArrayList<>();
    if (getSelectionPaths() == null) {
      return result;
    }
    for (TreePath selectedPath : getSelectionPaths()) {
      List<String> path = new ArrayList<>();
      for (int i = 1; i < selectedPath.getPathCount(); i++) {
        MPSTreeNode node = (MPSTreeNode) selectedPath.getPathComponent(i);
        path.add(node.getNodeIdentifier());
      }
      result.add(path);
    }
    return result;
  }

  private List<String> getSelectedPaths() {
    List<String> result = new ArrayList<>();
    if (getSelectionPaths() == null) {
      return result;
    }
    for (TreePath selectionPart : getSelectionPaths()) {
      String pathString = pathToString(selectionPart);
      if (result.contains(pathString)) {
        LOG.warn("two selected paths have the same string representation");
      }
      result.add(pathString);
    }
    return result;
  }

  public TreeState saveState() {
    TreeState result = new TreeState();
    result.myExpansion.addAll(getExpandedPaths());
    result.mySelection.addAll(getSelectedPaths());
    return result;
  }

  public void loadState(TreeState state) {
    selectPaths(state.mySelection);
    expandPaths(state.myExpansion);
  }

  // TODO: refactor TreeState to include these instead of the old format
  public void loadState(List<List<String>> expandedPaths, List<List<String>> selectedPaths) {
    expandPathsRaw(expandedPaths);
    selectPathsRaw(selectedPaths);
  }

  @Override
  public int getToggleClickCount() {
    MPSTreeNode node = getNodeFromPath(getSelectionPath());
    if (node == null) {
      return -1;
    }
    return node.getToggleClickCount();
  }

  public boolean isDisposed() {
    return myDisposed;
  }

  @Override
  public void dispose() {
    assert !myDisposed;

    fireBeforeTreeDisposed();
    myDisposed = true;
    setRootNode(null);
    myTreeNodeListeners.clear();
  }

  public static class TreeState {
    private List<String> myExpansion = new ArrayList<>();
    private List<String> mySelection = new ArrayList<>();

    public List<String> getExpansion() {
      return myExpansion;
    }

    public void setExpansion(List<String> expansion) {
      myExpansion = expansion;
    }

    public List<String> getSelection() {
      return mySelection;
    }

    public void setSelection(List<String> selection) {
      mySelection = selection;
    }
  }

  private class MyTreeWillExpandListener implements TreeWillExpandListener {
    @Override
    public void treeWillExpand(TreeExpansionEvent event) {
      MPSTreeNode treeNode = getNodeFromPath(event.getPath());
      if (treeNode != null) {
        treeNode.init();
      }
    }

    @Override
    public void treeWillCollapse(TreeExpansionEvent event) {
    }
  }

  private class MyTreeExpansionListener implements TreeExpansionListener {
    @Override
    public void treeExpanded(TreeExpansionEvent event) {
      if (!myAutoExpandEnabled) {
        return;
      }

      TreePath eventPath = event.getPath();
      MPSTreeNode node = getNodeFromPath(eventPath);

      if (node != null && node.getChildCount() == 1) {
        List<MPSTreeNode> path = new ArrayList<>();
        for (Object item : eventPath.getPath()) {
          path.add((MPSTreeNode) item);
        }
        MPSTreeNode onlyChild = (MPSTreeNode) node.getChildAt(0);

        if (onlyChild.isAutoExpandable()) {
          path.add(onlyChild);
          expandPath(new TreePath(path.toArray()));
        }
      }
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent event) {
    }
  }

  private class MyMouseAdapter extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
      myMousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      myMouseReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      myMouseClicked(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      myTooltipManagerRecentInitialDelay = ToolTipManager.sharedInstance().getInitialDelay();
      ToolTipManager.sharedInstance().setInitialDelay(10);
    }

    @Override
    public void mouseExited(MouseEvent e) {
      ToolTipManager.sharedInstance().setInitialDelay(myTooltipManagerRecentInitialDelay);
    }
  }

  private class MyDoubleClickAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
      MPSTreeNode selNode = getNodeFromPath(getSelectionPath());
      if (selNode == null) {
        return;
      }

      doubleClick(selNode);
    }
  }

  private class MyRefreshAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
      rebuildNow();
    }
  }

  // XXX this class is worth re-use
  private static final class SafeUpdate extends Update {
    private final Runnable myDelegate;
    @Nullable
    private Logger myLogger;
    private Exception myException;

    public SafeUpdate(@NotNull Object updateId, @NotNull Runnable updateCode, @Nullable Logger logger) {
      super(updateId);
      myDelegate = updateCode;
      myLogger = logger;
    }

    @Override
    public void run() {
      try {
        myDelegate.run();
      } catch (Exception ex) {
        if (myLogger != null) {
          myLogger.error("Update failed", ex);
        }
        myException = ex;
      }
    }

    @Nullable
    public Exception getException() {
      return myException;
    }
  }
}
