public void backspace(){
  if (column == 0) {
    Debug.ReportBug("CharReader.backspace trying to move past beginning of line");
  }
  ;
  column=column - 1;
  vcolumn=vcolumn - 1;
}
