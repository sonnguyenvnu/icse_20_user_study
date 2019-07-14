/** 
 * Repaints the lines containing the selection. 
 */
final void invalidateSelectedLines(){
  invalidateLineRange(textArea.getSelectionStartLine(),textArea.getSelectionStopLine());
}
