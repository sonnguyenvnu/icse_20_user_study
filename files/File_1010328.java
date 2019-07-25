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
package jetbrains.mps.smodel.adapter.structure.property;

import jetbrains.mps.smodel.adapter.ids.STypeId;
import jetbrains.mps.smodel.adapter.structure.FormatException;
import jetbrains.mps.smodel.adapter.structure.types.InvalidDataType;
import jetbrains.mps.smodel.adapter.structure.types.TypeRegistry;
import jetbrains.mps.smodel.runtime.PropertyDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SDataType;
import org.jetbrains.mps.openapi.language.SProperty;
import org.jetbrains.mps.openapi.language.SType;

public abstract class SPropertyAdapter implements SProperty {
  public static final String ID_DELIM = ":";

  protected String myPropertyName;

  public SPropertyAdapter(String name) {
    myPropertyName = name;
  }

  @NotNull
  public abstract SAbstractConcept getOwner();

  @Nullable
  public abstract PropertyDescriptor getPropertyDescriptor();

  @Override
  public boolean isValid() {
    return getPropertyDescriptor() != null;
  }

  @Override
  public String toString() {
    return getName();
  }

  @Override
  @NotNull
  public SDataType getType() {
    PropertyDescriptor pd = getPropertyDescriptor();
    if (pd == null) {
      return new InvalidDataType("??? (no property descriptor)");
    }

    final STypeId dataTypeId = pd.getDataTypeId();
    if (dataTypeId != null) {
      final SType type = TypeRegistry.getInstance().getType(dataTypeId);
      if (type instanceof SDataType) {
        return (SDataType) type;
      }
      return new InvalidDataType(type.toString() + " (not a data type)");
    }

    return new InvalidDataType("??? (type id not specified)");
  }

  @Override
  public boolean isValid(String string) {
    return getType().fromString(string) != SType.NOT_A_VALUE;
  }

  public abstract String serialize();

  public static SPropertyAdapter deserialize(String s) {
    if (s.startsWith(SPropertyAdapterById.PROP_PREFIX)) {
      return SPropertyAdapterById.deserialize(s);
    } else if (s.startsWith(InvalidProperty.INVALID_PREFIX)) {
      return InvalidProperty.deserialize(s);
    } else {
      throw new FormatException("Illegal property type: " + s);
    }
  }
}
