private List<Object> getNextBatchOfDataValues(int expectedCount) throws IOException {
  List<Object> cells=new LinkedList<Object>();
  int start=_line.startsWith("DATA=") ? 5 : 0;
  while (_line != null) {
    int end=Math.min(customIndexOf(_line,';',start),Math.min(customIndexOf(_line,' ',start),customIndexOf(_line,'\t',start)));
    if (end > start) {
      cells.add(_line.substring(start,end));
    }
    while (end < _line.length()) {
      if (Character.isWhitespace(_line.charAt(end))) {
        end++;
      }
 else {
        break;
      }
    }
    if (end == _line.length()) {
      _line=_lnReader.readLine();
      start=0;
    }
 else     if (_line.charAt(end) == ';') {
      _line=_lnReader.readLine();
      break;
    }
 else {
      start=end;
    }
    if (cells.size() == expectedCount) {
      _line=_line.substring(start);
      break;
    }
  }
  return cells;
}
