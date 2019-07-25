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
package jetbrains.mps.smodel;

import jetbrains.mps.logging.Logger;
import jetbrains.mps.smodel.legacy.ConceptMetaInfoConverter;
import jetbrains.mps.util.EqualUtil;
import jetbrains.mps.util.InternUtil;
import jetbrains.mps.util.containers.EmptyIterable;
import org.apache.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.language.SProperty;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SNodeAccessUtil;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static jetbrains.mps.util.SNodeOperations.getDebugText;

/**
 * As a tribute to legacy code, we do allow access to constant and meta-info objects of a node without read access.
 * It's not encouraged for a new code, though, and might change in future, that's why it's stated here and not in openapi.SNode
 */
public class SNode implements org.jetbrains.mps.openapi.model.SNode {
  private static final Logger LOG = Logger.wrap(LogManager.getLogger(SNode.class));
  private static final String[] EMPTY_ARRAY = new String[0];
  private static final Object USER_OBJECT_LOCK = new Object();

  /**
   * inv: all children of a node, inclusive, have the same owner
   */
  @NotNull
  private SNodeOwner myOwner = FreeFloatNodeOwner.INSTANCE;
  private SContainmentLink myRoleInParent;
  private jetbrains.mps.smodel.SReference[] myReferences = jetbrains.mps.smodel.SReference.EMPTY_ARRAY;
  private Object[] myProperties = null;
  private org.jetbrains.mps.openapi.model.SNodeId myId;
  private volatile Object[] myUserObjects; // key,value,key,value ; copy-on-write (!)
  private SConcept myConcept; //todo make final after 3.2
  private SNode parent;
  /**
   * access only in firstChild()/firstChildInRole(role)
   */
  private SNode first;
  private SNode next;  // == null only for the last child in the list
  private SNode prev;  // notNull, myFirstChild.myLeftSibling = the last child

  public SNode(@NotNull SConcept concept) {
    this(concept, SModel.generateUniqueId());
  }

  public SNode(@NotNull SConcept concept, @NotNull org.jetbrains.mps.openapi.model.SNodeId id) {
    myConcept = concept;
    myId = id;
  }

  @NotNull
  @Override
  public SConcept getConcept() {
    // deliberately no assertCanRead(). It's constant field and meta-info.
    return myConcept;
  }

  @Override
  public boolean isInstanceOfConcept(@NotNull SAbstractConcept c) {
    return getConcept().isSubConceptOf(c);
  }

  @Override
  public void insertChildAfter(@NotNull SContainmentLink role, @NotNull org.jetbrains.mps.openapi.model.SNode child,
      @Nullable org.jetbrains.mps.openapi.model.SNode anchor) {
    if (anchor == null) {
      insertChildBefore(role, child, getFirstChild());
    } else {
      insertChildBefore(role, child, anchor.getNextSibling());
    }
  }

  protected final void assertCanRead() {
    myOwner.assertLegalRead();
  }

  private void assertCanChange() {
    myOwner.assertLegalChange();
  }

  @Override
  public org.jetbrains.mps.openapi.model.SNodeId getNodeId() {
    // deliberately no assertCanRead. It's constant field and sort of meta-info, why to constraint to read access?
    return myId;
  }

  @Override
  @NotNull
  public SNode getContainingRoot() {
    assertCanRead();

    SNode current = this;

    while (true) {
      if (current.treeParent() == null) return current;

      current = current.treeParent();
      myOwner.fireNodeRead(current, false);
    }
  }

  @Override
  public String getName() {
    assertCanRead();

    if (getConcept().isSubConceptOf(SNodeUtil.concept_INamedConcept)) {
      return SNodeAccessUtil.getProperty(this, SNodeUtil.property_INamedConcept_name);
    } else {
      myOwner.fireNodeRead(this, false);
      return null;
    }
  }

  @Override
  final public SNode getParent() {
    assertCanRead();

    SNode parent = treeParent();

    if (parent != null) {
      myOwner.fireNodeRead(parent, true);
    }
    return parent;
  }

