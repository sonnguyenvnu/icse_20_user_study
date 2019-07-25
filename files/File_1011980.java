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

import jetbrains.mps.VisibleModuleRegistry;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.util.Condition;

/**
 * Filters 'visible' modules through VisibleModuleRegistry.
 * Since VisibleModuleRegistry is in mps-platform, can't move this to kernel/smodel modules
 * @author Artem Tikhomirov
 */
public class VisibleModuleCondition implements Condition<SModule> {
  private final VisibleModuleRegistry myVisibleModuleRegistry;

  public VisibleModuleCondition() {
    myVisibleModuleRegistry = VisibleModuleRegistry.getInstance();
  }

  @Override
  public boolean met(SModule m) {
    return myVisibleModuleRegistry.isVisible(m);
  }
}
