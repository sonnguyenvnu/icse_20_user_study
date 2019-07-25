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
package jetbrains.mps.editor.runtime.style;

import jetbrains.mps.editor.runtime.style.StyleAttributeMap.DiscardValue;
import jetbrains.mps.openapi.editor.style.Style;
import jetbrains.mps.openapi.editor.style.StyleAttribute;
import jetbrains.mps.openapi.editor.style.StyleChangeEvent;
import jetbrains.mps.openapi.editor.style.StyleListener;
import jetbrains.mps.util.EqualUtil;
import jetbrains.mps.util.containers.EmptyIterator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * User: shatalin
 * Date: 1/11/13
 */
public class StyleImpl implements Style {
  private static final Logger LOG = LogManager.getLogger(StyleImpl.class);

  private Style myParent;
  private List<Style> myChildren = null;
  private List<StyleListener> myStyleListeners = null;

  private TopLevelStyleMap myAttributes = new TopLevelStyleMap();
  private TopLevelStyleMap myCachedAttributes = new TopLevelStyleMap();

  @Override
  public void putAll(@NotNull Style style) {
    putAll(style, 0);
  }

  @Override
  public void putAll(@NotNull Style style, int selfPriority) {
    Set<StyleAttribute> addedSimple = new StyleAttributeSet();
    Set<StyleAttribute> addedNotSimple = new StyleAttributeSet();
    for (StyleAttribute<Object> attribute : style.getSpecifiedAttributes()) {
      Collection<IntPair<Object>> putAttributes = style.getAll(attribute);
      if (putAttributes != null) {
        int attributePointer = myAttributes.search(attribute.getIndex());
        for (IntPair<Object> value : putAttributes) {
          attributePointer = myAttributes.setValue(attribute, attributePointer, Math.max(value.index, selfPriority), value.value == null ? DiscardValue.getInstance() : value.value);
        }
      }
      if (StyleAttributes.isSimple(attribute)) {
        addedSimple.add(attribute);
      } else {
        addedNotSimple.add(attribute);
      }
    }
    updateCache(addedNotSimple);
    fireStyleChanged(new StyleChangeEvent(this, addedSimple));
  }

  @Override
  public void removeAll(@NotNull Style style) {
    Set<StyleAttribute> addedSimple = new StyleAttributeSet();
    Set<StyleAttribute> addedNotSimple = new StyleAttributeSet();
    for (StyleAttribute<Object> attribute : style.getSpecifiedAttributes()) {
      Collection<IntPair<Object>> putAttributes = style.getAll(attribute);
      if (putAttributes != null) {
        int attributePointer = myAttributes.search(attribute.getIndex());
        for (IntPair<Object> value : putAttributes) {
          attributePointer = myAttributes.setValue(attribute, attributePointer, value.index, DiscardValue.getInstance());
        }
      }
      if (StyleAttributes.isSimple(attribute)) {
        addedSimple.add(attribute);
      } else {
        addedNotSimple.add(attribute);
      }
    }
    updateCache(addedNotSimple);
    fireStyleChanged(new StyleChangeEvent(this, addedSimple));
  }

  @Override
  public <T> void set(StyleAttribute<T> attribute, int priority, T value) {
    myAttributes.setValue(attribute, priority, value);
    Set<StyleAttribute> attributeSet = Collections.<StyleAttribute>singleton(attribute);
    if (StyleAttributes.isSimple(attribute)) {
      fireStyleChanged(new StyleChangeEvent(this, attributeSet));
    } else {
      updateCache(attributeSet);
    }
  }

  @Override
  public <T> void set(StyleAttribute<T> attribute, T value) {
    set(attribute, 0, value);
  }

  @Override
  public <T> int getHighestPriority(StyleAttribute<T> attribute) {
    int cachedAttributePointer = myCachedAttributes.search(attribute.getIndex());
    if (TopLevelStyleMap.isEmpty(cachedAttributePointer)) {
      return -1;
    } else {
      return myCachedAttributes.getTopPair(attribute, cachedAttributePointer).index;
    }
  }

  @Override
  public <T> T get(StyleAttribute<T> attribute) {
    if (StyleAttributes.isSimple(attribute)) {
      IntPair<T> topPair = myAttributes.getTopPair(attribute);
      return topPair == null ? attribute.combine(null, null) : topPair.value;
    } else {
      IntPair<T> topPair = myCachedAttributes.getTopPair(attribute);
      return topPair == null ? attribute.combine(null, null) : topPair.value;
    }
  }

  @Override
  @Nullable
  public <T> Collection<IntPair<T>> getAll(StyleAttribute<T> attribute) {
    int attributePointer = myAttributes.search(attribute.getIndex());
    return TopLevelStyleMap.isEmpty(attributePointer) ? null : myAttributes.getDiscardNullReplaced(attribute, attributePointer);
  }

  @Override
  @Nullable
  public <T> Collection<IntPair<T>> getAllCached(StyleAttribute<T> attribute) {
    if (StyleAttributes.isSimple(attribute)) {
      int attributePointer = myAttributes.search(attribute.getIndex());
      return TopLevelStyleMap.isEmpty(attributePointer) ? null : (Collection) myAttributes.getAll(attribute, attributePointer);
    } else {
      int cachedAttributePointer = myCachedAttributes.search(attribute.getIndex());
      return TopLevelStyleMap.isEmpty(cachedAttributePointer) ? null : (Collection) myCachedAttributes.getAll(attribute, cachedAttributePointer);
    }
  }

