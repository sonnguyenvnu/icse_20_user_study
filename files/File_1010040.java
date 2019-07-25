/*
 * Copyright 2003-2015 JetBrains s.r.o.
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
package jetbrains.mps.core.aspects.behaviour;

import jetbrains.mps.util.containers.BidirectionalMap;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class BoxingHelper {
  private static final BidirectionalMap<Class, Class> PRIMITIVE_TO_BOXED_TYPE = new BidirectionalMap<>();

  static {
    PRIMITIVE_TO_BOXED_TYPE.put(Character.TYPE, Character.class);
    PRIMITIVE_TO_BOXED_TYPE.put(Byte.TYPE, Byte.class);
    PRIMITIVE_TO_BOXED_TYPE.put(Short.TYPE, Short.class);
    PRIMITIVE_TO_BOXED_TYPE.put(Integer.TYPE, Integer.class);
    PRIMITIVE_TO_BOXED_TYPE.put(Long.TYPE, Long.class);
    PRIMITIVE_TO_BOXED_TYPE.put(Float.TYPE, Float.class);
    PRIMITIVE_TO_BOXED_TYPE.put(Double.TYPE, Double.class);
    PRIMITIVE_TO_BOXED_TYPE.put(Boolean.TYPE, Boolean.class);
    PRIMITIVE_TO_BOXED_TYPE.put(Void.TYPE, Void.class);
  }


  @NotNull
  public static Class<?> box(@NotNull Class<?> primitiveType) {
    assert primitiveType.isPrimitive();
    return PRIMITIVE_TO_BOXED_TYPE.get(primitiveType);
  }

  @NotNull
  public static Class<?> unBox(@NotNull Class<?> boxedType) {
    List<Class> keysByValue = PRIMITIVE_TO_BOXED_TYPE.getKeysByValue(boxedType);
    assert keysByValue != null && keysByValue.size() == 1;
    return keysByValue.get(0);
  }

  public static boolean isBoxedType(@NotNull Class<?> type) {
    return PRIMITIVE_TO_BOXED_TYPE.containsValue(type);
  }

  public static boolean isAssignableTo(@NotNull Class<?> type, @NotNull Class<?> anotherType) {
    if (!type.isPrimitive() && !anotherType.isPrimitive()) {
      return type.isAssignableFrom(anotherType);
    }
    if (BoxingHelper.isBoxedType(type)) {
      type = BoxingHelper.unBox(type);
    }
    if (BoxingHelper.isBoxedType(anotherType)) {
      anotherType = BoxingHelper.unBox(anotherType);
    }
    return type == anotherType;
  }
}
