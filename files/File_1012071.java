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

import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import jetbrains.mps.util.annotation.ToRemove;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Kostik
 */
//todo[MM]: unimplement iterable after 2019.1, use getChildren()
public class MPSTreeNode extends DefaultMutableTreeNode implements Iterable<MPSTreeNode> {
  private static final Logger LOG = LogManager.getLogger(MPSTreeNode.class);

  private MPSTree myTree;
  private boolean myAdded;

  private Icon myIcon = AllIcons.Nodes.Folder;
  private String myNodeIdentifier;
  private String myText;
  private String myAdditionalText = null;
  private String myTooltipText;
  private Color myColor = EditorColorsManager.getInstance().getGlobalScheme().getColor(ColorKey.createColorKey("FILESTATUS_NOT_CHANGED"));
  // initialized once with the value of myColor the moment node is created, to facilitate nodes with pre-defined colors (initialized in cons)
  // which sometimes is overridden with colors coming from extra messages (we need to revert to 'normal' color the moment all messages that have
  // altered the color are gone).
  private Color myDefaultColor;
  private int myFontStyle = Font.PLAIN;
  private boolean myAutoExpandable = true;
  private ErrorState myErrorState = ErrorState.NONE;
  private ErrorState myCombinedErrorState = ErrorState.NONE;
  // it seems cheaper to use copy-on-write list than to keep distinct synchronization object in all nodes (most of which don't use extra messages)
  // Once initialized, doesn't ever become null
  private CopyOnWriteArrayList<TreeMessage> myTreeMessages = null;
  private Map<TextAttribute, Object> myFontAttributes;
  private int myToggleClickCount = 2;

  public MPSTreeNode() {
    super(null);
  }

  public MPSTreeNode(Object userObject) {
    super(userObject);
  }

  @NotNull
  @Override
  @SuppressWarnings("unchecked")
  @Deprecated
  @ToRemove(version = 2019.1)
  public Iterator<MPSTreeNode> iterator() {
    if (children == null) {
      return Collections.<MPSTreeNode>emptySet().iterator();
    }
    return (Iterator)children.iterator();
  }

  public List<? extends MPSTreeNode> getChildren() {
    return children == null ? Collections.emptyList() : ((List) children);
  }

  public MPSTree getTree() {
    if (myTree == null && getParent() instanceof MPSTreeNode) {
      return ((MPSTreeNode) getParent()).getTree();
    }
    return myTree;
  }

  /**
   * returns the closest ancestor (nodes or the containing tree) which implements the given class
   */
  public <AT> AT getAncestor(@NotNull Class<AT> cls) {
    TreeNode p = getParent();
    while (p != null) {
      if (cls.isInstance(p)) {
        return cls.cast(p);
      }
      p = p.getParent();
    }
    if (cls.isInstance(myTree)) {
      return cls.cast(myTree);
    }
    return null;
  }

  public void setTree(MPSTree tree) {
    myTree = tree;
  }

  public boolean isInitialized() {
    return true;
  }

  public boolean hasInfiniteSubtree() {
    return false;
  }

  public void doubleClick() {
  }

  protected void onRemove() {
    getTree().fireTreeNodeRemoved(this);
  }

  protected void onAdd() {
    updatePresentation();
    getTree().fireTreeNodeAdded(this);
  }

  /**
   * Deemed for tree clients to ensure node is initialized (i.e. has its children).
   * If the node is already {@link #isInitialized() initialized}, does nothing.
   * Otherwise, {@link jetbrains.mps.ide.ui.tree.MPSTree#performInit(MPSTreeNode)}  delegates} to owning tree, if any,
   * to perform actual initialization, with respect to tree's considerations (e.g. might wrap with model read action or
   * "Loading..."  notification nodes).
   * Tree shall call {@link #doInit()} at some point where actual node initialization takes place.
   * Although not final, extra care should be taken if overriding (FIXME perhaps, shall make final, and move isInitialized field here as well).
   */
  public void init() {
    if (isInitialized()) {
      return;
    }
    MPSTree tree = getTree();
    if (tree != null) {
      tree.performInit(this);
    } else {
      doInit();
    }
    if (myDefaultColor == null) {
      // in case subclasses have specified color during construction or in their doInit(), record it as default value to reset to at each visual update
      // see #doUpdatePresentation()
      myDefaultColor = myColor;
    }
  }

