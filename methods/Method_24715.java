/** 
 * Updates the error table in the Error Window. Overridden to handle the fugly import suggestions text.
 */
@Override public void updateErrorTable(List<Problem> problems){
  errorTable.clearRows();
  for (  Problem p : problems) {
    JavaProblem jp=(JavaProblem)p;
    String message=p.getMessage();
    if (JavaMode.importSuggestEnabled && jp.getImportSuggestions() != null && jp.getImportSuggestions().length > 0) {
      message+=" (double-click for suggestions)";
    }
    errorTable.addRow(p,message,sketch.getCode(jp.getTabIndex()).getPrettyName(),Integer.toString(p.getLineNumber() + 1));
  }
}
