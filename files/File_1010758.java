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
package jetbrains.mps.nodeEditor;

import org.jetbrains.annotations.NotNull;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * This class used to track focused state of particular editor component.
 * We do not use {@link Component#isFocusOwner()} method because of the
 * delayed focus event dispatching in AWT/IDEA. This component used to
 * store "request focus" events in order to already use modified component
 * focus state in other places like:
 * - {@link jetbrains.mps.ide.editor.BaseNodeEditor#loadState(jetbrains.mps.openapi.editor.EditorState)}
 * - {@link com.intellij.openapi.command.impl.UndoRedo#restore(com.intellij.openapi.command.impl.EditorAndState)}
 * <p>
 * Created by shatalin on 20/07/16.
 */
public class EditorComponentFocusTracker {
  @NotNull
  private final EditorComponent myEditorComponent;
  private boolean myEffectiveFocusState;
  private FocusListener myFocusListener;

  protected EditorComponentFocusTracker(@NotNull EditorComponent editorComponent) {
    myEditorComponent = editorComponent;
    installFocusListener();
  }

  /**
   * This method may be called to say that we are asking AWT to focus/un-focus
   * current component, so this component will be focused/unfocused later, but
   * we should remember new focus state (effective focus state) for it and use
   * this state in all subsequent calls to save/restore editor states.
   *
   * @param isFocused boolean future focused state for this component
   */
  public void setEffectiveFocusState(boolean isFocused) {
    myEffectiveFocusState = isFocused;
  }

  public boolean getEffectiveFocusState() {
    return myEffectiveFocusState;
  }

  public void dispose() {
    unInstallFocusListener();
  }

  private void installFocusListener() {
    myEditorComponent.addFocusListener(myFocusListener = new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        myEffectiveFocusState = true;
      }

      @Override
      public void focusLost(FocusEvent e) {
        myEffectiveFocusState = false;
      }
    });
  }

  private void unInstallFocusListener() {
    myEditorComponent.removeFocusListener(myFocusListener);
    myFocusListener = null;
  }

}
