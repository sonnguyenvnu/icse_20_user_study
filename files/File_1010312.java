/*
 * Copyright 2003-2019 JetBrains s.r.o.
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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Reentrant action execution, with notification on first and last action.
 * Use {@link #dispatch(Runnable)} to execute {@link Runnable action} with proper event dispatching.
 * If you need to postpone execution (and event dispatching), you can get appropriate runnable with {@link #wrap(Runnable)}.
 *
 * Implementation doesn't assume single thread {@linkplain #dispatch(Runnable) dispatches}, though is not completely thread-safe.
 * {@code onActionStart} is notified prior to first action of a first thread that asked for {@code dispatch} and
 * {@code onActionFinish} is notified after the last {@code dispatch} is over.
 * FIXME However, note that there may be another read started from other thread the moment finish events are dispatched for the other thread.
 * FIXME       Besides, there are chances to get into read action prior to the moment event dispatch is over (thread-1 starts read and fires events, thread-2
 * FIXME       starts meanwhile and runs read.
 *             Now there is only 1 listener that may get affected, still needs to address this defect. Need a synch primitive to ensure notifications are out
 *             the moment any other read gets their chance to run. Barrier, semaphore? Perhaps, split single-threaded impl to keep it simple?
 *
 *
 * @param <T> listener to notify
 *
 * @author Artem Tikhomirov
 * @since 2017.3
 */
/*package*/ final class ActionDispatcher<T> {
  private final Logger LOG = LogManager.getLogger(ActionDispatcher.class);
  private final List<T> myListeners = new CopyOnWriteArrayList<>();
  private final Consumer<T> myOnActionStart;
  private final Consumer<T> myOnActionFinish;
  private final DispatchController myDispatchController;
  private final AtomicInteger myActionLevel = new AtomicInteger(0);

  // all arguments are non-null
  public ActionDispatcher(Consumer<T> onActionStart, Consumer<T> onActionFinish) {
    myDispatchController = new DispatchController() {};
    myOnActionStart = onActionStart;
    myOnActionFinish = onActionFinish;
  }

  // all arguments are non-null
  public ActionDispatcher(DispatchController controller, Consumer<T> onActionStart, Consumer<T> onActionFinish) {
    myDispatchController = controller;
    myOnActionStart = onActionStart;
    myOnActionFinish = onActionFinish;
  }

  /**
   * Execute an action with notification about action started/finished dispatching for top-most action.
   * @param r action to execute
   */
  public void dispatch(Runnable r) {
    final boolean traceEnabled = LOG.isTraceEnabled();
    final int actionLevel = myActionLevel.getAndIncrement();
    try {
      if (actionLevel == 0) {
        // in case listener fails, shall get into final to ensure myActionLevel is correct
        onActionStarted();
      }
      if (traceEnabled) {
        // I want trace to report actionLevel consistently (for multi-threaded run, myActionLevel.get() gives actual value, not the stable one)
        LOG.trace(String.format("Action started (level:%d)", actionLevel));
      }
      try {
        r.run();
      } catch (RuntimeException ex) {
        // re-throw an exception, if any, just to let user code to use exceptions to control code flow (alas, we can't prevent this unfortunate practice)
        // but at least mention it in the log in case it's not a control flow and there are chances for the exception to get obscured by a subsequent one.
        logUnexpectedRuntimeException(ex);
        throw ex;
      }
    } finally {
      if (traceEnabled) {
        LOG.trace(String.format("Action finished (level:%d)", actionLevel));
      }
      if (myActionLevel.decrementAndGet() == 0) {
        onActionFinished();
      }
    }
  }

  /**
   * @param r original action to {@linkplain #dispatch(Runnable) dispatch}
   * @return a runnable, which, when executed, will {@linkplain #dispatch(Runnable) dispatch} original action.
   */
  public Runnable wrap(final Runnable r) {
    return () -> dispatch(r);
  }

  private void logUnexpectedRuntimeException(RuntimeException ex) {
    // we need this catch not to obscure errors during run with errors from finally block (e.g. if both onActionStarted and onActionFinished fail with
    // exception, we would observe only the last one from onActionFinished)
    // FWIW, there's no scenario behind actionLevel printout here, just in case it yields anything interesting.
    LOG.error(String.format("Action dispatch failed (level:%d)", myActionLevel.get()), ex);
  }

  private void onActionStarted() {
    try {
      myDispatchController.onActionStart();
      myListeners.forEach(myOnActionStart);
    } catch (RuntimeException ex) {
      logUnexpectedRuntimeException(ex);
      throw ex;
    }
  }

  private void onActionFinished() {
    try {
      myListeners.forEach(myOnActionFinish);
      myDispatchController.onActionFinish();
    } catch (RuntimeException ex) {
      logUnexpectedRuntimeException(ex);
      throw ex;
    }
  }

  public boolean isInsideAction() {
    return myActionLevel.get() > 0;
  }

  /**
   * @param listener not {@code null}, duplicate listeners are not tolerated.
   * @throws ListenersConsistenceException if there's already such listener
   */
  public void addActionListener(T listener) {
    if (myListeners.contains(listener)) {
      throw new ListenersConsistenceException("Adding the same listener again");
    }
    myListeners.add(listener);
    if (isInsideAction()) {
      // FIXME there's ClassLoaderManager.init that attaches listeners inside model write and expects to receive 'start' notification, otherwise
      // internal state of BatchEventsProcessor breaks. However, I don't think it's good idea to send notifications like that when listener is added,
      // we can violate the listener contract or expectations
      myOnActionStart.accept(listener);
    }
  }

  /**
   * @param listener not {@code null}, listener has to be {@link #addActionListener(Object) registered} beforehand.
   * @throws ListenersConsistenceException if there's no such listener
   */
  public void removeActionListener(T listener) {
    if (!myListeners.contains(listener)) {
      throw new ListenersConsistenceException("The listener you are trying to remove does not exist");
    }
    if (isInsideAction()) {
      myOnActionFinish.accept(listener);
    }
    myListeners.remove(listener);
  }

  public static class ListenersConsistenceException extends RuntimeException {
    ListenersConsistenceException(String msg) {
      super(msg);
    }
  }

  /**
   * Mechanism to get pre and post notification around action dispatch for listeners T
   * Note, the need for this class stems from the mixing 2 kind of functionality in the same class,
   * one is action level tracking and another is collection of listeners and notification mechanism.
   * However, I don't feel the urge to refactor it now (don't have a name for listener container class ;) as
   * for now I need both aspects of this functionality at the same time, and no scenario for distinct use.
   * Besides, likely would need DispatchController anyway (which would do
   * {@code writeListenerContainer.notify(WrriteActionLisener::actionStarted)} itself.
   */
  public interface DispatchController {
    default void onActionStart() {}
    default void onActionFinish() {}
  }
}
