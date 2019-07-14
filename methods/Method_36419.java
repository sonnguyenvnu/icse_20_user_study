private void parse(String path){
  ArrayList<String> seg=new ArrayList<String>();
  StringBuffer buf=new StringBuffer();
  char[] chars=path.toCharArray();
  boolean attr=false;
  for (  char c : chars) {
switch (c) {
case '/':
      seg.add(buf.toString());
    buf.setLength(0);
  break;
case '@':
attr=true;
seg.add(buf.toString());
buf.setLength(0);
break;
default :
buf.append(c);
break;
}
}
if (buf.length() > 0) {
if (attr) {
attribute=buf.toString();
}
 else {
seg.add(buf.toString());
}
}
int size=seg.size();
if (size == 1 && seg.get(0).length() == 0) {
segments=EMPTY_SEGMENTS;
}
 else {
segments=seg.toArray(new String[size]);
}
}
