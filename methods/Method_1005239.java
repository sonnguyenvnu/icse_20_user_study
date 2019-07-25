@PostConstruct public void init(){
  if (tags == null)   return;
  for (  String key : tags.keySet()) {
    freemarker.setSharedVariable(key,tags.get(key));
  }
}
