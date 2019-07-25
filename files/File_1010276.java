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
package jetbrains.mps.extapi.persistence;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.util.annotation.ToRemove;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Internal;
import org.jetbrains.mps.annotations.Mutable;
import org.jetbrains.mps.annotations.Singleton;
import org.jetbrains.mps.openapi.persistence.ModelFactory;
import org.jetbrains.mps.openapi.persistence.ModelFactoryType;
import org.jetbrains.mps.openapi.persistence.datasource.DataSourceType;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Unlike {@link ModelFactoryRegistry}
 * this class has special setter methods to allow workbench model factory extensions.
 * These are going to disappear as API in the 3.6 version, since the public access is unnecessary.
 *
 * NB: every method work with the last-added priority -- it acts like a model factory stack
 *
 * NB: LEGACY registrations are not available here (though the opposite is a guarantee).
 * Thus when one uses this API he/she must be strongly convinced that the provider also used
 * this API not the LEGACY one
 *
 * @see org.jetbrains.mps.openapi.persistence.PersistenceFacade
 *
 * @author apyshkin
 * @since 29/12/16
 */
@Singleton
@Mutable
public final class ModelFactoryService implements ModelFactoryRegistry, CoreComponent {
  private static final Logger LOG = LogManager.getLogger(ModelFactoryService.class);

  private static ModelFactoryService ourInstance;

  private final List<ModelFactory> myCustomModelFactories = new CopyOnWriteArrayList<>();

  public ModelFactoryService() {
  }

  @Override
  public void init() {
    ourInstance = this;
  }

  @Override
  public void dispose() {
    ourInstance = null;
  }

  /**
   * @deprecated access through {@link jetbrains.mps.components.ComponentHost#findComponent(Class)}
   */
  @NotNull
  @Deprecated
  @ToRemove(version = 2018.3)
  public static synchronized ModelFactoryService getInstance() {
    return ourInstance;
  }

  /**
   * @internal please do not invoke these two methods -- the only caller is supposed to be ModelFactoryRegister$ModelFactoryProvider
   * whose purpose is pushing up the model factories registered via IDEA extension points mechanism
   *
   * it is lowering its visibility after 3.6
   * AP
   */
  @Internal
  @Mutable
  public void register(@NotNull ModelFactory factory) {
    if (myCustomModelFactories.contains(factory)) {
      LOG.error(String.format("Model factory '%s' is already registered", factory), new Throwable());
      return;
    }
    myCustomModelFactories.add(factory);
  }

  @Internal
  @Mutable
  public void unregister(@NotNull ModelFactory factory) {
    if (!myCustomModelFactories.contains(factory)) {
      LOG.error(String.format("Model factory '%s' is not found", factory), new Throwable());
      return;
    }
    myCustomModelFactories.remove(factory);
  }

  @NotNull
  private ModelFactoryRegistry createComposite() {
    return new ModelFactoryRegistryInt(myCustomModelFactories);
  }

  /**
   * @return factories in the reverse order of registration -- from the newest to the oldest.
   */
  @NotNull
  public List<ModelFactory> getFactories() {
    return createComposite().getFactories();
  }

  @Nullable
  @Override
  public ModelFactory getFactoryByType(@NotNull ModelFactoryType factoryId) {
    return createComposite().getFactoryByType(factoryId);
  }

  @Nullable
  @Override
  public ModelFactory getDefaultModelFactory(@NotNull DataSourceType dataSourceType) {
    return createComposite().getDefaultModelFactory(dataSourceType);
  }

  @NotNull
  @Override
  public List<ModelFactory> getModelFactories(@NotNull DataSourceType dataSourceType) {
    return createComposite().getModelFactories(dataSourceType);
  }

  @NotNull
  @Override
  public List<ModelFactoryType> getFactoryTypes() {
    return createComposite().getFactoryTypes();
  }
}
