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
package jetbrains.mps.smodel.event;

import jetbrains.mps.extapi.model.SNodeBatchChangeListener;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.event.AbstractModelChangeEvent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Mechanism to track {@link jetbrains.mps.extapi.model.SNodeBatchChangeListener} and dispatch events to them
 * @author Artem Tikhomirov
 */
public class BatchChangeEventDispatch {
  private final List<SNodeBatchChangeListener> myListeners = new CopyOnWriteArrayList<>();

  public void add(@Nullable SNodeBatchChangeListener l) {
    if (l != null) {
      myListeners.add(l);
    }
  }

  public void remove(@Nullable SNodeBatchChangeListener l) {
    if (l != null) {
      myListeners.remove(l);
    }
  }

  public void dispatch(@Nullable Collection<AbstractModelChangeEvent> events) {
    if (events == null || events.isEmpty()) {
      return;
    }
    for (SNodeBatchChangeListener l : myListeners) {
      l.processEvents(events);
    }
  }
}