  /**
   * This method shall not be invoked by code outside of MPSTree framework.
   * Subclasses shall override and perform actual node initialization here.
   * Default implementation does nothing, subclasses don't need to invoke <code>super.doInit()</code>
   */
  protected void doInit() {
  }

  public void updateSubTree() {
    getTree().runRebuildAction(this::update, true);
  }

  /**
   * Unlike {@link #updatePresentation()}, focus on node modifications that involve structure change.
   * Dispatches a tree model notification to reflect structure change of a subtree rooted at this node.
   * Override {@link #doUpdate()} to perform actual modification of a subtree
   */
  public void update() {
    doUpdate();
    getTree().getModel().nodeStructureChanged(this);
  }

  protected void doUpdate() {
  }

  @Override
  public void remove(int childIndex) {
    if (myAdded && getTree() != null && !getTree().isDisposed()) {
      ((MPSTreeNode) getChildAt(childIndex)).removeThisAndChildren();
    }
    super.remove(childIndex);
    updateErrorState();
  }


  @Override
  public void insert(MutableTreeNode newChild, int childIndex) {
    super.insert(newChild, childIndex);
    if (myAdded && getTree() != null && !getTree().isDisposed()) {
      ((MPSTreeNode) getChildAt(childIndex)).addThisAndChildren();
    }
    updateErrorState();
  }

  public boolean hasChild(MutableTreeNode node) {
    for (int i = 0; i < getChildCount(); i++) {
      if (getChildAt(i) == node) {
        return true;
      }
    }
    return false;
  }

  /**
   * This method (along with {@link #addThisAndChildren()} counterpart) is to dispatch {@link #onRemove()} notification for each element
   * of a subtree removed. As the removed subtree may get later re-attached in a different location, this method shall not clear parent/child
   * relations.
   */
  final void removeThisAndChildren() {
    if (!myAdded) {
      throw new IllegalStateException(
          String.format("Trying to remove tree node which have not been added, tree=%s, node=%s",
                        myTree != null ? myTree.getClass().getName() : "null", getClass().getName()));
    }
    try {
      onRemove();
    } catch (Throwable t) {
      LOG.error("removeThisAndChildren", t);
    }
    myAdded = false;
    if (!isInitialized()) {
      return;
    }
    for (MPSTreeNode node : getChildren()) {
      node.removeThisAndChildren();
    }
  }

  /**
   * Counterpart for {@link #removeThisAndChildren()} to send out {@link #onAdd()} notification for each element of a subtree being added (provided
   * subtree elements are already initialized. Otherwise, doesn't force their initialization).
   * Shall not affect parent/child relationship, merely a notification mechanism
   */
  final void addThisAndChildren() {
    if (myAdded) {
      throw new IllegalStateException(
          String.format("Trying to add tree node which have already been added, tree=%s, node=%s",
                        myTree != null ? myTree.getClass().getName() : "null", getClass().getName()));
    }
    try {
      onAdd();
    } catch (Throwable t) {
      LOG.error("addThisAndChildren", t);
    }
    myAdded = true;
    if (!isInitialized()) {
      return;
    }
    for (int i = 0; i < getChildCount(); i++) {
      MPSTreeNode node = (MPSTreeNode) getChildAt(i);
      node.addThisAndChildren();
    }
  }

  public MPSTreeNode findExactChildWith(Object userObject) {
    for (MPSTreeNode child : getChildren()) {
      if (child.getUserObject() == userObject) {
        return child;
      }
    }
    return null;
  }

  /**
   * Ignores subtree of nodes that have not been initialized yet.
   */
  @Nullable
  public final MPSTreeNode findDescendantWith(Object userObject) {
    if (getUserObject() == null ? userObject == null : getUserObject().equals(userObject)) {
      return this;
    }
    if (isInitialized()) {
      for (int i = 0; i < getChildCount(); i++) {
        MPSTreeNode result = ((MPSTreeNode) getChildAt(i)).findDescendantWith(userObject);
        if (result != null) {
          return result;
        }
      }
    }
    return null;
  }

