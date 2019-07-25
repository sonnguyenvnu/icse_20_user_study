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
package jetbrains.mps.editor.runtime.cells;

/**
 * Tracking cell caret visibility state.
 * <p>
 * Created by shatalin on 29/07/16.
 */
public class CaretState {
  private boolean myCaretIsVisible;
  private long myChangeCaretTimeStamp = 0;

  /**
   * This method should be called at the moment we move caret within the cell.
   * It will show the caret & suppress caret blinking for some small period.
   */
  public void touch() {
    touch(true);
  }

  /**
   * This method should be called on entering/exiting the cell (from
   * {@link jetbrains.mps.openapi.editor.cells.EditorCell#setSelected(boolean)} method).
   * <p>
   * It will show/hide the caret & suppress caret blinking for some small period.
   */
  public void touch(boolean showCaret) {
    myChangeCaretTimeStamp = System.currentTimeMillis();
    myCaretIsVisible = showCaret;
  }

  /**
   * This method should be called by caret blinking thread (timer) to show/hide the caret.
   */
  public void tick() {
    if (System.currentTimeMillis() - myChangeCaretTimeStamp < 500) {
      return;
    }
    myCaretIsVisible = !myCaretIsVisible;
  }

  public boolean isVisible() {
    return myCaretIsVisible;
  }
}
