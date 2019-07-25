public void enable(){
  myDisabled=false;
  Set<StaticReference> existing=myReferences.get();
  if (existing == null) {
    existing=new THashSet<>(myHashStrategy);
    myReferences.set(existing);
  }
 else {
    existing.clear();
  }
}
