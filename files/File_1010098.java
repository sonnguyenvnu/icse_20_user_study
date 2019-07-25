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
package jetbrains.mps.findUsages;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.progress.EmptyProgressMonitor;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SReference;
import org.jetbrains.mps.openapi.module.FindUsagesFacade;
import org.jetbrains.mps.openapi.module.SearchScope;
import org.jetbrains.mps.openapi.util.Consumer;
import org.jetbrains.mps.openapi.util.ProgressMonitor;

import java.util.LinkedHashSet;
import java.util.Set;

public class FindUsagesManager extends FindUsagesFacade implements CoreComponent {

  /**
   * @deprecated it's {@link CoreComponent}, use {@link jetbrains.mps.components.ComponentHost#findComponent(Class)} to retrieve an instance
   */
  @Deprecated
  @ToRemove(version = 2018.2)
  public static FindUsagesManager getInstance() {
    return (FindUsagesManager) INSTANCE;
  }

  @Override
  public Set<SReference> findUsages(@NotNull SearchScope scope, @NotNull Set<SNode> nodes, ProgressMonitor monitor) {
    return findUsages0(nodes, new UsagesSearchType(), scope, monitor);
  }

  @Override
  public Set<SNode> findInstances(@NotNull SearchScope scope, @NotNull Set<? extends SAbstractConcept> concepts, boolean exact, ProgressMonitor monitor) {
    return findUsages0(concepts, new InstancesSearchType(exact), scope, monitor);
  }

  @Override
  public Set<SModel> findModelUsages(@NotNull SearchScope scope, @NotNull Set<SModelReference> modelReferences, ProgressMonitor monitor) {
    return findUsages0(modelReferences, new ModelUsagesSearchType(), scope, monitor);
  }

  @Override
  public void findUsages(@NotNull SearchScope scope, @NotNull Set<SNode> nodes, @NotNull Consumer<SReference> consumer, ProgressMonitor monitor) {
    findUsages0(nodes, new UsagesSearchType(), scope, consumer, monitor);
  }

  @Override
  public void findInstances(@NotNull SearchScope scope, @NotNull Set<? extends SAbstractConcept> concepts, boolean exact, @NotNull Consumer<SNode> consumer, ProgressMonitor monitor) {
    findUsages0(concepts, new InstancesSearchType(exact), scope, consumer, monitor);
  }

  @Override
  public void findModelUsages(@NotNull SearchScope scope, @NotNull Set<SModelReference> modelReferences, @NotNull Consumer<SModel> consumer, ProgressMonitor monitor) {
    findUsages0(modelReferences, new ModelUsagesSearchType(), scope, consumer, monitor);
  }

  private <T, R> Set<T> findUsages0(Set<? extends R> elements, SearchType<T, R> type, SearchScope scope, @Nullable ProgressMonitor monitor) {
    if (monitor == null) {
      monitor = new EmptyProgressMonitor();
    }

    return type.search(new LinkedHashSet<>(elements), scope, monitor);
  }

  private <T, R> void findUsages0(Set<? extends R> elements, SearchType<T, R> type, SearchScope scope, Consumer<T> consumer, @Nullable ProgressMonitor monitor) {
    if (monitor == null) {
      monitor = new EmptyProgressMonitor();
    }

    type.search(new LinkedHashSet<>(elements), scope, consumer, monitor);
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
}
