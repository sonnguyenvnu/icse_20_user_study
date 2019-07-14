private List<String> parseMetadataValues(int start,List<Exception> _exceptions) throws IOException {
  List<String> values=new ArrayList<String>();
  outer:   while (_line != null && start < _line.length()) {
    char c=_line.charAt(start);
    if (c == '"') {
      StringBuffer sb=new StringBuffer();
      inner:       while (_line != null && start < _line.length()) {
        int close=_line.indexOf('"',start + 1);
        if (close < 0) {
          _exceptions.add(new Exception("Missing closing quotation mark on line " + _lnReader.getLineNumber()));
          sb.append(_line.substring(start + 1));
          values.add(sb.toString());
          break outer;
        }
 else {
          sb.append(_line.substring(start + 1,close));
          if (close == _line.length() - 1) {
            _line=_lnReader.readLine();
            start=0;
            if (_line != null && _line.length() > 0) {
              c=_line.charAt(0);
              if (c == '"') {
                continue inner;
              }
            }
            break;
          }
 else {
            start=close + 1;
            break;
          }
        }
      }
      values.add(sb.toString());
    }
 else {
      int comma=customIndexOf(_line,',',start + 1);
      int semicolon=customIndexOf(_line,';',start + 1);
      int space=customIndexOf(_line,' ',start + 1);
      int end=Math.min(comma,Math.min(semicolon,space));
      values.add(_line.substring(start,end));
      start=end;
    }
    if (start == _line.length()) {
      _line=_lnReader.readLine();
      start=0;
    }
 else {
      c=_line.charAt(start);
      if (c == ';' || c == ')') {
        break;
      }
 else       if (c == ',' || c == ' ' || c == '-') {
        start++;
        if (start == _line.length()) {
          _line=_lnReader.readLine();
          start=0;
        }
      }
 else {
        _exceptions.add(new Exception("Unrecognized character " + c + " on line " + _lnReader.getLineNumber()));
        break;
      }
    }
  }
  return values;
}
