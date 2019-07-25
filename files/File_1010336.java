/*
 * Copyright 2003-2018 JetBrains s.r.o.
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
package jetbrains.mps.smodel.adapter.structure.types;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.smodel.adapter.ids.PrimitiveTypeId;
import jetbrains.mps.smodel.adapter.ids.SDataTypeId;
import jetbrains.mps.smodel.adapter.ids.STypeId;
import jetbrains.mps.smodel.adapter.structure.MetaAdapterFactory;
import jetbrains.mps.smodel.language.ConceptRegistry;
import jetbrains.mps.smodel.runtime.ConstrainedStringDatatypeDescriptor;
import jetbrains.mps.smodel.runtime.DataTypeDescriptor;
import jetbrains.mps.smodel.runtime.EnumerationDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SType;

/**
 * Provisional API.
 *
 * Aimed to supply {@link SType} instances by provided their identities ({@link STypeId}).
 * Currently it handles only fixed set of type kinds (primitive, constrained string, enumeration),
 * however, later it will be possible to register custom type suppliers.
 *
 * @author Radimir.Sorokin
 * @since 2018.3
 */
public final class TypeRegistry implements CoreComponent {

  private static TypeRegistry INSTANCE;

  public static TypeRegistry getInstance() {
    return INSTANCE;
  }

  @Override
  public void init() {
    if (INSTANCE != null) {
      throw new IllegalStateException("double initialization");
    }
    INSTANCE = this;
  }

  @Override
  public void dispose() {
    INSTANCE = null;
  }

  @NotNull
  public SType getType(STypeId id) {
    // FIXME make extensible
    if (id instanceof PrimitiveTypeId) {
      return SPrimitiveTypes.getType((PrimitiveTypeId) id);
    }
    if (id instanceof SDataTypeId) {
      final SDataTypeId dataTypeId = (SDataTypeId) id;
      DataTypeDescriptor descriptor = ConceptRegistry.getInstance().getDataTypeDescriptor(dataTypeId);
      if (descriptor == null) {
        return new InvalidDataType("??? (no descriptor found)");
      }
      if (descriptor instanceof ConstrainedStringDatatypeDescriptor) {
        return MetaAdapterFactory.getConstrainedStringDataType(dataTypeId, descriptor.getName());
      }
      if (descriptor instanceof EnumerationDescriptor) {
        return MetaAdapterFactory.getEnumeration(dataTypeId, descriptor.getName());
      }
      return new InvalidDataType(descriptor.getName() + " (unknown descriptor kind)");
    }
    return new InvalidDataType("??? (unknown type identity kind)");
  }
}
