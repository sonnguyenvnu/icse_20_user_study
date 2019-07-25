public LanguageConceptIndexBuilder put(long conceptId,int index){
  assert !myIsSealed;
  myIndex.put(conceptId,index);
  return this;
}
