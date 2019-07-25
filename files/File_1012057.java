/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package jetbrains.mps.ide.ui.dialogs.properties.input;

import jetbrains.mps.util.Computable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;

import java.util.ArrayList;
import java.util.List;

/**
 * SModule to SModuleReference
 * @author Artem Tikhomirov
 */
public class ModuleCollector implements Computable<List<SModuleReference>> {
  private final Iterable<SModule> myModules;

  public ModuleCollector(@NotNull Iterable<SModule> modules) {
    myModules = modules;
  }

  @Override
  public List<SModuleReference> compute() {
    ArrayList<SModuleReference> rv = new ArrayList<>(50);
    for (SModule m : myModules) {
      rv.add(m.getModuleReference());
    }
    return rv;
  }
}
