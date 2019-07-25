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

import jetbrains.mps.core.aspects.behaviour.api.BHDescriptor;
import jetbrains.mps.core.aspects.behaviour.api.SMethod;
import jetbrains.mps.core.aspects.behaviour.api.SMethodId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Primitive representation of the virtual behavior methods table.
 * Contains a mapping from a SMethodId (which are the same for the overriding virtual methods)
 * to the concrete SMethod<?> with implementation (SMethod identifies its location up to a concept so that two overriding virtual methods are different for two concepts)
 *
 * Created by apyshkin on 28/07/15.
 */
public final class SMethodVirtualTable {
  // contains all virtual
  // pointing to the concrete SMethod in a concrete BHDescriptor
  private final Map<SMethodId, SMethod<?>> myIdToImplementationTable = new HashMap<>();

  SMethodVirtualTable() {
  }

  SMethodVirtualTable(@NotNull List<SMethod<?>> methods) {
    for (SMethod<?> method : methods) {
      if (method.isVirtual()) {
        myIdToImplementationTable.put(method.getId(), method);
      }
    }
  }

  /**
   * @param methodId -- the id of the virtual method;
   * @return corresponding SMethod or null if the virtual table does not contain the id
   */
  @Nullable
  public SMethod<?> get(@NotNull SMethodId methodId) {
    return myIdToImplementationTable.get(methodId);
  }

  /**
   * merges a descriptor into the vTable
   * mainly a method from 'another' descriptor is merged into <code>myIdToImplementationTable</code> if only
   * there is no any record in it yet
   */
  public void merge(@NotNull final BHDescriptor descriptor) {
    for (SMethod method : descriptor.getDeclaredMethods()) {
      if (method.isVirtual()) {
        SMethodId id = method.getId();
        if (!myIdToImplementationTable.containsKey(id) || myIdToImplementationTable.get(id).isAbstract()) {
          myIdToImplementationTable.put(id, method);
        }
      }
    }
  }

  /**
   * @return all the virtual methods including the abstract ones
   */
  @NotNull
  public Set<SMethod<?>> getMethods() {
    return new HashSet<>(myIdToImplementationTable.values());
  }
}
