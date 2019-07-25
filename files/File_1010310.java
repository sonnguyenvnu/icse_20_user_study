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
package jetbrains.mps.project.structure.modules;

import jetbrains.mps.project.structure.modules.mappingpriorities.MappingPriorityRule;
import jetbrains.mps.util.StatefulUpdate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Mutable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static jetbrains.mps.smodel.SModelReference.differs;

/**
 * Model/module name might get changed, and to reflect actual name in descriptor files, we 'update' references from time to time,
 * by resolving them into actual objects and re-obtaining the reference.
 */
public final class RefUpdateUtil {
  private final SRepository myRepository;

  /**
   * @param repository where to look actual modules/models up
   */
  public RefUpdateUtil(@NotNull SRepository repository) {
    myRepository = repository;
  }

  public boolean updateModelRefs(@Mutable Collection<SModelReference> refs) {
    Set<SModelReference> remove = new HashSet<>();
    Set<SModelReference> add = new LinkedHashSet<>();

    for (SModelReference ref : refs) {
      SModelReference newRef = updateModelRef(ref);
      if (newRef != null) {
        remove.add(ref);
        add.add(newRef);
      }
    }

    refs.removeAll(remove);
    refs.addAll(add);

    return !remove.isEmpty();
  }

  /**
   * @return <code>null</code> if modelRef is unchanged or could not be updated, not <code>null</code> iff actual reference is different
   */
  @Nullable
  public SModelReference updateModelRef(@NotNull SModelReference modelRef) {
    final SModel resolved = modelRef.resolve(myRepository);
    if (resolved == null) {
      return null;
    }
    final SModelReference actualModelRef = resolved.getReference();
    return differs(actualModelRef, modelRef) ? actualModelRef : null;
  }

  public boolean updateModuleRefs(Collection<SModuleReference> refs) {
    Set<SModuleReference> remove = new HashSet<>();
    Set<SModuleReference> add = new LinkedHashSet<>();

    for (SModuleReference ref : refs) {
      SModuleReference newRef = update(ref);
      if (ModuleReference.differs(ref, newRef)) {
        remove.add(ref);
        add.add(newRef);
      }
    }

    refs.removeAll(remove);
    refs.addAll(add);

    return !remove.isEmpty();
  }

  public<T> boolean updateModuleRefs(Map<SModuleReference, T> refs) {
    Set<SModuleReference> remove = new HashSet<>();
    Map<SModuleReference, T> add = new LinkedHashMap<>();

    for (Entry<SModuleReference, T> ref : refs.entrySet()) {
      SModuleReference newRef = update(ref.getKey());
      if (ModuleReference.differs(ref.getKey(), newRef)) {
        remove.add(ref.getKey());
        add.put(newRef, ref.getValue());
      }
    }

    refs.keySet().removeAll(remove);
    refs.putAll(add);

    return !remove.isEmpty();
  }

  // Perhaps, RUU could be kept generic, and ModuleDescriptor would take care of the owner class (Dependency) then?
  public boolean updateDependencies(Collection<Dependency> deps) {
    boolean changed = false;
    for (Dependency dep : deps) {
      SModuleReference ref = dep.getModuleRef();
      @NotNull SModuleReference newRef = update(ref);
      if (ModuleReference.differs(ref, newRef)) {
        changed = true;
        dep.setModuleRef(newRef);
      }
    }
    return changed;
  }

  public boolean updateMappingPriorityRules(List<MappingPriorityRule> rules) {
    boolean changed = false;
    for (MappingPriorityRule rule : rules) {
      boolean result = rule.updateReferences(myRepository);
      changed = changed || result;
    }
    return changed;
  }

  public static boolean composeUpdates(boolean... values) {
    boolean changed = false;
    for (boolean v : values) {
      if (v) changed = true;
    }
    return changed;
  }

  // could be public if we decide this class shall be generic and do not deal with Dependency (@see #updateDependencies())
  private SModuleReference update(SModuleReference reference) {
    SModule module = reference.resolve(myRepository);
    return module == null ? reference : module.getModuleReference();
  }

  public StatefulUpdate<SModelReference> withState() {
    return new StatefulUpdate<>(this::updateModelRef);
  }

}
