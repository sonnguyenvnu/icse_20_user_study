@Override public String key(String s,Object... o){
  if (s == null || o != null && o.length > 0) {
    throw new IllegalArgumentException("Fingerprint keyer accepts a single string parameter");
  }
  s=s.trim();
  s=s.toLowerCase();
  s=punctctrl.matcher(s).replaceAll("");
  s=asciify(s);
  String[] frags=StringUtils.split(s);
  TreeSet<String> set=new TreeSet<String>();
  for (  String ss : frags) {
    set.add(ss);
  }
  StringBuffer b=new StringBuffer();
  Iterator<String> i=set.iterator();
  while (i.hasNext()) {
    b.append(i.next());
    if (i.hasNext()) {
      b.append(' ');
    }
  }
  return b.toString();
}
