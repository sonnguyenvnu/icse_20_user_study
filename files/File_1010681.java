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
package jetbrains.mps.util.performance;

import jetbrains.mps.util.annotation.ToRemove;

/**
 * Interface which offers a cosy stack-like methods to track the performance (e.g. time consumption)
 *
 * Evgeny Gryaznov, Feb 23, 2010
 */
public interface IPerformanceTracer {

  /**
   * @deprecated parameter {@code isMajor} is useless, use {@link #push(String)}
   */
  @Deprecated
  @ToRemove(version = 2018.2)
  void push(String taskName, boolean isMajor);

  default void push(String taskName) {
    push(taskName, false);
  }

  /**
   * Include trace information from another instance as part of active task.
   * Might come handy to overcome limitations of single-threaded implementation (e.g. generator's main thread with a
   * 'global' tracer, and individual transformation threads reporting into own trace, with results merged).
   * @param other not null
   */
  void push(IPerformanceTracer other);

  void pop();

  /**
   * @param separate name of tasks not to get merged with other and reported individually
   * @return multi-line report text
   */
  String report(String... separate);

  void addText(String s);

  /**
   * Default implementation which tracks nothing
   */
  final class NullPerformanceTracer implements IPerformanceTracer {

    @Override
    public void push(String taskName, boolean isMajor) {
    }

    @Override
    public void push(String taskName) {
    }

    @Override
    public void push(IPerformanceTracer other) {
    }

    @Override
    public void pop() {
    }

    @Override
    public String report(String... separate) {
      return null;
    }

    @Override
    public void addText(String s){
    }
  }
}
