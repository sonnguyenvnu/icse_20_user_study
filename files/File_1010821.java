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

import jetbrains.mps.openapi.editor.style.Style.IntPair;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Mihail.Buryakov
 * Date: 11/28/13
 */
class StyleAttributeMap<T> extends StyleMap<Object> {

  public static class DiscardValue {
    private static DiscardValue myInstance;

    private DiscardValue() {

    }

    public static DiscardValue getInstance() {
      if (myInstance == null) {
        myInstance = new DiscardValue();
      }
      return myInstance;
    }
  }

  public static boolean isStyleAttributeMapEmpty(int topLevelPointer, int pointer, int index) {
    if (pointer == -1) {
      return index != 0 || TopLevelStyleMap.isEmpty(topLevelPointer);
    } else {
      return StyleMap.isEmpty(pointer);
    }
  }

  public static Object get(Object styleMapOrValue, TopLevelStyleMap topLevelStyleMap, int topLevelPointer, int pointer) {
    if (!(styleMapOrValue instanceof StyleAttributeMap)) {
      return topLevelStyleMap.get(topLevelPointer);
    } else {
      StyleAttributeMap styleMap = (StyleAttributeMap) styleMapOrValue;
      return styleMap.get(pointer);
    }
  }

  public static int search(Object styleMapOrValue, int index) {
    if (!(styleMapOrValue instanceof StyleAttributeMap)) {
      return -1;
    } else {
      StyleAttributeMap styleMap = (StyleAttributeMap) styleMapOrValue;
      return styleMap.search(index);
    }
  }

  public Collection<IntPair<Object>> getAll() {
    ArrayList<IntPair<Object>> result = new ArrayList<>(indexes.length);
    for (int i = 0; i < indexes.length; i++) {
      result.add(new IntPair<>(indexes[i], values[i]));
    }
    return result;
  }

  public Collection<IntPair<T>> getDiscardNullReplaced() {
    ArrayList<IntPair<T>> result = new ArrayList<>(indexes.length);
    for (int i = 0; i < indexes.length; i++) {
      if (values[i] instanceof DiscardValue) {
        result.add(new IntPair<>(indexes[i], null));
      } else {
        result.add(new IntPair<>(indexes[i], (T) values[i]));
      }
    }
    return result;
  }

  public void setValue(Object value) {
    setValue(0, value);
  }

}
