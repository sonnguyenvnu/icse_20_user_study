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
package jetbrains.mps.logging;

/**
 * log4j logger augmented with hint object
 */
public abstract class Logger {

  /**
   * Adapts log4j logger to our extended facility
   * These two methods we need for a migration script
   */
  public static Logger wrap(org.apache.log4j.Logger logger) {
    return new Log4jLogger(logger);
  }

  //--------------------------
  // Logger instance
  //--------------------------

  public void info(String message) {
    info(message, null);
  }

  public void info(String message, Throwable t) {
    info(message, t, null);
  }

  public void info(String message, Object hintObject) {
    info(message, null, hintObject);
  }

  public abstract void info(String message, Throwable t, Object hintObject);

  public void warning(String message) {
    warning(message, null);
  }

  public void warning(String message, Throwable t) {
    warning(message, t, null);
  }

  public void warning(String message, Object hintObject) {
    warning(message, null, hintObject);
  }

  public abstract void warning(String message, Throwable t, Object hintObject);

  public void debug(String message) {
    debug(message, null);
  }

  public void debug(String message, Throwable t) {
    debug(message, t, null);
  }

  public void debug(String message, Object hintObject) {
    debug(message, null, hintObject);
  }

  public abstract void debug(String message, Throwable t, Object hintObject);

  public void error(String message) {
    error(message, null);
  }

  public void error(Throwable t) {
    error(t, null);
  }

  public void error(Throwable t, Object hintObject) {
    if (t != null) {
      error(t.getClass().getName() + (t.getMessage() != null ? " : " + t.getMessage() : ""), t, hintObject);
    } else {
      error(new Throwable("error with null throwable was called"));
    }
  }

  public void error(String message, Throwable t) {
    error(message, t, null);
  }

  public void error(String message, Object hintObject) {
    error(message, null, hintObject);
  }

  public abstract void error(String message, Throwable t, Object hintObject);

  public void errorWithTrace(String message) {
    error(message, new Throwable(message));
  }

  public void fatal(String message) {
    fatal(message, null);
  }

  public void fatal(String message, Throwable t) {
    fatal(message, t, null);
  }

  public void fatal(String message, Object hintObject) {
    fatal(message, null, hintObject);
  }

  public abstract void fatal(String message, Throwable t, Object hintObject);

  public void assertLog(boolean condition) {
    assertLog(condition, "Assertion failed");
  }

  public abstract void assertLog(boolean condition, String message);
}