  /**
   * Removes child from current node. This affects only link between current node and its child, but not links in
   * subtree of child node.
   * <p/>
   * Differs from {@link SNode#delete()}. FIXME please explain how it differs from delete()
   *
   * @param child
   */
  @Override
  public void removeChild(@NotNull org.jetbrains.mps.openapi.model.SNode child) {
    assertCanChange();
    assert
        child.getParent() == this :
        "Can't remove a node not from it's parent node: removing " + child.getReference().toString() + " from " + getReference().toString();

    final SNode wasChild = (SNode) child;
    final SContainmentLink wasRole = wasChild.getContainmentLink();
    final SNode anchorPrev = firstChild() == wasChild ? null : wasChild.treePrevious();
    final SNode anchorNext = wasChild.treeNext();

    assert wasRole != null;
    myOwner.fireBeforeNodeRemove(this, wasRole, wasChild, anchorPrev);

    children_remove(wasChild);
    wasChild.myRoleInParent = null;
    SModel model = myOwner.getModel();
    if (model != null && !model.isUpdateMode()) {
      // XXX no idea what this isUpdateMode() check is about, used to be in SNode.detach()
      //     it dates back to e64402e1, I suspect it might be a performance optimization
      //     (nobody gonna access references of a node that has been removed during internal update process)
      //
      // makeDirect has been separated from detach() code to give better control over reference resolution time.
      // indeed, in a perfect world we would know all nodes to be deleted during a command beforehand, and could process their references at once.
      // as it's not possible (node.sibling.detach could come right after node.detach) we at least go easy path for references within a detached subtree
      wasChild.makeReferencesDirect();
    }
    // FIXME what if myOwner is DetachedNodeOwner - shall we make node free-floating or leave it as detached?
    wasChild.detach(model == null ? myOwner : new DetachedNodeOwner(model));

    myOwner.performUndoableAction(this, new RemoveChildUndoableAction(this, anchorNext, wasRole, wasChild));
    myOwner.fireNodeRemove(this, wasRole, wasChild, anchorPrev);
  }

  /**
   * Deletes all nodes in subtree starting with current. Differs from {@link SNode#removeChild(org.jetbrains.mps.openapi.model.SNode)}.
   */
  @Override
  public void delete() {
    assertCanChange();

    SNode p = getParent();
    if (p != null) {
      p.removeChild(this);
    } else if (myOwner.getModel() != null) {
      myOwner.getModel().removeRootNode(this);
    }
  }

  @Override
  public String getPresentation() {
    if (!getConcept().isValid()) {
      String persistentName = findProperty(SNodeUtil.property_INamedConcept_name);
      return String.format("%s (concept is not found)", persistentName != null ? persistentName : myConcept.getName());
    }

    return String.valueOf(SNodeUtil.getPresentation(this));
  }

  @Override
  public String toString() {
    String s = null;
    try {
      s = findProperty(SNodeUtil.property_INamedConcept_name);
      if (s == null) {
        s = getPresentation();
      }
    } catch (RuntimeException t) {
      LOG.error(t, this);
    }
    if (s == null) {
      return "???";
    }
    return s;
  }

  @NotNull
  @Override
  public SNodeReference getReference() {
    return new jetbrains.mps.smodel.SNodePointer(this);
  }

  @Override
  public Object getUserObject(Object key) {
    final Object[] userObjects = myUserObjects;
    if (userObjects == null) return null;
    for (int i = 0; i < userObjects.length; i += 2) {
      if (userObjects[i].equals(key)) {
        return userObjects[i + 1];
      }
    }
    return null;
  }

  @Override
  public void putUserObject(Object key, @Nullable Object value) {
    synchronized (USER_OBJECT_LOCK) {
      if (value == null) {
        if (myUserObjects == null) return;
        for (int i = 0; i < myUserObjects.length; i += 2) {
          if (myUserObjects[i].equals(key)) {
            Object[] newarr = new Object[myUserObjects.length - 2];
            if (i > 0) {
              System.arraycopy(myUserObjects, 0, newarr, 0, i);
            }
            if (i + 2 < myUserObjects.length) {
              System.arraycopy(myUserObjects, i + 2, newarr, i, newarr.length - i);
            }
            myUserObjects = newarr;
            break;
          }
        }
        if (myUserObjects.length == 0) {
          myUserObjects = null;
        }
        return;
      }

      if (myUserObjects == null) {
        myUserObjects = new Object[]{key, value};
        return;
      }

      for (int i = 0; i < myUserObjects.length; i += 2) {
        if (myUserObjects[i].equals(key)) {
          Object[] newarr = new Object[myUserObjects.length];
          System.arraycopy(myUserObjects, 0, newarr, 0, myUserObjects.length);
          newarr[i + 1] = value;
          myUserObjects = newarr;
          return;
        }
      }
      Object[] newarr = new Object[myUserObjects.length + 2];
      System.arraycopy(myUserObjects, 0, newarr, 2, myUserObjects.length);
      newarr[0] = key;
      newarr[1] = value;
      myUserObjects = newarr;
    }
  }

