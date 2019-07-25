/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.workbench.choose;

import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.util.Computable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.SearchScope;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Gives access to {@linkplain SearchScope scope's} modules as iterable collection of {@link SModuleReference},
 * to use in {@link ChooseByNameData#setScope(Iterable, Iterable)}.
 * @author Artem Tikhomirov
 * @since 3.4
 */
public final class ModuleScopeIterable implements Iterable<SModuleReference> {
  private final SearchScope myScope;
  private final SRepository myRepo;

  public ModuleScopeIterable(@NotNull SearchScope scope, @NotNull SRepository repo) {
    myScope = scope;
    myRepo = repo;
  }

  @Override
  public Iterator<SModuleReference> iterator() {
    List<SModuleReference> refs = new ModelAccessHelper(myRepo).runReadAction((Computable<List<SModuleReference>>) () -> {
      // if we got few modules, it's unlikely we care to use choose by name functionality
      ArrayList<SModuleReference> rv = new ArrayList<>(100);
      for (SModule m : myScope.getModules()) {
        rv.add(m.getModuleReference());
      }
      return rv;
    });
    return refs.iterator();
  }
}
