public void backspace(){
  if (column == 0) {
    if (line == 0) {
      PcalDebug.ReportBug("PcalCharReader.backspace trying to " + "move past beginning of reader");
    }
    ;
    line=line - 1;
    currentLine=(String)vec.elementAt(line);
    column=0;
    vcolumn=0;
    while (column < currentLine.length() - 1) {
      char c=getNextChar();
    }
  }
 else {
    column=column - 1;
    vcolumn=vcolumn - 1;
  }
}