  /**
   * Default value is: 2
   */
  public int getToggleClickCount() {
    return myToggleClickCount;
  }

  public void setToggleClickCount(int clickCount) {
    myToggleClickCount = clickCount;
  }

  //updates and refreshes tree
  public void renewPresentation() {
    final MPSTree tree = getTree();
    if (tree == null || tree.isDisposed()) {
      return;
    }
    updatePresentation();
    updateNodePresentationInTree();
  }

  /**
   * Does boilerplate refresh operations, like additional messages handling and notification dispatch.
   * Override {@link #doUpdatePresentation()} for custom presentation changes
   */
  protected final void updatePresentation() {
    doUpdatePresentation();
    if (myTree == null) {
      myTree = getTree();
    }
    if (myTree != null) {
      myTree.fireTreeNodeUpdated(this);
    }
    if (myTreeMessages != null) {
      myTreeMessages.stream()
                    .filter(TreeMessage::alternatesColor)
                    .max(Comparator.comparingInt(TreeMessage::getPriority))
                    .map(TreeMessage::getColor)
                    .ifPresent(this::setColor);
      myTreeMessages.stream()
                    .filter(TreeMessage::hasAdditionalText)
                    .max(Comparator.comparingInt(TreeMessage::getPriority))
                    .map(TreeMessage::getAdditionalText)
                    .ifPresent(this::setAdditionalText);
    }
  }

  /**
   * @deprecated odd and unclear contract (e.g. {@link #update()} and {@link #updateSubTree()} imply structure refresh, while this one does not),
   * parameters that merely control invocation of other public methods.
   * Use respective methods directly, instead.
   */
  @Deprecated
  public void updatePresentation(final boolean reloadSubTree, final boolean updateAncestors) {
    renewPresentation();
    if (reloadSubTree) {
      updateSubTree();
    }

    if (updateAncestors) {
      updateAncestorsPresentationInTree();
    }
  }

  /**
   * Attach an extra message to a node. Messages are identified by their {@link jetbrains.mps.ide.ui.tree.TreeMessageOwner owner}.
   * This method may be invoked from any thread, and doesn't trigger UI update, use {@link #renewPresentation()} from correct (EDT/UI) thread
   * if needed (e.g. if messages are attached the moment tree is being constructed, there's no reason to renew each node individually,
   * they get a chance to update them once tree becomes visible)
   *
   * @param message message to attach
   */
  public void addTreeMessage(@NotNull TreeMessage message) {
    List<TreeMessage> treeMessages = myTreeMessages;
    if (treeMessages == null) {
      synchronized (this) {
        if (myTreeMessages == null) {
          myTreeMessages = new CopyOnWriteArrayList<>();
        }
        treeMessages = myTreeMessages;
      }
    }
    treeMessages.add(message);
  }

  /**
   * Detach all messages of the specified owner.
   * This method can be invoked from any thread.
   * To trigger UI update, use {@link #renewPresentation()} from correct (EDT/UI) thread.
   *
   * @param owner identifies messages to remove
   * @return set of detached messages, or empty collection if none found
   */
  @NotNull
  public Set<TreeMessage> removeTreeMessages(TreeMessageOwner owner) {
    List<TreeMessage> treeMessages = myTreeMessages;
    if (owner == null || treeMessages == null) {
      return Collections.emptySet();
    }
    Set<TreeMessage> result = new HashSet<>(4);
    for (TreeMessage message : treeMessages) {
      if (owner.equals(message.getOwner())) {
        result.add(message);
      }
    }
    treeMessages.removeAll(result);
    return result;
  }

  protected void doUpdatePresentation() {
    if (myDefaultColor != null) {
      // reset color to default in case there were messages that affected color of the node
      setColor(myDefaultColor);
    }
  }

  /**
   * @deprecated use {@link MPSTreeNode#getIcon()} instead
   */
  @Deprecated
  @ToRemove(version = 2019.1)
  public final Icon getIcon(boolean expanded) {
    return getIcon();
  }

