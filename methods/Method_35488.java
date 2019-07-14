/** 
 * Get the next value. The value can be wrapped in quotes. The value can be empty.
 * @param x A JSONTokener of the source text.
 * @return The value string, or null if empty.
 * @throws JSONException if the quoted string is badly formed.
 */
private static String getValue(JSONTokener x) throws JSONException {
  char c;
  char q;
  StringBuffer sb;
  do {
    c=x.next();
  }
 while (c == ' ' || c == '\t');
switch (c) {
case 0:
    return null;
case '"':
case '\'':
  q=c;
sb=new StringBuffer();
for (; ; ) {
c=x.next();
if (c == q) {
  char nextC=x.next();
  if (nextC != '\"') {
    if (nextC > 0) {
      x.back();
    }
    break;
  }
}
if (c == 0 || c == '\n' || c == '\r') {
  throw x.syntaxError("Missing close quote '" + q + "'.");
}
sb.append(c);
}
return sb.toString();
case ',':
x.back();
return "";
default :
x.back();
return x.nextTo(',');
}
}
