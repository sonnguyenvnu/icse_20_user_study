static Pattern matchPattern(String regexp){
  Pattern p=null;
  if (matchPatterns == null) {
    matchPatterns=new LinkedHashMap<String,Pattern>(16,0.75f,true){
      @Override protected boolean removeEldestEntry(      Map.Entry<String,Pattern> eldest){
        return size() == 10;
      }
    }
;
  }
 else {
    p=matchPatterns.get(regexp);
  }
  if (p == null) {
    p=Pattern.compile(regexp,Pattern.MULTILINE | Pattern.DOTALL);
    matchPatterns.put(regexp,p);
  }
  return p;
}
