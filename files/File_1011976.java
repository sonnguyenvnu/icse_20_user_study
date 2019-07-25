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

import com.intellij.openapi.progress.ProgressIndicator;
import jetbrains.mps.util.EqualUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Evgeny Gryaznov, 9/30/11
 */
public class ProgressMonitorAdapter extends ProgressMonitorBase {
  private final ProgressIndicator myIndicator;

  public ProgressMonitorAdapter(@NotNull ProgressIndicator indicator) {
    myIndicator = indicator;
    /* As ProgressMonitorAdapter#update uses ProgressIndicator#setFraction,
    * we have to take into account AbstractProgressIndicatorBase#setFraction check for indicator indeterminate state
    * */
    myIndicator.setIndeterminate(false);
  }

  @Override
  protected void update(double fraction) {
    myIndicator.setFraction(fraction);
  }

  @Override
  protected void setTitleInternal(String name) {
    if (name != null && name.startsWith("__")) {
      name = null;
    }
    final String oldText = myIndicator.getText();
    if (!EqualUtil.equals(name, oldText)) {
      myIndicator.setText(name);
    }
  }

  @Override
  protected void setStepInternal(String description) {
    if (description != null && description.startsWith("__")) {
      description = null;
    }
    final String oldText = myIndicator.getText2();
    if (!EqualUtil.equals(description, oldText)) {
      myIndicator.setText2(description);
    }
  }

  @Override
  protected void startInternal(String text) {

  }

  @Override
  protected void doneInternal(String text) {

  }

  public void pushState() {
    myIndicator.pushState();
  }

  public void popState() {
    myIndicator.popState();
  }

  @Override
  public boolean isCanceled() {
    return myIndicator.isCanceled();
  }

  public ProgressIndicator getIndicator() {
    return myIndicator;
  }

  @Override
  public void cancel() {
    myIndicator.cancel();
  }
}
