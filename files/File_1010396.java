/*
 * Copyright 2003-2011 JetBrains s.r.o.
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
package jetbrains.mps.validation;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.errors.CheckerRegistry;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.annotations.Nullable;

/**
 * FIXME  I suspect there's no need in settings of IModelValidationSettings these days, therefore CheckerRegistry is kept separate so that
 * we can remove this class once settings are gone. OTOH, CheckerRegistry itself is kind of odd (especially as an MPSCore component), perhaps,
 * is less odd if part of generic 'validation' infrastructure.
 * evgeny, 12/27/11
 */
public class ValidationSettings implements CoreComponent {

  private static ValidationSettings INSTANCE;

  public static ValidationSettings getInstance() {
    return INSTANCE;
  }

  private IModelValidationSettings myModelValidationSettings;

  private final CheckerRegistry myCheckerRegistry;

  public ValidationSettings(CheckerRegistry checkerRegistry) {
    myCheckerRegistry = checkerRegistry;
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

  public IModelValidationSettings getModelValidationSettings() {
    return myModelValidationSettings;
  }

  /**
   * @deprecated {@link CheckerRegistry} is {@link CoreComponent}, use {@link jetbrains.mps.components.ComponentHost#findComponent(Class)} to obtain its instance
   *
   * To my best knowledge, there are no uses of this method in mbeddr.
   */
  @Deprecated
  @ToRemove(version = 2019.1)
  public CheckerRegistry getCheckerRegistry() {
    return myCheckerRegistry;
  }

  public void setModelValidationSettings(@Nullable IModelValidationSettings settings) {
    myModelValidationSettings = settings;
  }
}
