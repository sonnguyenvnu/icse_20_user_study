public String apply(String s){
  if (this.rewriters == null || this.rewriters.size() == 0)   return s;
  for (  Map.Entry<Pattern,String> entry : this.rewriters.entrySet()) {
    Matcher m=entry.getKey().matcher(s);
    if (m.matches())     s=m.replaceAll(entry.getValue());
  }
  return s;
}
