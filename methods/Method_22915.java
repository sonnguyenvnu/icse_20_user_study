/** 
 * Updates the error table in the Error Window.
 */
public void updateErrorTable(List<Problem> problems){
  if (errorTable != null) {
    errorTable.clearRows();
    for (    Problem p : problems) {
      String message=p.getMessage();
      errorTable.addRow(p,message,sketch.getCode(p.getTabIndex()).getPrettyName(),Integer.toString(p.getLineNumber() + 1));
    }
  }
}
