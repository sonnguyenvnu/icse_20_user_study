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

import jetbrains.mps.core.aspects.behaviour.api.SAbstractType;
import jetbrains.mps.smodel.behaviour.DefaultValuesHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * A type backed by java class.
 */
public class SJavaCompoundTypeImpl implements SJavaCompoundType {

  private final Class<?> myType;

  public SJavaCompoundTypeImpl(@NotNull Class<?> type) {
    myType = type;
  }

  @NotNull
  private Class<?> box(Class<?> type) {
    if (!type.isPrimitive()) {
      return type;
    }
    return BoxingHelper.box(type);
  }

  @Nullable
  @Override
  public Object getDefaultValue() {
    return DefaultValuesHolder.defaultValue(myType);
  }

  /**
   * similar to java implicit conversions
   */
  @Override
  public boolean isAssignableFrom(@NotNull SAbstractType another) {
    if (another instanceof SJavaCompoundType) {
      return BoxingHelper.isAssignableTo(myType, ((SJavaCompoundType) another).getJavaType());
    }
    return false;
  }

  @NotNull
  @Override
  public Class<?> getJavaType() {
    return myType;
  }

  @Override
  public String toString() {
    return "J(" + myType +"):";
  }

  @Override
  public int hashCode() {
    return myType.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof SJavaCompoundType) {
      return myType.equals(((SJavaCompoundType) o).getJavaType());
    }
    return false;
  }

  public static List<SJavaCompoundType> fromClasses(Class<?>... types) {
    List<SJavaCompoundType> result = new ArrayList<>();
    for (Class<?> type : types) {
      result.add(new SJavaCompoundTypeImpl(type));
    }
    return result;
  }
}