  @NotNull
  @Override
  public List<SNode> getChildren() {
    return getChildren((SContainmentLink) null);
  }

  @NotNull
  @Override
  public List<jetbrains.mps.smodel.SReference> getReferences() {
    assertCanRead();

    myOwner.fireNodeRead(this, true);

    return Arrays.asList(myReferences);
  }

  @Override
  public org.jetbrains.mps.openapi.model.SNode getFirstChild() {
    assertCanRead();

    SNode child = firstChild();
    if (child != null) {
      myOwner.fireNodeRead(child, false);
    }
    return child;
  }

  @Override
  public org.jetbrains.mps.openapi.model.SNode getLastChild() {
    assertCanRead();

    SNode fc = firstChild();
    if (fc == null) {
      return null;
    }

    SNode lc = fc.treePrevious();
    if (lc != null) {
      myOwner.fireNodeRead(lc, false);
    }
    return lc;
  }

  @Override
  public SNode getPrevSibling() {
    assertCanRead();

    SNode p = treeParent();
    if (p == null) {
      return null;
    }

    myOwner.fireNodeRead(p, true);

    SNode tp = treePrevious();
    SNode ps = tp.next == null ? null : tp;
    if (ps != null) {
      myOwner.fireNodeRead(ps, true);
    }
    return ps;
  }

  @Override
  public SNode getNextSibling() {
    assertCanRead();

    SNode p = treeParent();
    if (p == null) {
      return null;
    }

    myOwner.fireNodeRead(p, true);

    SNode tn = treeNext();
    if (tn != null) {
      myOwner.fireNodeRead(tn, true); // although it used to send 2, not 3 notification, don't see any reason to have it different for parent and sibling
    }
    return tn;
  }

