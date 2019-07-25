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
package jetbrains.mps.findUsages;

import jetbrains.mps.persistence.PersistenceRegistry;
import jetbrains.mps.util.CollectConsumer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.module.SearchScope;
import org.jetbrains.mps.openapi.util.Consumer;
import org.jetbrains.mps.openapi.util.ProgressMonitor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class SearchType<T, R> {
  private static final Logger LOG = LogManager.getLogger(SearchType.class);

  /**
   * @deprecated a client should migrate to the {@link #search(Set, SearchScope, Consumer, ProgressMonitor)} instead
   */
  @Deprecated
  @NotNull
  public Set<T> search(Set<R> elements, SearchScope scope, @NotNull ProgressMonitor monitor) {
    CollectConsumer<T> consumer = new CollectConsumer<>();
    search(elements, scope, consumer, monitor);
    return new LinkedHashSet<>(consumer.getResult());
  }

  public /*abstract*/ void search(Set<R> elements, SearchScope scope, Consumer<T> consumer, @NotNull ProgressMonitor monitor) {
    // default implementation, to be removed
    Set<T> res = search(elements, scope, monitor);
    for (T t : res) {
      consumer.consume(t);
    }
  }

  protected void showNoFastFindTipIfNeeded(Collection<SModel> noFastFindModels) {
    if (!PersistenceRegistry.getInstance().isFastSearch()) {
      return;
    }

    HashSet<SModel> notEmptyNoFastFindModels = new HashSet<>();
    for (SModel m : noFastFindModels) {
      if (m.getRootNodes().iterator().hasNext()) {
        notEmptyNoFastFindModels.add(m);
      }
    }

    if (notEmptyNoFastFindModels.isEmpty()) {
      return;
    }

    int othersSize = notEmptyNoFastFindModels.size() - 1;
    String others = othersSize == 0 ? "" : " and " + othersSize + " others";
    LOG.warn("Fast usages search is not supported for model " + notEmptyNoFastFindModels.iterator().next().getName() + others + ". " +
        "Usages search may be slow.");
  }
}
