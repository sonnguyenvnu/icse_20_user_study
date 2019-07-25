/*
 * Copyright 2003-2015 JetBrains s.r.o.
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
package jetbrains.mps.project.validation;

import jetbrains.mps.errors.MessageStatus;
import jetbrains.mps.errors.item.ReportItem;
import org.jetbrains.mps.openapi.util.Processor;

import java.util.ArrayList;
import java.util.List;

public class MessageCollectProcessor<T extends ReportItem> implements Processor<T> {
  private List<String> myWarnings = new ArrayList<>(1);
  private List<String> myErrors = new ArrayList<>(1);
  private final boolean myCollectWarnings;

  public MessageCollectProcessor() {
    this(true);
  }

  public MessageCollectProcessor(boolean collectWarnings) {
    myCollectWarnings = collectWarnings;
  }

  @Override
  public boolean process(T problem) {
    if (problem.getSeverity() == MessageStatus.ERROR) {
      myErrors.add(formatMessage(problem));
    } else if (myCollectWarnings){
      myWarnings.add(formatMessage(problem));
    }
    return true;
  }

  protected String formatMessage(T problem) {
    return problem.getMessage();
  }

  public List<String> getWarnings() {
    return myWarnings;
  }

  public List<String> getErrors() {
    return myErrors;
  }
}