  public final Icon getIcon() {
    return myIcon;
  }

  /**
   * @deprecated use {@link MPSTreeNode#setIcon(javax.swing.Icon)} instead
   */
  @Deprecated
  @ToRemove(version = 2019.1)
  public final void setIcon(Icon newIcon, boolean expanded) {
    setIcon(newIcon);
  }

  public final void setIcon(Icon newIcon) {
    myIcon = newIcon;
  }

  public final Color getColor() {
    return myColor;
  }

  public final void setColor(Color color) {
    myColor = color;
  }

  @MagicConstant(flags = {Font.PLAIN, Font.BOLD, Font.ITALIC})
  public final int getFontStyle() {
    return myFontStyle;
  }

  @MagicConstant(flags = {Font.PLAIN, Font.BOLD, Font.ITALIC})
  public final void setFontStyle(int fontStyle) {
    myFontStyle = fontStyle;
  }

  public final void addFontAttribute(TextAttribute key, Object value) {
    if (myFontAttributes == null) {
      myFontAttributes = new HashMap<>(4);
    }
    myFontAttributes.put(key, value);
  }

  public final Map getFontAttributes() {
    return myFontAttributes == null ? Collections.emptyMap() : myFontAttributes;
  }

  @NotNull
  public final String getNodeIdentifier() {
    if (myNodeIdentifier == null) {
      // extra info for assertion failed due to MPS-12305
      String parentId = null;
      if (getParent() instanceof MPSTreeNode) {
        parentId = ((MPSTreeNode) getParent()).getNodeIdentifier();
      }
      throw new IllegalStateException("MPSTreeNode identifier cannot be null, class="
                                      + getClass().getName() + ", parent id=" + parentId);
    } else {
      return myNodeIdentifier;
    }
  }

  public final void setNodeIdentifier(@NotNull String newNodeIdentifier) {
    myNodeIdentifier = newNodeIdentifier;
  }

  public final String getAdditionalText() {
    return myAdditionalText;
  }

  public final void setAdditionalText(String newAdditionalText) {
    myAdditionalText = newAdditionalText;
  }

  public final String getText() {
    if (myText == null) {
      return getNodeIdentifier();
    } else {
      return myText;
    }
  }

  public final void setText(String text) {
    myText = text;
  }

  public final String getTooltipText() {
    return myTooltipText;
  }

  public final void setTooltipText(String tooltipText) {
    myTooltipText = tooltipText;
  }

  public final boolean isErrorState() {
    return myErrorState == ErrorState.ERROR;
  }

  public final void setErrorState(ErrorState state) {
    myErrorState = state;
    updateErrorState();
  }

  public final ErrorState getErrorState() {
    return myErrorState;
  }

  public final ErrorState getAggregatedErrorState() {
    return myCombinedErrorState;
  }

  protected void updateErrorState() {
    ErrorState state = ErrorState.NONE;
    if (propogateErrorUpwards()) {
      for (MPSTreeNode node : getChildren()) {
        state = state.combine(node.getAggregatedErrorState());
      }
    }
    myCombinedErrorState = state.combine(myErrorState);
    if (getParent() != null) {
      ((MPSTreeNode) getParent()).updateErrorState();
    }
  }

  protected boolean propogateErrorUpwards() {
    return true;
  }

  public String toString() {
    return getText();
  }

  public final boolean isAutoExpandable() {
    return myAutoExpandable;
  }

  public final void setAutoExpandable(boolean autoExpandable) {
    myAutoExpandable = autoExpandable;
  }

  /**
   * Dispatch a notification that tree model needs to reflect changes in node's presentation
   */
  public final void updateNodePresentationInTree() {
    if (getTree() != null) {
      getTree().getModel().nodeChanged(this);
    }
  }

  public void updateAncestorsPresentationInTree() {
    updateNodePresentationInTree();
    if (getParent() instanceof MPSTreeNode) {
      ((MPSTreeNode) getParent()).updateAncestorsPresentationInTree();
    }
  }

  protected boolean canBeOpened() {
    return true;
  }

  public void autoscroll() {

  }

  public boolean isLoadingEnabled() {
    return false;
  }
}
