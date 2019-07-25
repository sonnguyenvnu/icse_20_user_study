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
package jetbrains.mps.ide.blame;

import com.intellij.diagnostic.AbstractMessage;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.diagnostic.ErrorReportSubmitter;
import com.intellij.openapi.diagnostic.IdeaLoggingEvent;
import com.intellij.openapi.diagnostic.SubmittedReportInfo;
import com.intellij.openapi.diagnostic.SubmittedReportInfo.SubmissionStatus;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Consumer;
import jetbrains.mps.ide.ThreadUtils;
import jetbrains.mps.ide.blame.dialog.BlameDialog;
import jetbrains.mps.ide.blame.dialog.BlameDialogComponent;
import jetbrains.mps.ide.blame.perform.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Component;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CharismaReporter extends ErrorReportSubmitter {

  @NotNull
  @Override
  public String getReportActionText() {
    return "Report To JetBrains MPS Tracker";
  }

  @Override
  public boolean submit(@NotNull IdeaLoggingEvent[] events, @Nullable String additionalInfo, @NotNull Component parentComponent,
                        @NotNull Consumer<SubmittedReportInfo> consumer) {
    ThreadUtils.assertEDT();

    if (events.length == 0) {
      consumer.consume(new SubmittedReportInfo(null, null, SubmissionStatus.FAILED));
      return false;
    }
    final DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);
    final Project project = PlatformDataKeys.PROJECT.getData(dataContext);

    BlameDialog blameDialog = BlameDialogComponent.getInstance().createDialog(project, parentComponent);
    blameDialog.addExceptions(Arrays.stream(events).map(IdeaLoggingEvent::getThrowable).collect(Collectors.toList()));
    // Use only first message. Other messages will be shown in their stack traces.
    blameDialog.setIssueTitle(extractMessage(events[0]));
    blameDialog.setDescription(additionalInfo);
    blameDialog.setPluginDescriptor(getPluginDescriptor());

    blameDialog.initDialog();
    blameDialog.show();

    if (blameDialog.isCancelled()) {
      consumer.consume(new SubmittedReportInfo(null, "Cancelled issue submit", SubmissionStatus.FAILED));
      return false;
    }

    Response response = blameDialog.getResult();
    assert response != null : "Response must not be null";
    assert response.isSuccess() : "Response is not 'cancelled' or 'success'";
    consumer.consume(new SubmittedReportInfo(null, "", SubmissionStatus.NEW_ISSUE));
    return true;
  }

  private String extractMessage(final IdeaLoggingEvent event) {
    if (StringUtil.isNotEmpty(event.getMessage())) {
      return event.getMessage();
    }

    if (event.getData() instanceof AbstractMessage
        && StringUtil.isNotEmpty(((AbstractMessage) event.getData()).getThrowable().getMessage())) {
      return ((AbstractMessage) event.getData()).getThrowable().getMessage();
    }

    if (event.getThrowable() != null && StringUtil.isNotEmpty(event.getThrowable().getMessage())) {
      return event.getThrowable().getMessage();
    }

    return "Unknown event occurred";
  }
}
