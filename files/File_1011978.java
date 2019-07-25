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
package jetbrains.mps.project;

import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.util.Condition;

/**
 * Check SModule instance for particular subclass (Generator, Solution, Language, DevKit).
 * Useful to filter sequence of modules.
 * Perhaps, shall live in project module, next to SModule/Language/DevKit stuff
 * @author Artem Tikhomirov
 */
public class ModuleInstanceCondition implements Condition<SModule> {
  private final Class<? extends SModule>[] myModuleClasses;

  public ModuleInstanceCondition(Class<? extends SModule>... moduleClasses) {
    myModuleClasses = moduleClasses;
  }

  @Override
  public boolean met(SModule m) {
    for (Class<? extends SModule> c : myModuleClasses) {
      if (c.isInstance(m)) {
        return true;
      }
    }
    return false;
  }
}
