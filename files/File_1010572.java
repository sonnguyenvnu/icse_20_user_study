/*
 * Copyright 2003-2011 JetBrains s.r.o.
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
package jetbrains.mps.progress;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.util.ProgressMonitor;
import org.jetbrains.mps.openapi.util.SubProgressKind;

public class ProgressMonitorDecorator implements ProgressMonitor {

  private final ProgressMonitor myDelegate;

  public ProgressMonitorDecorator(ProgressMonitor delegate) {
    this.myDelegate = delegate;
  }

  @Override
  public void start(@NotNull String taskName, int totalWork) {
    myDelegate.start(taskName, totalWork);
  }

  @Override
  public void advance(int work) {
    myDelegate.advance(work);
  }

  @Override
  public void step(String title) {
    myDelegate.step(title);
  }

  @Override
  public void done() {
    myDelegate.done();
  }

  @Override
  public boolean isCanceled() {
    return myDelegate.isCanceled();
  }

  @Override
  public void cancel() {
    myDelegate.cancel();
  }

  @Override
  public ProgressMonitor subTask(int work) {
    return myDelegate.subTask(work);
  }

  @Override
  public ProgressMonitor subTask(int work, SubProgressKind kind) {
    return myDelegate.subTask(work, kind);
  }
}
