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
package jetbrains.mps.generator.impl;

import jetbrains.mps.generator.GenerationCanceledException;
import jetbrains.mps.generator.impl.IGenerationTaskPool.GenerationTask;
import jetbrains.mps.typechecking.TypecheckingFacade;
import jetbrains.mps.typechecking.backend.TypecheckingSession;
import jetbrains.mps.typechecking.backend.TypecheckingSession.Flags;
import jetbrains.mps.util.Callback;
import org.jetbrains.annotations.NotNull;

/**
 * Wrap {@link GenerationTask} into plain {@link Runnable}.
 * Notifies TypeSystem of task start and finish.
 * @author Artem Tikhomirov
 */
class GenerationTaskAdapter implements Runnable {
  private final GenerationTask myTask;
  private final Callback<Throwable> myExceptionReporter;

  GenerationTaskAdapter(@NotNull GenerationTask task, @NotNull Callback<Throwable> exceptionReporter) {
    myTask = task;
    myExceptionReporter = exceptionReporter;
  }

  @Override
  public void run() {
    TypecheckingSession typecheckingSession = TypecheckingFacade.getFromContext().requestNewSession(Flags.generator());
    try {
      myTask.run();
    } catch (Throwable th) {
      myExceptionReporter.call(th);
    } finally {
      typecheckingSession.release();
    }
  }

  /**
   * Handy code to re-throw exception caught from GenerationTask
   */
  static void rethrow(@NotNull Throwable th) throws GenerationCanceledException, GenerationFailureException {
    if (th instanceof GenerationCanceledException) {
      throw (GenerationCanceledException) th;
    } else if (th instanceof GenerationFailureException) {
      throw (GenerationFailureException) th;
    } else if (th instanceof RuntimeException) {
      throw (RuntimeException) th;
    }
    throw new GenerationFailureException(th);
  }
}
