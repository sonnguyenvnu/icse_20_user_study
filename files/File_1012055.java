/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.ide.messages.navigation;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;

/**
 * Navigation to text editor.
 *
 * non-public unless necessary
 * @author Artem Tikhomirov
 * @since 3.4
 */
class VirtualFileNavigatable implements Navigatable {
  private final Project myProject;
  private final VirtualFile myVirtualFile;
  private int myOffset = -1;
  private int myLine = 0;
  private int myColumn = 0;

  VirtualFileNavigatable(Project ideaProject, VirtualFile virtualFile) {
    myProject = ideaProject;
    myVirtualFile = virtualFile;
  }

  public VirtualFileNavigatable offset(int offset) {
    myOffset = offset;
    return this;
  }

  public VirtualFileNavigatable at(int line, int column) {
    myLine = line;
    myColumn = column;
    myOffset = -1;
    return this;
  }

  @Override
  public void navigate(boolean requestFocus) {
    for (FileEditor fe: FileEditorManager.getInstance(myProject).openFile(myVirtualFile, true, true)) {
      if (!(fe instanceof TextEditor)) {
        continue;
      }

      TextEditor te = (TextEditor) fe;
      Document document = te.getEditor().getDocument();
      if (myOffset != -1) {
        te.getEditor().getCaretModel().moveToOffset(Math.min(myOffset, document.getTextLength()));
      } else {
        int maxLines = document.getLineCount();
        int line = Math.min(Math.max(0, myLine), maxLines - 1);
        int lineWidth = document.getLineEndOffset(line) - document.getLineStartOffset(line);
        int column = Math.min(Math.max(0, myColumn), lineWidth);
        LogicalPosition position = new LogicalPosition(line, column);
        te.getEditor().getCaretModel().moveToLogicalPosition(position);
      }
      te.getEditor().getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
      return;
    }

  }

  @Override
  public boolean canNavigate() {
    return true;
  }

  @Override
  public boolean canNavigateToSource() {
    return true;
  }
}
