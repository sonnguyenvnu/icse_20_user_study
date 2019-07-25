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
package jetbrains.mps.generator.impl.template;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tracks visibility context of a variable name, produce name unique in the context.
 * Not thread-safe, deemed for use from single thread. The only non-safe operation is assign/access new name, could be guarded, if necessary.
 *
 * @author Artem Tikhomirov
 */
public final class VariableNameSource {
  private final String myBaseName;
  private final String myName;
  private String myNewName;
  private final AtomicInteger myCounter;

  public VariableNameSource(@NotNull String baseName) {
    this(baseName, new AtomicInteger(0), baseName);
  }

  public VariableNameSource(@NotNull String baseName, @NotNull AtomicInteger counter) {
    this(baseName, counter, baseName);
  }

  public VariableNameSource(@NotNull String baseName, @NotNull AtomicInteger counter, @NotNull String actualName) {
    myBaseName = baseName;
    myName = actualName;
    myCounter = counter;
  }

  /**
   * @return name this source got at construction time, never {@code null}
   */
  public String getActualName() {
    return myName;
  }

  /**
   * @return name latest created with a {@link #newName()} operation, or {@link #getActualName() actual name} if no new name was assigned, never {@code null}
   */
  public String getNewName() {
    if (myNewName != null) {
      return myNewName;
    }
    return myName;
  }

  public boolean hasNewName() {
    return myNewName != null;
  }

  /**
   * Construct and record new variable name, overriding previous one (i.e. result of preceding {@link #newName()} call).
   * @return newly constructed name, never {@code null}
   */
  public String newName() {
    myNewName = myBaseName + myCounter.incrementAndGet();
    return myNewName;
  }

  /**
   * @return name source with {@link #getNewName()} value of the current source as its {@link #getActualName()}.
   */
  public VariableNameSource next() {
    return new VariableNameSource(myBaseName, myCounter, getNewName());
  }
}
