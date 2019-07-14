/** 
 * Called when a window is activated. Because of variations in native windowing systems, no guarantees about changes to the focused and active Windows can be made. Never assume that this Window is the focused or active Window until this Window actually receives a WINDOW_GAINED_FOCUS or WINDOW_ACTIVATED event.
 */
public void handleActivated(Editor whichEditor){
  activeEditor=whichEditor;
  EditorConsole.setEditor(activeEditor);
  nextMode=whichEditor.getMode();
  Preferences.set("mode.last",nextMode.getIdentifier());
}
