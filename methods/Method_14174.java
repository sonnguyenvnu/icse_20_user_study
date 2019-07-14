@Override public String key(String s,Object... o){
  int ngram_size=2;
  if (o != null && o.length > 0 && o[0] instanceof Number) {
    ngram_size=(Integer)o[0];
  }
  s=s.toLowerCase();
  s=alphanum.matcher(s).replaceAll("");
  TreeSet<String> set=ngram_split(s,ngram_size);
  StringBuffer b=new StringBuffer();
  Iterator<String> i=set.iterator();
  while (i.hasNext()) {
    b.append(i.next());
  }
  return asciify(b.toString());
}
