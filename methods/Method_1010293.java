public LanguageConceptIndex seal(){
  assert !myIsSealed;
  myIsSealed=true;
  myIndex.compact();
  return new LanguageConceptIndex(myIndex);
}
