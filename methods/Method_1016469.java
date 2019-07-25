public void check(MultiProtocolURL root,String className,char[] content){
  if (this.classVocabulary == null)   return;
  String voc=this.classVocabulary.get(className);
  if (voc == null)   return;
  ConcurrentHashMap<String,String> vocmap=this.vocMap.get(root);
  if (vocmap == null) {
synchronized (this) {
      vocmap=new ConcurrentHashMap<>();
      this.vocMap.put(root,vocmap);
    }
  }
  if (!vocmap.containsKey(voc))   vocmap.put(voc,new String(content));
}
