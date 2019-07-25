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
package jetbrains.mps.smodel;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Code that grabs a lock, either in a blocking or timeout-guarded manner,
 * runs a delegate under the lock and reports whether the execution succeeded (from the locking perspective).
 * @author Artem Tikhomirov
 * @since 2018.3
 */
final class LockRunnable implements Runnable {
  private final Lock myLock;
  private final long myTimeoutMillis;
  private final Runnable myDelegate;
  private boolean myDelegateExecuted;

  public LockRunnable(Lock lock, Runnable delegate) {
    myLock = lock;
    myTimeoutMillis = -1;
    myDelegate = delegate;
  }

  public LockRunnable(Lock lock, long timeoutMillis, Runnable delegate) {
    myLock = lock;
    myTimeoutMillis = timeoutMillis;
    myDelegate = delegate;
  }

  @Override
  public void run() {
    myDelegateExecuted = false;
    try {
      final boolean lockGranted;
      if (myTimeoutMillis == -1) {
        myLock.lock();
        lockGranted = true;
      } else {
        lockGranted = myLock.tryLock(myTimeoutMillis, TimeUnit.MILLISECONDS);
      }
      if (lockGranted) {
        try {
          myDelegate.run();
          myDelegateExecuted = true;
        } finally {
          myLock.unlock();
        }
      }
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
      Logger.getLogger(ModelAccess.class).error("Interrupted while trying to lock", ex);
    }
  }

  boolean wasExecuted() {
    return myDelegateExecuted;
  }
}
