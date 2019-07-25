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
package jetbrains.mps.classloading;

import jetbrains.mps.smodel.SRepositoryBatchListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.event.SRepositoryEvent;
import org.jetbrains.mps.openapi.repository.WriteActionListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The class is responsible for listening {@link org.jetbrains.mps.openapi.module.SRepositoryListener} events like
 * moduleAdded,
 * moduleRemoved,
 * etc.,
 * and dispatching batch (group) events to listeners.
 * It listens the start and finish of write action. It starts batching events at the start of write action
 * and yields the result at the end of write action.
 * <p/>
 * The class also supports pause/resume scenario to postpone dispatch of collected events to an appropriate moment.
 * In fact, it's rather non-knowingly-inappropriate moment, as any first write action after resume dispatches all collected events.
 *
 * @see org.jetbrains.mps.openapi.module.ModelAccess#runWriteAction(Runnable)
 */
public class ModuleEventsDispatcher implements WriteActionListener {
  private final BatchEventsProcessor myBatchEventsProcessor;

  private final List<SRepositoryBatchListener> myListeners = new CopyOnWriteArrayList<>();

  private final SRepository myRepository;

  private final AtomicBoolean myPaused = new AtomicBoolean(false);

  public ModuleEventsDispatcher(@NotNull SRepository repository) {
    myRepository = repository;
    myBatchEventsProcessor = new BatchEventsProcessor(repository);
  }

  public void init() {
    myBatchEventsProcessor.init();
    myRepository.getModelAccess().addWriteActionListener(this);
  }

  public void dispose() {
    myRepository.getModelAccess().removeWriteActionListener(this);
    myBatchEventsProcessor.dispose();
  }

  public void pause() {
    myPaused.compareAndSet(false, true);
  }

  public void proceed() {
    myPaused.set(false);
  }

  @Override
  public void actionStarted() {
    myBatchEventsProcessor.startBatching();
  }

  @Override
  public void actionFinished() {
    try {
      if (myPaused.get()) {
        return;
      }
      List<SRepositoryEvent> batchedEvents;
      do {
        batchedEvents = myBatchEventsProcessor.flush();
        if (!batchedEvents.isEmpty()) {
          fireModuleEvents(batchedEvents);
        }
      } while (!batchedEvents.isEmpty());
    } finally {
      myBatchEventsProcessor.finishBatching();
    }
  }

  public boolean flush() {
    if (myPaused.get()) {
      return false;
    }
    final List<SRepositoryEvent> batchedEvents = myBatchEventsProcessor.flush();
    if (batchedEvents.isEmpty()) return false;

    myRepository.getModelAccess().checkWriteAccess();
    fireModuleEvents(batchedEvents);
    return true;
  }

  public final void addRepositoryBatchEventsListener(SRepositoryBatchListener listener) {
    myListeners.add(listener);
  }

  public final void removeRepositoryBatchEventsListener(SRepositoryBatchListener listener) {
    myListeners.remove(listener);
  }

  private void fireModuleEvents(List<SRepositoryEvent> events) {
    myRepository.getModelAccess().checkWriteAccess();
    for (SRepositoryBatchListener listener : myListeners) {
      listener.eventsHappened(events);
    }
  }
}
