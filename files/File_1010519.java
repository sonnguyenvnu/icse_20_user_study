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
package jetbrains.mps.project;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.smodel.BaseScope;
import jetbrains.mps.util.annotation.ToRemove;
import jetbrains.mps.util.iterable.CollectManyIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.Iterator;

/**
 * Global in a sense 'global for a given repository'. Since we used to have single repository, deemed 'global'.
 */
public class GlobalScope extends BaseScope implements CoreComponent {
  private static GlobalScope INSTANCE;

  /**
   * @deprecated there ain't no such thing as 'global' scope, use {@link #GlobalScope(SRepository)}
   */
  @Deprecated
  @ToRemove(version = 2019.1)
  public static GlobalScope getInstance() {
    return INSTANCE;
  }

  protected final SRepository myRepository;

  public GlobalScope(SRepository moduleRepository) {
    myRepository = moduleRepository;
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

  public String toString() {
    return "global scope";
  }

  @NotNull
  @Override
  public Iterable<SModule> getModules() {
    return myRepository.getModules();
  }

  @NotNull
  @Override
  public Iterable<SModel> getModels() {
    return () -> new CollectManyIterator<SModule, SModel>(getModules()) {
      @Nullable
      @Override
      protected Iterator<SModel> translate(SModule module) {
        return module.getModels().iterator();
      }
    };
  }

  @Override
  public SModule resolve(@NotNull SModuleReference reference) {
    return myRepository.getModule(reference.getModuleId());
  }

  @Override
  public SModel resolve(@NotNull SModelReference reference) {
    return reference.resolve(myRepository);
  }
}
