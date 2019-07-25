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
package jetbrains.mps.ide.ui.tree.smodel;

import jetbrains.mps.ide.icons.GlobalIconManager;
import jetbrains.mps.ide.ui.tree.MPSTreeChildOrder;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.ide.ui.tree.MPSTreeNodeEx;
import jetbrains.mps.ide.ui.tree.TreeElement;
import jetbrains.mps.ide.ui.tree.TreeNodeTextSource;
import jetbrains.mps.ide.ui.tree.TreeNodeVisitor;
import jetbrains.mps.smodel.DependencyRecorder;
import jetbrains.mps.smodel.SNodeUtil;
import jetbrains.mps.util.ToStringComparator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeAccessUtil;
import org.jetbrains.mps.util.Condition;

import javax.swing.Icon;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SModelTreeNode extends MPSTreeNode implements TreeElement {
  private final SModel myModelDescriptor;
  private final TreeNodeTextSource<SModelTreeNode> myTextSource;
  private List<SModelTreeNode> myChildModelTreeNodes = new ArrayList<>();

  private boolean myPackagesEnabled;
  private boolean myInitialized = false;
  private boolean myInitializing = false;
  private List<SNodeGroupTreeNode> myRootGroups = new ArrayList<>();

  private final DependencyRecorder<SNodeTreeNode> myDependencyRecorder = new DependencyRecorder<>();

  // the only reason to keep this map is its use through insertRoots()
  private Map<String, PackageNode> myPackageNodes = new HashMap<>();
  private Icon myBaseIcon;

  public SModelTreeNode(@NotNull SModel model) {
    this(model, new LongModelNameText());
  }

  public SModelTreeNode(@NotNull SModel model, @NotNull TreeNodeTextSource<SModelTreeNode> textSource) {
    myModelDescriptor = model;
    myTextSource = textSource;
    // Though it's odd, we use model name as tree node's user object for centuries. There's code that match tree nodes
    // based on their user object value, change with extra care, see MPSTree.findNodeWith(). If this scenario persists, have to change to model ref or at least
    // full name including stereotype, as use of plain name is ambiguous.
    setUserObject(model.getName().getLongName());
    setNodeIdentifier(model.toString());
    Icon icon = GlobalIconManager.getInstance().getIconFor(model);
    setIcon(icon);
    setBaseIcon(icon);
    // invocation of external code with not completely initialized this is bad. Perhaps, shall rely on doUpdatePresentation invoked from onAdd()?
    setText(myTextSource.calculateText(this));
    myPackagesEnabled = true;
  }

  public void setBaseIcon(@Nullable Icon baseIcon) {
    myBaseIcon = baseIcon;
  }

  /**
   * Base/default icon is not necessarily the one actually displayed, which may include different overlays,
   * like 'modified' indicator.
   *
   * @return base icon, if any
   */
  @Nullable
  public Icon getBaseIcon() {
    // XXX How come base icon is only for models, not for SNodeTreeNode as well?
    return myBaseIcon;
  }

  @Override
  public boolean isLeaf() {
    return false;
  }

  public boolean hasModelsUnder() {
    return !getSubfolderSModelTreeNodes().isEmpty();
  }

  //do not use!
  public DependencyRecorder<SNodeTreeNode> getDependencyRecorder() {
    return myDependencyRecorder;
  }

  protected SNodeGroupTreeNode getNodeGroupFor(SNode node) {
    if (!myPackagesEnabled) {
      return null;
    }

    String nodePackage = SNodeAccessUtil.getProperty(node, SNodeUtil.property_BaseConcept_virtualPackage);

    if (nodePackage != null && !nodePackage.isEmpty()) {
      String[] packages = nodePackage.split("\\.");

      String pack = "";
      PackageNode current = null;
      for (String aPackage : packages) {
        if (pack.length() > 0) {
          pack += ".";
        }
        pack += aPackage;

        if (!myPackageNodes.containsKey(pack)) {
          PackageNode parent = current;
          current = new PackageNode(this, aPackage, parent);
          myPackageNodes.put(pack, current);
          register(parent, current);
        }

        current = myPackageNodes.get(pack);
      }

      return current;
    }
    return null;
  }

  private void register(SNodeGroupTreeNode parent, SNodeGroupTreeNode groupTreeNode) {
    final String rp = groupTreeNode.getText();
    if (parent == null) {
      int index = -1;
      for (int i = 0; i < myRootGroups.size(); i++) {
        SNodeGroupTreeNode group = myRootGroups.get(i);
        if (rp.compareTo(group.getText()) < 0) {
          index = i;
          break;
        }
      }
      if (index == -1) {
        index = myRootGroups.size();
      }

      myRootGroups.add(index, groupTreeNode);

      if (myInitialized || myInitializing) {
        DefaultTreeModel treeModel = getTree().getModel();
        treeModel.insertNodeInto(groupTreeNode, this, index + myChildModelTreeNodes.size());
      }
    } else {
      int index = -1;
      int groupCount = 0;
      for (int i = 0; i < parent.getChildCount(); i++) {
        if (!(parent.getChildAt(i) instanceof SNodeGroupTreeNode)) {
          break;
        }
        groupCount++;
        SNodeGroupTreeNode group = (SNodeGroupTreeNode) parent.getChildAt(i);
        if (rp.compareTo(group.getText()) < 0) {
          index = i;
          break;
        }
      }
      if (index == -1) {
        index = groupCount;
      }

      if (myInitialized || myInitializing) {
        DefaultTreeModel treeModel = getTree().getModel();
        treeModel.insertNodeInto(groupTreeNode, parent, index);
      } else {
        parent.insert(groupTreeNode, index);
      }
    }
  }

  public void groupBecameEmpty(SNodeGroupTreeNode node) {
    DefaultTreeModel treeModel = getTree().getModel();

    myRootGroups.remove(node);

    MPSTreeNode parent = (MPSTreeNode) node.getParent();
    if (node.isAutoDelete()) {
      treeModel.removeNodeFromParent(node);
    }

    if (parent instanceof SNodeGroupTreeNode && parent.getChildCount() == 0) {
      groupBecameEmpty((SNodeGroupTreeNode) parent);
    }

    if (node instanceof PackageNode) {
      myPackageNodes.remove(((PackageNode) node).getPackage());
    }
  }

  public SModel getModel() {
    return myModelDescriptor;
  }

  @NotNull
  public final SNodeTreeNode createSNodeTreeNode(SNode node) {
    return createSNodeTreeNode(node, (String) null);
  }

  @NotNull
  public final SNodeTreeNode createSNodeTreeNode(SNode node, Condition<SNode> condition) {
    return createSNodeTreeNode(node, null, condition);
  }

  @NotNull
  public final SNodeTreeNode createSNodeTreeNode(SNode node, String role) {
    return createSNodeTreeNode(node, role, Condition.always());
  }

  @NotNull
  public SNodeTreeNode createSNodeTreeNode(SNode node, String role, Condition<SNode> condition) {
    return new SNodeTreeNode(node, role, condition);
  }

  @Override
  public boolean isInitialized() {
    return myInitialized;
  }

  public void addChildModel(SModelTreeNode model) {
    myChildModelTreeNodes.add(model);
  }

  public List<SModelTreeNode> getSubfolderSModelTreeNodes() {
    return Collections.unmodifiableList(myChildModelTreeNodes);
  }

  public List<SModelTreeNode> getAllSubfolderSModelTreeNodes() {
    List<SModelTreeNode> result = new ArrayList<>();
    if (myChildModelTreeNodes.isEmpty()) {
      result.add(this);
    } else {
      for (SModelTreeNode treeNode : myChildModelTreeNodes) {
        result.addAll(treeNode.getAllSubfolderSModelTreeNodes());
      }
    }
    return result;
  }

  @Override
  protected void doUpdate() {
    myInitialized = false;
    this.removeAllChildren();
  }

  @Override
  protected void doUpdatePresentation() {
    setText(myTextSource.calculateText(this));
  }

  @Override
  protected void doInit() {
    try {
      myInitializing = true;

      removeAllChildren();
      myPackageNodes.clear();
      myRootGroups.clear();

      for (SModelTreeNode newChildModel : myChildModelTreeNodes) {
        add(newChildModel);
      }
      org.jetbrains.mps.openapi.model.SModel model = getModel();

      List<SNode> filteredRoots = new ArrayList<>();
      for (SNode node : model.getRootNodes()) {
        filteredRoots.add(node);
      }
      for (SNode sortedRoot : filteredRoots) {
        MPSTreeNodeEx treeNode = createSNodeTreeNode(sortedRoot);
        MPSTreeNode group = getNodeGroupFor(sortedRoot);
        if (group != null) {
          group.add(treeNode);
        } else {
          add(treeNode);
        }
      }

      if (getTree() instanceof MPSTreeChildOrder) {
        final ArrayList<MPSTreeNode> copyToSort = new ArrayList<>(getChildren());
        if (((MPSTreeChildOrder) getTree()).reorder(this, copyToSort)) {
          removeAllChildren();
          copyToSort.forEach(this::add);
        }
      }

      final DefaultTreeModel treeModel = getTree().getModel();
      treeModel.nodeStructureChanged(this);
    } finally {
      myInitialized = true;
      myInitializing = false;
    }
  }

  @Override
  protected final boolean canBeOpened() {
    return false;
  }

  public void insertRoots(Set<SNode> addedRoots) {
    if (addedRoots.isEmpty()) return;

    DefaultTreeModel treeModel = getTree().getModel();

    final ArrayList<SNode> allRoots = new ArrayList<>();
    for (SNode root1 : getModel().getRootNodes()) {
      allRoots.add(root1);
    }
    Collections.sort(allRoots, new ToStringComparator(true));

    List<SNode> added = new ArrayList<>(addedRoots);
    Collections.sort(added, Comparator.comparingInt(allRoots::indexOf));

    //Assuming that "added" as well as targetNode.children for all targetNodes are sorted already,
    //so we merge the two by always remembering the last insertion point
    final HashMap<MPSTreeNode, Integer> lastPositions = new HashMap<>();
    for (SNode root : added) {
      SNodeTreeNode nodeToInsert = createSNodeTreeNode(root);
      MPSTreeNode targetNode = getNodeGroupFor(root);

      if (targetNode == null) {
        targetNode = SModelTreeNode.this;
      }

      int index = -1;
      Integer lastPosition = lastPositions.get(targetNode);
      if (lastPosition == null) lastPosition = 0;

      for (int i = lastPosition; i < targetNode.getChildCount(); i++) {
        if (!(targetNode.getChildAt(i) instanceof SNodeTreeNode)) {
          continue;
        }
        SNodeTreeNode child = (SNodeTreeNode) targetNode.getChildAt(i);
        String rp = root.toString();
        String cp = child.getSNode().toString();
        if (rp.compareTo(cp) < 0) {
          index = i;
          break;
        }
      }
      if (index == -1) {
        index = targetNode.getChildCount();
      }
      lastPositions.put(targetNode, index + 1);
      treeModel.insertNodeInto(nodeToInsert, targetNode, index);
    }
  }

  @Override
  public void accept(@NotNull TreeNodeVisitor visitor) {
    visitor.visitModelNode(this);
  }

  public static class LongModelNameText implements TreeNodeTextSource<SModelTreeNode> {
    @Override
    public String calculateText(SModelTreeNode treeNode) {
      SModel model = treeNode.getModel();
      return model == null ? "<null>" : model.getName().getValue();
    }
  }

  public static class ShortModelNameText implements TreeNodeTextSource<SModelTreeNode> {
    @Override
    public String calculateText(SModelTreeNode treeNode) {
      SModel model = treeNode.getModel();
      return model == null ? "<null>" : model.getName().getShortNameWithStereotype();
    }
  }
}
