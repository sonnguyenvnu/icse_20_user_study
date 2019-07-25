/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.smodel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.ModelAccess;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.SRepositoryListener;

/**
 * Handy facility to (un)register a listener to a repository, with respect to model read access constraint.
 * Not exposed in openapi as I'm not sure why we don't allow to attach listeners from anywhere (we need RA to walk modules, however SRepository
 * could grab read access as needed)
 */
public class RepoListenerRegistrar implements Runnable {
  // XXX could use name Plug instead of Registrar.
  private final SRepository myRepo;
  private final SRepositoryListener myListener;
  private boolean myIsAttach = false;

  public RepoListenerRegistrar(@NotNull SRepository repo, @NotNull SRepositoryListener ca) {
    myRepo = repo;
    myListener = ca;
  }

  @Override
  public void run() {
    if (myIsAttach) {
      myRepo.addRepositoryListener(myListener);
    } else {
      myRepo.removeRepositoryListener(myListener);
    }
  }

  public void attach() {
    myIsAttach = true;
    final ModelAccess ma = myRepo.getModelAccess();
    if (ma.canRead()) {
      run();
    } else {
      ma.runReadAction(this);
    }
  }

  public void detach() {
    myIsAttach = false;
    final ModelAccess ma = myRepo.getModelAccess();
    if (ma.canRead()) {
      run();
    } else {
      ma.runReadAction(this);
    }
  }
}
