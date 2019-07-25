/*
 * Copyright 2003-2013 JetBrains s.r.o.
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

import jetbrains.mps.generator.IGeneratorLogger;
import jetbrains.mps.messages.Message;
import jetbrains.mps.messages.MessageKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SModuleReference;

/**
 * Almost useless(?!) {@link jetbrains.mps.generator.IGeneratorLogger} implementation that counts errors and warnings
 * and aware of errors for modules (beside nodes).
 */
public class GenerationSessionLogger extends GeneratorLoggerAdapter implements IGeneratorLogger {

  private int myWarningsCount;
  private int myErrorsCount;

  public GenerationSessionLogger(GeneratorLoggerAdapter logger, MessageFactory factory) {
    super(logger.myMessageHandler, factory, logger.myHandleInfo, logger.myHandleWarnings);
  }

  /* package */ void error(@NotNull SModuleReference moduleReference, String message) {
    errorReported();
    report(MessageKind.ERROR, message, moduleReference);
  }

  @Override
  protected void errorReported() {
    myErrorsCount++;
  }

  @Override
  protected void warningReported() {
    myWarningsCount++;
  }

  private void report(MessageKind kind, String text, @NotNull SModuleReference module) {
    Message m = myFactory.prepare(kind, text, null);
    m.setHintObject(module);
    addMessage(m);
  }

  public int getErrorCount() {
    return myErrorsCount;
  }

  public int getWarningCount() {
    return myWarningsCount;
  }
}
