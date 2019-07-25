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
package jetbrains.mps.extapi.persistence.datasource;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Internal;
import org.jetbrains.mps.annotations.Mutable;
import org.jetbrains.mps.annotations.Singleton;
import org.jetbrains.mps.openapi.persistence.datasource.DataSourceType;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Service provider to define your own data source factories
 *
 * @see DataSourceFactoryFromName
 * @author apyshkin
 * @since 12/22/16 [3.5]
 */
@Singleton
public final class DataSourceFactoryRuleService implements CoreComponent  {
  private final Deque<DataSourceFactoryRule> myFactoryRules = new LinkedList<>(); // stack
  private static DataSourceFactoryRuleService ourInstance;

  public DataSourceFactoryRuleService() {
  }

  @Override
  public void init() {
    if (ourInstance != null) {
      throw new IllegalStateException("Already initialized");
    }
    ourInstance = this;
  }

  @Override
  public void dispose() {
    ourInstance = null;
  }

  /**
   * @deprecated Use {@link jetbrains.mps.components.ComponentHost#findComponent(Class) MPS standard mechanism} to access component instances
   */
  @NotNull
  @Deprecated
  @ToRemove(version = 2018.2)
  public static synchronized DataSourceFactoryRuleService getInstance() {
    return ourInstance;
  }

  @Internal
  @Mutable
  public void register(@NotNull DataSourceFactoryRule rule) {
    myFactoryRules.addFirst(rule);
  }

  @Internal
  @Mutable
  public void unregister(@NotNull DataSourceFactoryRule rule) {
    myFactoryRules.remove(rule);
  }

  @Nullable
  public synchronized DataSourceFactoryFromName getFactory(@NotNull DataSourceType dataSourceType) {
    for (DataSourceFactoryRule rule : myFactoryRules) {
      DataSourceFactoryFromName result = rule.spawn(dataSourceType);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  @Nullable
  public synchronized DataSourceFactoryFromURL getFactory(@NotNull URL url) {
    for (DataSourceFactoryRule rule : myFactoryRules) {
      DataSourceFactoryFromURL result = rule.spawn(url);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  /**
   * @return factories in the reverse order of registration -- from the newest to the oldest.
   */
  @NotNull
  public synchronized List<DataSourceFactoryRule> getFactoryRules() {
    return Collections.unmodifiableList(new ArrayList<>(myFactoryRules));
  }
}
