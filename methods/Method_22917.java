/** 
 * Updates editor status bar, depending on whether the caret is on an error line or not
 */
public void updateEditorStatus(){
  Problem problem=findProblem(textarea.getCaretLine());
  if (problem != null) {
    int type=problem.isError() ? EditorStatus.CURSOR_LINE_ERROR : EditorStatus.CURSOR_LINE_WARNING;
    statusMessage(problem.getMessage(),type);
  }
 else {
switch (getStatusMode()) {
case EditorStatus.CURSOR_LINE_ERROR:
case EditorStatus.CURSOR_LINE_WARNING:
      statusEmpty();
    break;
}
}
}
