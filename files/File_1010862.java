/*
 * Copyright 2003-2013 JetBrains s.r.o.
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
package jetbrains.mps.idea.core.psi.impl;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.util.ArrayUtil;
import com.intellij.util.SmartList;
import jetbrains.mps.fileTypes.MPSLanguage;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.idea.core.psi.impl.NodeList.Entry;
import jetbrains.mps.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.SRepository;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * evgeny, 1/25/13
 */
public abstract class MPSPsiNodeBase extends LightElement {

  private NodeList children;
  private NodeList.Entry listEntry;

  private int cachedTreePosition;

  public MPSPsiNodeBase(PsiManager manager) {
    super(manager, MPSLanguage.INSTANCE);
    this.children = new NodeList(this);
  }

  @Override
  public PsiManager getManager() {
    if (myManager != null) {
      return myManager;
    }

    return getContainingModel().getManager();
  }

  public MPSPsiModel getContainingModel() {
    PsiElement parent = this;
    while (parent != null && !(parent instanceof MPSPsiRootNode)) {
      parent = parent.getParent();
    }

    if (parent == null) {
      throw new PsiInvalidElementAccessException(this);
    }

    return ((MPSPsiRootNode) parent).getContainingModel();
  }

  protected SRepository getProjectRepository() {
    Project p = ProjectHelper.fromIdeaProject(getProject());
    assert p != null;
    return p.getRepository();
  }

  @Override
  public PsiFile getContainingFile() {
    return null; // either that or a real file!
  }

  @Override
  public TextRange getTextRange() {
    int p = getTreePosition();
    return new TextRange(p, p);
  }

  @Override
  public int getTextOffset() {
    return getTreePosition();
  }

  private int getTreePosition() {
    MPSPsiModel model = getContainingModel();
    Integer pos = model.getNodePosition(this);
    if (pos == null) {
      return cachedTreePosition;
    }
    cachedTreePosition = pos;
    return pos;
  }

  @Override
  public boolean isValid() {
    return listEntry != null;
  }

  @Override
  public MPSPsiNodeBase getFirstChild() {
    return children.first();
  }

  @Override
  public MPSPsiNodeBase getLastChild() {
    return children.last();
  }

  @NotNull
  @Override
  public PsiElement[] getChildren() {
    PsiElement[] result = new PsiElement[children.size()];
    children.toArray(result);
    return result;
  }

  @SuppressWarnings("unchecked")
  public <T extends PsiElement> T[] getChildren(Class<T> aClass) {
    T[] result = (T[]) Array.newInstance(aClass, children.size());
    children.toArray(result);
    return result;
  }

  public <T extends PsiElement> T[] getChildrenOfType(String role, @NotNull Class<T> aClass) {
    if (role == null) return null;

    List<T> result = null;
    for (PsiElement child = getFirstChild(); child != null; child = child.getNextSibling()) {
      if (child instanceof MPSPsiNode && role.equals(((MPSPsiNode) child).getContainingRole()) && aClass.isInstance(child)) {
        if (result == null) result = new SmartList<T>();
        //noinspection unchecked
        result.add((T) child);
      }
    }
    return result == null ? null : ArrayUtil.toObjectArray(result, aClass);
  }

  protected <T extends PsiElement> T getChildOfType(@NotNull Class<T> aClass) {
    for (PsiElement child = getFirstChild(); child != null; child = child.getNextSibling()) {
      if (child instanceof MPSPsiNodeBase && aClass.isInstance(child)) {
        return aClass.cast(child);
      }
    }
    return null;
  }

  /**
   * Enables PSI nodes to have a different parent-child structure than that of MPS nodes they're built upon
   * @param child about to be added
   * @return Null if this node will be the parent (default) or some other child (or maybe even grandchild) node
   */
  @Nullable
  protected MPSPsiNodeBase getParentFor(MPSPsiNode child) {
    return null;
  }

  @Override
  public MPSPsiNodeBase getNextSibling() {
    return listEntry != null && !listEntry.isLast() ? listEntry.list().next(this) : null;
  }

  @Override
  public MPSPsiNodeBase getPrevSibling() {
    return listEntry != null && !listEntry.isFirst() ? listEntry.list().prev(this) : null;
  }

  @Override
  public PsiElement getParent() {
    return listEntry != null ? listEntry.list().owner() : null;
  }

  @Override
  public PsiReference getReference() {
    return null;
  }

  @Override
  public PsiReference[] getReferences() {
    return PsiReference.EMPTY_ARRAY;
  }

  public MPSPsiRootNode getContainingRoot() {
    PsiElement parent = this;
    while (parent != null && !(parent instanceof MPSPsiRootNode)) {
      parent = parent.getParent();
    }

    if (parent == null) {
      throw new PsiInvalidElementAccessException(this);
    }

    return (MPSPsiRootNode) parent;
  }

  @Override
  public boolean isPhysical() {
    return true;
  }

  protected final Iterable<MPSPsiNodeBase> children() {
    return new Iterable<MPSPsiNodeBase>() {
      @Override
      public Iterator<MPSPsiNodeBase> iterator() {
        return new Iterator<MPSPsiNodeBase>() {
          MPSPsiNodeBase node = null;

          @Override
          public boolean hasNext() {
            return (node == null && children.first() != null) ||
              (node != null && node != children.last());
          }

          @Override
          public MPSPsiNodeBase next() {
            if (!hasNext()) throw new NoSuchElementException();
            if (node == null) {
              return (node = children.first());
            } else {
              return (node = children.next(node));
            }
          }

          @Override
          public void remove() {
            throw new UnsupportedOperationException();
          }
        };
      }
    };
  }

  protected final void addChildFirst(@NotNull MPSPsiNodeBase node) {
    children.addFirst(node);
  }

  protected final void addChildLast(@NotNull MPSPsiNodeBase node) {
    children.addLast(node);
  }

  protected final void insertChildAfter(MPSPsiNodeBase anchor, @NotNull MPSPsiNodeBase node) {
    children.insertAfter(anchor, node);
  }

  protected final void insertChildBefore(MPSPsiNodeBase anchor, @NotNull MPSPsiNodeBase node) {
    children.insertBefore(anchor, node);
  }

  protected final void updateChildren() {
    for(MPSPsiNodeBase element : children()) {
      Entry entry = children.findEntry(element);
      element.setEntry(entry);
      element.updateChildren();
    }
  }

  @Deprecated
  protected final void addChild(MPSPsiNodeBase anchor, @NotNull MPSPsiNodeBase node) {
    if (anchor == null) {
      children.addFirst(node);
    } else {
      children.insertAfter(anchor, node);
    }
  }

  protected final void removeChild(@NotNull MPSPsiNodeBase node) {
    children.remove(node);
  }

  protected final void replaceChild(@NotNull MPSPsiNodeBase oldNode, @NotNull MPSPsiNodeBase replacementNode) {
    children.replace(oldNode, replacementNode);
  }

  protected final void clearChildren() {
    children.clear();
  }


  /*package*/ void setEntry(Entry newEntry) {
    assert (listEntry == null && newEntry != null) || (listEntry != null && newEntry == null);
    listEntry = newEntry;

    if (newEntry == null) {
      // means invalidating, propagating it down
      MPSPsiNodeBase curr = children.first();
      while (curr != null) {
        MPSPsiNodeBase next = curr.getNextSibling();
        curr.setEntry(null);
        curr = next;
      }
    }
  }

  /*package*/ Entry getEntry() {
    return listEntry;
  }
}