  @Override
  public <T> boolean isSpecified(StyleAttribute<T> attribute) {
    return !TopLevelStyleMap.isEmpty(myAttributes.search(attribute.getIndex()));
  }

  @Override
  public Set<StyleAttribute> getSpecifiedAttributes() {
    StyleAttributeSet res = new StyleAttributeSet();
    for (int attributeIndex : myAttributes.getIndexes()) {
      res.add(attributeIndex);
    }
    return res;
  }

  @Override
  public void addListener(StyleListener l) {
    if (myStyleListeners == null) {
      myStyleListeners = new ArrayList<>(1);
    }
    myStyleListeners.add(l);
  }

  @Override
  public void removeListener(StyleListener l) {
    if (myStyleListeners == null) {
      return;
    }
    myStyleListeners.remove(l);
    if (myStyleListeners.isEmpty()) {
      myStyleListeners = null;
    }
  }

  private void fireStyleChanged(StyleChangeEvent e) {
    if (myStyleListeners == null) {
      return;
    }
    for (StyleListener l : myStyleListeners) {
      try {
        l.styleChanged(e);
      } catch (Throwable t) {
        LOG.error(null, t);
      }
    }
  }

  @Override
  public void add(Style child) {
    if (myChildren == null) {
      myChildren = new LinkedList<>();
    }
    myChildren.add(child);
    child.setParent(this, getNonDefaultValuedAttributes());
  }

  @Override
  public void remove(Style child) {
    myChildren.remove(child);
    if (myChildren.size() == 0) {
      myChildren = null;
    }
    child.setParent(null, getNonDefaultValuedAttributes());
  }

  @Override
  public void setParent(Style parent, Collection<StyleAttribute> inheritedAttributes) {
    myParent = parent;
    updateCache(inheritedAttributes);
  }

  private Set<StyleAttribute> getNonDefaultValuedAttributes() {
    StyleAttributeSet result = new StyleAttributeSet();
    for (int attributeIndex : myCachedAttributes.getIndexes()) {
      result.add(attributeIndex);
    }
    return result;
  }

  private Style getParentStyle() {
    return myParent;
  }

  private void updateCache(Collection<StyleAttribute> attributes) {
    if (attributes.isEmpty()) {
      return;
    }

    Set<StyleAttribute> changedAttributes = new StyleAttributeSet();
    for (StyleAttribute<Object> attribute : attributes) {
      assert !StyleAttributes.isSimple(attribute);

      int attributePointer = myAttributes.search(attribute.getIndex());
      int cachedAttributePointer = myCachedAttributes.search(attribute.getIndex());

      Collection<IntPair<Object>> parentValues = getParentStyle() == null ? null : getParentStyle().getAllCached(attribute);
      Collection<IntPair<Object>> currentValues = TopLevelStyleMap.isEmpty(attributePointer) ? null : myAttributes.getAll(attribute, attributePointer);
      Collection<IntPair<Object>> oldValues = TopLevelStyleMap.isEmpty(cachedAttributePointer) ? null : myCachedAttributes.getAll(attribute, cachedAttributePointer);

      Iterator<IntPair<Object>> parentIterator = parentValues == null ? new EmptyIterator<>() : parentValues.iterator();
      Iterator<IntPair<Object>> currentIterator = currentValues == null ? new EmptyIterator<>() : currentValues.iterator();

      IntPair<Object> parentValue;
      IntPair<Object> currentValue;

      parentValue = parentIterator.hasNext() ? parentIterator.next() : null;
      currentValue = currentIterator.hasNext() ? currentIterator.next() : null;

      StyleAttributeMap<Object> newValues = new StyleAttributeMap<>();
      while (parentValue != null || currentValue != null ) {

        if (currentValue != null && (parentValue == null || currentValue.index < parentValue.index)) {
          if (!(currentValue.value instanceof DiscardValue)) {
            newValues.setValue(currentValue.index, attribute.combine(null, currentValue.value));
          }
          currentValue = currentIterator.hasNext() ? currentIterator.next() : null;
        } else if (currentValue == null || parentValue.index < currentValue.index) {
          newValues.setValue(parentValue.index, attribute.combine(parentValue.value, null));
          parentValue = parentIterator.hasNext() ? parentIterator.next() : null;
        } else {
          if (!(currentValue.value instanceof DiscardValue)) {
            newValues.setValue(currentValue.index, attribute.combine(parentValue.value, currentValue.value));
          }
          currentValue = currentIterator.hasNext() ? currentIterator.next() : null;
          parentValue = parentIterator.hasNext() ? parentIterator.next() : null;
        }
      }

      Iterator<IntPair<Object>> oldIterator = oldValues == null ? new EmptyIterator<>() : oldValues.iterator();

      Iterator<IntPair<Object>> newIterator = newValues.getAll().iterator();
      while (oldIterator.hasNext() || newIterator.hasNext()) {
        if (newIterator.hasNext() ^ oldIterator.hasNext()) {
          changedAttributes.add(attribute);
          break;
        }
        IntPair<Object> newValue = newIterator.next();
        IntPair<Object> oldValue = oldIterator.next();
        if (newValue.index != oldValue.index || !EqualUtil.equals(newValue.value, oldValue.value)) {
          changedAttributes.add(attribute);
          break;
        }
      }
      myCachedAttributes.set(attribute.getIndex(), cachedAttributePointer, newValues);
    }

    if (!changedAttributes.isEmpty()) {
      if (myChildren != null) {
        for (Style style : myChildren) {
          style.setParent(this, changedAttributes);
        }
      }

      fireStyleChanged(new StyleChangeEvent(this, changedAttributes));
    }
  }
}
