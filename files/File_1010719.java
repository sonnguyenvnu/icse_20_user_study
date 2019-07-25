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
package jetbrains.mps.nodeEditor.actions;

import jetbrains.mps.openapi.editor.EditorComponent;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.selection.Selection;
import jetbrains.mps.openapi.editor.selection.SelectionListener;
import jetbrains.mps.openapi.editor.selection.SelectionManager;

/**
 * User: shatalin
 * Date: 6/11/13
 */
public class CursorPositionTracker implements SelectionListener {
  private SelectionManager mySelectionManager;
  private boolean myHasPosition;
  private int myPosition;

  public CursorPositionTracker(EditorContext context) {
    mySelectionManager = context.getSelectionManager();
    mySelectionManager.addSelectionListener(this);
  }

  public int getPosition() {
    return myPosition;
  }

  public void savePosition(int position) {
    myHasPosition = true;
    myPosition = position;
  }

  public void forgetPosition() {
    myHasPosition = false;
  }

  public boolean hasPosition() {
    return myHasPosition;
  }

  public void dispose() {
    mySelectionManager.removeSelectionListener(this);
    mySelectionManager = null;
  }

  @Override
  public void selectionChanged(EditorComponent editorComponent, Selection oldSelection, Selection newSelection) {
    forgetPosition();
  }
}
