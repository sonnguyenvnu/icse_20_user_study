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
package jetbrains.mps.make;

import jetbrains.mps.util.performance.IPerformanceTracer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.util.ProgressMonitor;
import org.jetbrains.mps.openapi.util.SubProgressKind;

/**
 * A composite which traces performance and also updates the progress monitor if it is presented
 *
 * Created by apyshkin on 5/26/16.
 */
public final class CompositeTracer {
  private final IPerformanceTracer myTracer;
  private final MessageSender mySender;
  private String myCurrentStartMsg;

  @Nullable private final ProgressMonitor myMonitor;

  CompositeTracer(@NotNull IPerformanceTracer tracer, @NotNull MessageSender sender) {
    myTracer = tracer;
    mySender = sender;
    myMonitor = null;
  }

  CompositeTracer(@NotNull CompositeTracer tracer, @Nullable ProgressMonitor monitor) {
    myTracer = tracer.myTracer;
    mySender = tracer.mySender;
    myMonitor = monitor;
  }

  public void start(@NotNull String startMsg, int stepsCount) {
    myCurrentStartMsg = startMsg;
    if (!startMsg.isEmpty()) {
      mySender.trace(startMsg);
      myTracer.push(startMsg);
    }
    if (myMonitor != null) {
      myMonitor.start(startMsg, stepsCount);
    }
  }

  /**
   * composite action to print the msg to log, to the performance tracer and to the ui
   */
  public void push(@NotNull String msg) {
    mySender.trace(msg);
    myTracer.push(msg);
    if (myMonitor != null) {
      myMonitor.step(msg);
    }
  }

  public void pop() {
    pop(0);
  }

  public void pop(int work) {
    myTracer.pop();
    if (myMonitor != null) {
      myMonitor.advance(work);
    }
  }

  public boolean isMonitorCanceled() {
    return myMonitor != null && myMonitor.isCanceled();
  }

  public void done() {
    done(0);
  }

  public void done(int work) {
    if (myMonitor != null) {
      myMonitor.advance(work);
      myMonitor.done();
    }
    if (!myCurrentStartMsg.isEmpty()) {
      myTracer.pop();
    }
  }

  @Nullable
  public String getReport() {
    return myTracer.report();
  }

  @NotNull
  public CompositeTracer subTracer(int size) {
    return subTracer(size, SubProgressKind.DEFAULT);
  }

  public void advance(int i) {
    if (myMonitor != null) {
      myMonitor.advance(i);
    }
  }

  @NotNull
  public CompositeTracer subTracer(int size, SubProgressKind kind) {
    ProgressMonitor monitor = null;
    if (myMonitor != null) {
      monitor = myMonitor.subTask(size, kind);
    }
    return new CompositeTracer(this, monitor);
  }

  public MessageSender getSender() {
    return mySender;
  }

  public void printReport() {
    final String report = getReport();
    if (report != null) {
      mySender.info(report);
    }
  }
}
