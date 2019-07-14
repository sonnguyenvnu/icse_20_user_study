/** 
 * Check the current selection for reference. If no selection is active, expand the current selection.
 * @return
 */
protected String referenceCheck(boolean selectIfFound){
  int start=textarea.getSelectionStart();
  int stop=textarea.getSelectionStop();
  if (stop < start) {
    int temp=stop;
    stop=start;
    start=temp;
  }
  char[] c=textarea.getText().toCharArray();
  if (start == stop) {
    while (start > 0 && functionable(c[start - 1])) {
      start--;
    }
    while (stop < c.length && functionable(c[stop])) {
      stop++;
    }
  }
  String text=new String(c,start,stop - start).trim();
  if (checkParen(c,stop,c.length)) {
    text+="_";
  }
  String ref=mode.lookupReference(text);
  if (selectIfFound) {
    textarea.select(start,stop);
  }
  return ref;
}