  @Override
  public Iterable<Object> getUserObjectKeys() {
    assertCanRead();

    final Object[] userObjects = myUserObjects;
    if (userObjects == null || userObjects.length == 0) return EmptyIterable.getInstance();
    return () -> new Iterator<Object>() {
      private int myIndex = 0;

      @Override
      public boolean hasNext() {
        return myIndex < userObjects.length;
      }

      @Override
      public Object next() {
        myIndex += 2;
        return userObjects[myIndex - 2];
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Override
  public org.jetbrains.mps.openapi.model.SModel getModel() {
    final SModel persistenceModel = myOwner.getModel();
    return persistenceModel == null ? null : persistenceModel.getModelDescriptor();
  }

  //----------------------------------------------------------
  //----------------USAGES IN REFACTORINGS ONLY---------------
  //----------------------------------------------------------

  public void setId(@Nullable org.jetbrains.mps.openapi.model.SNodeId id) {
    if (EqualUtil.equals(id, myId)) return;

    if (myOwner.getModel() == null) {
      myId = id;
    } else {
      LOG.error("can't set id to registered node " + getDebugText(this), new Throwable());
    }
  }

  void attach(@NotNull SNodeOwner nodeOwner) {
    nodeOwner.registerNode(this);

    myOwner = nodeOwner;

    for (SReference ref : myReferences) {
      ref.makeIndirect();
    }

    for (SNode child = firstChild(); child != null; child = child.treeNext()) {
      child.attach(nodeOwner);
    }
  }

  /**
   * for a subtree starting at this node, ask all references to point to an actual target node object instead of by-name/by-id value.
   * This operation usually precedes detachment of a node/subtree. Direct object pointer then facilitates reference access operations from
   * the detached nodes just in case there's need.
   */
  final void makeReferencesDirect() {
    for (SReference ref : myReferences) {
      ref.makeDirect();
    }
    for (SNode child = firstChild(); child != null; child = child.treeNext()) {
      child.makeReferencesDirect();
    }
  }

  void detach(@NotNull SNodeOwner detachedOwner) {
    myOwner.unregisterNode(this);

    for (SNode child = firstChild(); child != null; child = child.treeNext()) {
      child.detach(detachedOwner);
    }

    myOwner = detachedOwner;
  }

  @NotNull
  /*package*/ SNodeOwner getNodeOwner() {
    // FIXME for consistency, shall use same approach to dispatch events from e.g. getParent(), where I use
    // owner of the child node (in assumption owner is identical for the whole tree) myOwner.fireNodeRead(parent, true);
    // and in ChildrenIterator, which I don't want to make non-static, nor don't want to pass SNodeOwner in there right now
    // FIXME revisit uses of this method, re-consider approach. E.g. perhaps SModel shall keep SNodeOwner instance?
    return myOwner;
  }

  //--------private-------

  // perform inner structures update, doesn't dispatch any events
  private void addReferenceInternal(final SReference reference) {
    int oldLen = myReferences.length;
    jetbrains.mps.smodel.SReference[] newArray = new jetbrains.mps.smodel.SReference[oldLen + 1];
    System.arraycopy(myReferences, 0, newArray, 0, oldLen);
    newArray[oldLen] = reference;
    myReferences = newArray;

    myOwner.performUndoableAction(this, new InsertReferenceAtUndoableAction(this, reference));
  }

  // perform inner structures update, doesn't dispatch any events
  private void removeReferenceInternal(final SReference ref) {
    int index = -1;
    for (int i = 0; i < myReferences.length; i++) {
      if (myReferences[i] == ref) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      LOG.error("ref not found " + ref, new Throwable());
      return;
    }

    jetbrains.mps.smodel.SReference[] newArray = new jetbrains.mps.smodel.SReference[myReferences.length - 1];
    System.arraycopy(myReferences, 0, newArray, 0, index);
    System.arraycopy(myReferences, index + 1, newArray, index, myReferences.length - index - 1);
    myReferences = newArray;

    myOwner.performUndoableAction(this, new RemoveReferenceAtUndoableAction(this, ref));
  }

  protected SNode firstChild() {
    return first;
  }

  protected SNode treePrevious() {
    return prev;
  }

  public SNode treeNext() {
    return next;
  }

  protected SNode treeParent() {
    return parent;
  }

  //-------------new methods working by id-----------------

  protected void children_insertBefore(SNode anchor, @NotNull SNode node) {
    //be sure that getFirstChild is called before any access to myFirstChild
    SNode firstChild = firstChild();

    node.parent = this;

    if (firstChild == null) {
      assert anchor == null;
      first = node;
      node.next = null;
      node.prev = node;
      return;
    }

    if (anchor == null) {
      SNode lastChild = firstChild.prev;
      node.next = null;
      node.prev = lastChild;
      firstChild.prev = node;
      lastChild.next = node;
      return;
    }

    node.next = anchor;
    node.prev = anchor.prev;
    if (anchor != firstChild) {
      anchor.prev.next = node;
    } else {
      first = node;
    }
    anchor.prev = node;
  }

  protected void children_remove(@NotNull SNode node) {
    //be sure that getFirstChild is called before any access to myFirstChild
    SNode firstChild = firstChild();
    if (firstChild == node) {
      first = node.next;
      if (first != null) {
        first.prev = node.prev;
      }
    } else {
      node.prev.next = node.next;
      if (node.next != null) {
        node.next.prev = node.prev;
      } else {
        firstChild.prev = node.prev;
      }
    }
    node.prev = node.next = null;
    node.parent = null;
  }

  @Override
  public SContainmentLink getContainmentLink() {
    return myRoleInParent;
  }

  @Override
  public boolean hasProperty(@NotNull SProperty property) {
    assertCanRead();

    String val = findProperty(property);
    myOwner.firePropertyRead(this, property, val, true);
    return !SModelUtil_new.isEmptyPropertyValue(val);
  }

  @Override
  public String getProperty(@NotNull SProperty property) {
    assertCanRead();

    String value = findProperty(property);
    myOwner.firePropertyRead(this, property, value, false);
    return value;
  }

  /**
   * Bare access, no notifications nor checks
   */
  private String findProperty(SProperty property) {
    if (myProperties != null) {
      int index = getPropertyIndex(property);
      if (index != -1) {
        return (String) myProperties[index + 1];
      }
    }
    return null;
  }

  @Override
  public void setProperty(@NotNull final SProperty property, String propertyValue) {
    assertCanChange();

    propertyValue = InternUtil.intern(propertyValue);

    int index = getPropertyIndex(property);
    final String oldValue = index == -1 ? null : (String) myProperties[index + 1];
    if (EqualUtil.equals(oldValue, propertyValue)) return;

    if (propertyValue == null) {
      Object[] oldProperties = myProperties;
      int newLength = oldProperties.length - 2;
      if (newLength == 0) {
        myProperties = null;
      } else {
        myProperties = new Object[newLength];
        System.arraycopy(oldProperties, 0, myProperties, 0, index);
        System.arraycopy(oldProperties, index + 2, myProperties, index, newLength - index);
      }
    } else if (oldValue == null) {
      Object[] oldProperties = myProperties == null ? EMPTY_ARRAY : myProperties;
      myProperties = new Object[oldProperties.length + 2];
      System.arraycopy(oldProperties, 0, myProperties, 0, oldProperties.length);
      myProperties[myProperties.length - 2] = property;
      myProperties[myProperties.length - 1] = propertyValue;
    } else {
      myProperties[index + 1] = propertyValue;
    }

    myOwner.performUndoableAction(this, new PropertyChangeUndoableAction(this, property, oldValue, propertyValue));

    myOwner.firePropertyChange(this, property, oldValue, propertyValue);
  }

  @NotNull
  @Override
  public Iterable<SProperty> getProperties() {
    assertCanRead();

    myOwner.fireNodeRead(this, true);

    if (myProperties == null) return new EmptyIterable<>();
    List<SProperty> result = new ArrayList<>(myProperties.length / 2);
    for (int i = 0; i < myProperties.length; i += 2) {
      result.add((SProperty) myProperties[i]);
    }
    return result;
  }

  @Override
  public void setReferenceTarget(@NotNull SReferenceLink role, @Nullable org.jetbrains.mps.openapi.model.SNode target) {
    assertCanChange();

    SReference toDelete = null;
    if (myReferences != null) {
      for (SReference reference : myReferences) {
        if (!reference.getLink().equals(role)) continue;
        toDelete = reference;
        break;
      }
    }
    if (toDelete == null && target == null) {
      return;
    }

    if (toDelete != null) {
      removeReferenceInternal(toDelete);
    }
    SReference newValue = null;
    if (target != null) {
      newValue = SReference.create(role, this, target);
      addReferenceInternal(newValue);
    }

    myOwner.fireReferenceChange(this, role, toDelete, newValue);
  }

  @Override
  public SNode getReferenceTarget(@NotNull SReferenceLink role) {
    assertCanRead();

    SReference reference = findReference(role);
    SNode result = reference == null ? null : (SNode) reference.getTargetNode();
    myOwner.fireReferenceRead(this, role, result);
    return result;
  }

  @Override
  public SReference getReference(@NotNull SReferenceLink role) {
    assertCanRead();

    SReference result = findReference(role);
    myOwner.fireReferenceRead(this, role, null);
    return result;
  }

  /**
   * Bare access, no notifications nor checks
   */
  private SReference findReference(@NotNull SReferenceLink role) {
    for (SReference reference : myReferences) {
      if (role.equals(reference.getLink())) {
        return reference;
      }
    }
    return null;
  }

  // FIXME odd to have role parameter, while SReference.getLink gives exactly what's needed (and doesn't violate consistency)
  // to clear reference, one could use setReferenceTarget(). Alternatively, SReference shall not keep
  // the meta-object, and query its source node for role instead (as a free-floating Reference shall not answer its SReferenceLink).
  @Override
  public void setReference(@NotNull SReferenceLink role, org.jetbrains.mps.openapi.model.SReference toAdd) {
    assertCanChange();

    SReference toRemove = null;
    for (SReference r : myReferences) {
      if (!r.getLink().equals(role)) continue;
      toRemove = r;
      break;
    }

    if (toRemove != null) {
      removeReferenceInternal(toRemove);
    }

    if (toAdd != null) {
      assert toAdd.getSourceNode() == this;
      assert role.equals(toAdd.getLink());
      addReferenceInternal((SReference) toAdd);
    }

    myOwner.fireReferenceChange(this, role, toRemove, toAdd);
  }

  public void insertChildBefore(@NotNull final SContainmentLink role, @NotNull org.jetbrains.mps.openapi.model.SNode child,
      @Nullable final org.jetbrains.mps.openapi.model.SNode anchor) {
    assertCanChange();

    final SNode schild = (SNode) child;
    SNode parentOfChild = schild.getParent();
    if (parentOfChild != null) {
      final String fmt = "%s already has parent: %s\nCouldn't add it to: %s";
      final String m = String.format(fmt, getDebugText(schild), getDebugText(parentOfChild), getDebugText(this));
      throw new IllegalModelAccessException(m);
    }
    final SModel childModel = schild.getNodeOwner().getModel();
    if (childModel != null) {
      if (childModel.isRoot(schild)) {
        final String fmt = "Attempt to add root %s from model %s to node %s.";
        throw new IllegalModelAccessException(String.format(fmt, getDebugText(schild), childModel, getDebugText(this)));
      } else {
        final String fmt = "Node to add (%s) belongs to a model. Couldn't add it to %s. Shall detach it/remove from the model %s first.";
        throw new IllegalModelAccessException(String.format(fmt, getDebugText(schild), getDebugText(this), childModel));
      }
    }

    if (getContainingRoot() == child) {
      throw new IllegalModelAccessException("Trying to create a cyclic tree");
    }

    if (anchor != null) {
      if (anchor.getParent() != this) {
        throw new IllegalModelAccessException(
            "anchor is not a child of this node" + " | " +
                "this: " + getDebugText(this) + " | " +
                "anchor: " + getDebugText(anchor)
        );
      }
    }

    schild.myRoleInParent = role;
    children_insertBefore(((SNode) anchor), schild);

    myOwner.startUndoTracking(this, schild);

    schild.attach(myOwner);

    myOwner.performUndoableAction(this, new InsertChildAtUndoableAction(this, anchor, role, child));

    myOwner.fireNodeAdd(this, role, schild, (SNode) anchor);
  }

  @Override
  public void addChild(@NotNull SContainmentLink role, @NotNull org.jetbrains.mps.openapi.model.SNode child) {
    insertChildBefore(role, child, null);
  }

  @Override
  @NotNull
  public List<SNode> getChildren(SContainmentLink role) {
    SNode firstChild = firstChild();

    if (role != null) {
      while (firstChild != null && !role.equals(firstChild.getContainmentLink())) {
        firstChild = firstChild.treeNext();
      }
    }

    if (firstChild == null) {
      return Collections.emptyList();
    }

    return new ImmutableChildrenList(firstChild, role);
  }

  private int getPropertyIndex(SProperty id) {
    if (myProperties == null) return -1;
    for (int i = 0; i < myProperties.length; i += 2) {
      if (id.equals(myProperties[i])) return i;
    }
    return -1;
  }

  @Deprecated
  @Override
  public String getRoleInParent() {
    SContainmentLink cl = getContainmentLink();
    if (cl == null) return null;
    return cl.getRoleName();
  }

  @Deprecated
  @Override
  public final boolean hasProperty(String propertyName) {
    return hasProperty(convertToProp(propertyName));
  }

  @Deprecated
  @Override
  public final String getProperty(String propertyName) {
    return getProperty(convertToProp(propertyName));
  }

  @Deprecated
  @Override
  public void setProperty(String propertyName, String propertyValue) {
    SProperty prop = convertToProp(propertyName);
    setProperty(prop, propertyValue);
  }

  @Deprecated
  @Override
  public Collection<String> getPropertyNames() {
    List<String> res = new ArrayList<>(myProperties == null ? 0 : myProperties.length / 2);
    for (SProperty p : getProperties()) {
      res.add(p.getName());
    }
    return res;
  }

  @Deprecated
  @Override
  public void setReferenceTarget(String role, @Nullable org.jetbrains.mps.openapi.model.SNode target) {
    setReferenceTarget(convertToRef(role), target);
  }

  @Deprecated
  @Override
  public SNode getReferenceTarget(String role) {
    return getReferenceTarget(convertToRef(role));
  }

  @Deprecated
  @Override
  public SReference getReference(String role) {
    return getReference(convertToRef(role));
  }

  @Deprecated
  @Override
  public void setReference(String role, @Nullable org.jetbrains.mps.openapi.model.SReference reference) {
    setReference(convertToRef(role), reference);
  }

  @Deprecated
  public void insertChildBefore(@NotNull String role, org.jetbrains.mps.openapi.model.SNode child,
      @Nullable final org.jetbrains.mps.openapi.model.SNode anchor) {
    insertChildBefore(convertToLink(role), child, anchor);
  }

  @Deprecated
  @Override
  public void addChild(String role, org.jetbrains.mps.openapi.model.SNode child) {
    insertChildBefore(role, child, null);
  }

  @Deprecated
  @Override
  @NotNull
  public List<SNode> getChildren(String role) {
    return getChildren(convertToLink(role));
  }

  @NotNull
  private SContainmentLink convertToLink(String role) {
    return ((ConceptMetaInfoConverter) myConcept).convertAggregation(role);
  }

  @NotNull
  private SReferenceLink convertToRef(String role) {
    return ((ConceptMetaInfoConverter) myConcept).convertAssociation(role);
  }

  @NotNull
  private SProperty convertToProp(String name) {
    return ((ConceptMetaInfoConverter) myConcept).convertProperty(name);
  }
}
