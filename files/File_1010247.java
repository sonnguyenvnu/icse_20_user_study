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
package jetbrains.mps.classloading;

import jetbrains.mps.module.ReloadableModuleBase.SModuleDependenciesListener;
import jetbrains.mps.module.ReloadableModuleBase;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.SRepositoryContentAdapter;
import org.jetbrains.mps.openapi.module.SRepositoryListener;
import org.jetbrains.mps.openapi.module.event.SModuleAddedEvent;
import org.jetbrains.mps.openapi.module.event.SModuleChangedEvent;
import org.jetbrains.mps.openapi.module.event.SModuleRemovedEvent;
import org.jetbrains.mps.openapi.module.event.SRepositoryEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class transforms non-batched events to batch events
 * It listens to usual SRepositoryListener events and groups them into SRepositoryEvents
 * not thread-safe
 */
public class BatchEventsProcessor {
  private static Logger LOG = LogManager.getLogger(BatchEventsProcessor.class);
  private volatile boolean myBatchStarted = false;

  private static final Object LOCK = new Object();

  private final List<SRepositoryEvent> myEvents = new ArrayList<>();

  private final SRepositoryListener myRepositoryListener = new MySRepositoryListener();

  private final SRepository myRepository;

  public BatchEventsProcessor(SRepository repository) {
    myRepository = repository;
  }

  public void startBatching() {
    if (myBatchStarted) {
      myBatchStarted = false;
      LOG.error("Batching has been already started; Ignoring...", new IllegalStateException());
    }
    if (!myEvents.isEmpty()) {
      LOG.warn("Events have not been flushed");
    }
    myBatchStarted = true;
  }

  /**
   * flushes all accumulated events
   * stops listening to SRepository, if no new events are discovered
   * @return result of batching: a list of SRepositoryEvents
   */
  public List<SRepositoryEvent> flush() {
    synchronized (LOCK) {
      if (!myBatchStarted) {
        return Collections.emptyList();
      }
      List<SRepositoryEvent> result = new ArrayList<>(myEvents);
      myEvents.clear();
      return result;
    }
  }

  public void finishBatching() {
    if (!myBatchStarted) {
      LOG.error("Batching has not even been started; Ignoring...", new IllegalStateException());
    }
    myBatchStarted = false;
  }

  public void dispose() {
    myRepository.removeRepositoryListener(myRepositoryListener);
  }

  public void init() {
    myRepository.addRepositoryListener(myRepositoryListener);
  }

  /**
   *  This class listens for module's add/removal, for 'moduleChanged' event (triggered by AbstractModule)
   *  and for internal (so far) 'dependenciesChanged' event.
   */
  private class MySRepositoryListener extends SRepositoryContentAdapter implements SModuleDependenciesListener {
    private void addEventToList(@NotNull SRepositoryEvent event) {
      synchronized (LOCK) {
        myEvents.add(event);
      }
    }

    @Override
    public void moduleAdded(@NotNull SModule module) {
      if (!myBatchStarted) return;
      if (module instanceof ReloadableModuleBase) {
        module.addModuleListener(this);
        ((ReloadableModuleBase) module).addDependenciesListener(this);
      }
      addEventToList(new SModuleAddedEvent(module));
    }

    @Override
    public void beforeModuleRemoved(@NotNull SModule module) {
      if (!myBatchStarted) return;
      if (module instanceof ReloadableModuleBase) {
        ((ReloadableModuleBase) module).removeDependenciesListener(this);
        module.removeModuleListener(this);
      }
    }

    @Override
    public void moduleRemoved(@NotNull SModuleReference mRef) {
      if (!myBatchStarted) return;
      addEventToList(new SModuleRemovedEvent(mRef, myRepository));
    }

    @Override
    public void moduleChanged(@NotNull SModule module) {
      if (!myBatchStarted) return;
      addEventToList(new SModuleChangedEvent(module));
    }

    @Override
    public void dependenciesChanged(@NotNull ReloadableModuleBase module) {
      if (!myBatchStarted) return;
      moduleChanged(module);
    }
  }
}
