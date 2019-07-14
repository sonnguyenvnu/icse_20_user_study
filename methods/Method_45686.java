/** 
 * ???JSON??????String?Number?True/False/Null?Map?Array
 * @return ??json????
 * @throws ParseException ??????
 */
protected Object nextValue() throws ParseException {
  try {
    char c=this.nextToken();
switch (c) {
case '{':
      try {
        LinkedHashMap<String,Object> map=new LinkedHashMap<String,Object>();
        if (nextToken() != '}') {
          --position;
          while (true) {
            String key=nextValue().toString();
            if (nextToken() != ':') {
              throw new ParseException(new String(this.buffer),this.position,"Expected a ':' after a key");
            }
            map.put(key,nextValue());
switch (nextToken()) {
case ';':
case ',':
              if (nextToken() == '}') {
                return map;
              }
            --position;
          break;
case '}':
        return map;
default :
      throw new ParseException(new String(this.buffer),this.position,"Expected a ',' or '}'");
  }
}
}
 else {
return map;
}
}
 catch (ArrayIndexOutOfBoundsException ignore) {
throw new ParseException(new String(this.buffer),this.position,"Expected a ',' or '}'");
}
case '[':
try {
ArrayList<Object> list=new ArrayList<Object>();
if (nextToken() != ']') {
--position;
while (true) {
if (nextToken() == ',') {
  --position;
  list.add(null);
}
 else {
  --position;
  list.add(nextValue());
}
switch (nextToken()) {
case ',':
  if (nextToken() == ']') {
    return list;
  }
--position;
break;
case ']':
return list;
default :
throw new ParseException(new String(this.buffer),this.position,"Expected a ',' or ']'");
}
}
}
 else {
return list;
}
}
 catch (ArrayIndexOutOfBoundsException ignore) {
throw new ParseException(new String(this.buffer),this.position,"Expected a ',' or ']'");
}
case '"':
case '\'':
StringBuilder sb=new StringBuilder();
while (true) {
char ch=this.buffer[++position];
switch (ch) {
case '\n':
case '\r':
throw new ParseException(new String(this.buffer),this.position,"Unterminated string");
case '\\':
ch=this.buffer[++position];
switch (ch) {
case 'b':
sb.append('\b');
break;
case 't':
sb.append('\t');
break;
case 'n':
sb.append('\n');
break;
case 'f':
sb.append('\f');
break;
case 'r':
sb.append('\r');
break;
case 'u':
int num=0;
for (int i=3; i >= 0; --i) {
int tmp=buffer[++position];
if (tmp <= '9' && tmp >= '0') {
tmp=tmp - '0';
}
 else if (tmp <= 'F' && tmp >= 'A') {
tmp=tmp - ('A' - 10);
}
 else if (tmp <= 'f' && tmp >= 'a') {
tmp=tmp - ('a' - 10);
}
 else {
throw new ParseException(new String(this.buffer),this.position,"Illegal hex code");
}
num+=tmp << (i * 4);
}
sb.append((char)num);
break;
case '"':
case '\'':
case '\\':
case '/':
sb.append(ch);
break;
default :
throw new ParseException(new String(this.buffer),this.position,"Illegal escape.");
}
break;
default :
if (ch == c) {
return sb.toString();
}
sb.append(ch);
}
}
}
int startPosition=this.position;
while (c >= ' ' && ",:]}/\\\"[{;=#".indexOf(c) < 0) {
c=this.buffer[++position];
}
String substr=new String(buffer,startPosition,position-- - startPosition);
if ("true".equalsIgnoreCase(substr)) {
return Boolean.TRUE;
}
if ("false".equalsIgnoreCase(substr)) {
return Boolean.FALSE;
}
if ("null".equalsIgnoreCase(substr)) {
return null;
}
char b="-+".indexOf(substr.charAt(0)) < 0 ? substr.charAt(0) : substr.charAt(1);
if (b >= '0' && b <= '9') {
try {
Long l=Long.valueOf(substr.trim());
if (l.intValue() == l) {
return l.intValue();
}
return l;
}
 catch (NumberFormatException exInt) {
try {
return new Double(substr.trim());
}
 catch (NumberFormatException ignore) {
}
}
}
return substr;
}
 catch (ArrayIndexOutOfBoundsException ignore) {
throw new ParseException(new String(this.buffer),this.position,"Unexpected end");
}
}
