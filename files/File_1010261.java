/*
 * Copyright 2003-2017 JetBrains s.r.o.
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
package jetbrains.mps.extapi.module;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.SRepositoryListener;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * evgeny, 5/21/13
 */
public class SRepositoryRegistry implements CoreComponent {
  private static SRepositoryRegistry INSTANCE;

  private final Object LOCK = new Object();
  private Set<SRepository> myRepositories = new LinkedHashSet<>();
  private Set<SRepositoryListener> myGlobalListeners = new LinkedHashSet<>();

  /**
   * @deprecated Instead, access instance through respective kernel {@link jetbrains.mps.components.ComponentPlugin} (i.e. {@code MPSCore}).
   *             There are no uses in MPS, although there are still few in mbeddr
   */
  @Deprecated
  @ToRemove(version = 2017.2)
  public static SRepositoryRegistry getInstance() {
    return INSTANCE;
  }

  public SRepositoryRegistry() {
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

  public void addRepository(final SRepository repository) {
    // listeners may access content of a newly added repository, hence the read lock
    // Perhaps, caller shall be responsible for this (need to update method API/documentation then).
    repository.getModelAccess().runReadAction(() -> doRepositoryAdd(repository));
  }

  public void removeRepository(final SRepository repository) {
    repository.getModelAccess().runReadAction(() -> doRepositoryRemove(repository));
  }

  void doRepositoryAdd(SRepository repository) {
    synchronized (LOCK) {
      myRepositories.add(repository);
      for (SRepositoryListener l : myGlobalListeners) {
        repository.addRepositoryListener(l);
      }
    }
  }

  void doRepositoryRemove(SRepository repository) {
    synchronized (LOCK) {
      for (SRepositoryListener l : myGlobalListeners) {
        repository.removeRepositoryListener(l);
      }
      myRepositories.remove(repository);
    }
  }

  public void addGlobalListener(final SRepositoryListener listener) {
    synchronized (LOCK) {
      myGlobalListeners.add(listener);
      for (final SRepository r : myRepositories) {
        r.getModelAccess().runReadAction(() -> r.addRepositoryListener(listener));
      }
    }
  }

  public void removeGlobalListener(final SRepositoryListener listener) {
    synchronized (LOCK) {
      for (final SRepository r : myRepositories) {
        r.getModelAccess().runReadAction(() -> r.removeRepositoryListener(listener));
      }
      myGlobalListeners.remove(listener);
    }

  }
}
