/** 
 * Break on commas, except those inside quotes, e.g.: size(300, 200, PDF, "output,weirdname.pdf"); No special handling implemented for escaped (\") quotes.
 */
static private StringList breakCommas(String contents){
  StringList outgoing=new StringList();
  boolean insideQuote=false;
  StringBuilder current=new StringBuilder();
  char[] chars=contents.toCharArray();
  for (int i=0; i < chars.length; i++) {
    char c=chars[i];
    if (insideQuote) {
      current.append(c);
      if (c == '\"') {
        insideQuote=false;
      }
    }
 else {
      if (c == ',') {
        if (current.length() != 0) {
          outgoing.append(current.toString());
          current.setLength(0);
        }
      }
 else {
        current.append(c);
        if (c == '\"') {
          insideQuote=true;
        }
      }
    }
  }
  if (current.length() != 0) {
    outgoing.append(current.toString());
  }
  return outgoing;
}
