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
package jetbrains.mps.generator;

import jetbrains.mps.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Records all completed generator tasks and their respective status.
 * @author Artem Tikhomirov
 * @since 3.4
 */
public class GenerationTaskRecorder<T extends GeneratorTask> implements GeneratorTaskListener<T> {
  private final GeneratorTaskListener<? super T> myDelegate;
  private final List<Pair<T, GenerationStatus>> myCompletedTasks = new ArrayList<>();

  public GenerationTaskRecorder(@Nullable GeneratorTaskListener<? super T> delegate) {
    myDelegate = delegate;
  }

  @Override
  public void start(@NotNull T task) {
    if (myDelegate != null) {
      myDelegate.start(task);
    }
  }

  @Override
  public void done(@NotNull T task, @NotNull GenerationStatus status) {
    myCompletedTasks.add(new Pair<>(task, status));
    if (myDelegate != null) {
      myDelegate.done(task, status);
    }
  }

  public List<GenerationStatus> getAllRecorded() {
    ArrayList<GenerationStatus> rv = new ArrayList<>(myCompletedTasks.size());
    for (Pair<T, GenerationStatus> p : myCompletedTasks) {
      rv.add(p.o2);
    }
    return rv;
  }

  /**
   * Looks up last recorded generation outcome for the given task
   * @param task what to look up
   * @return most recent generation status for the task, or <code>null</code> if none found.
   */
  @Nullable
  public GenerationStatus getRecorded(T task) {
    GenerationStatus lastStatus = null;
    for (Pair<T, GenerationStatus> p : myCompletedTasks) {
      if (p.o1 == task) {
        lastStatus = p.o2;
        // intentionally keep going, to find the latest.
        // Collections.reverse would walk the list once anyway, why bother then?
      }
    }
    return lastStatus;
  }
}
