public Tagging.Metatag metatag(String vocName,String term){
  Tagging tagging=this.vocabularies.get(vocName);
  if (tagging == null) {
    if (ProbabilisticClassifier.getContextNames().contains(vocName)) {
      tagging=new Tagging(vocName);
    }
  }
  if (tagging == null)   return null;
  return tagging.getMetatagFromTerm(Tagging.decodeMaskname(term));
}
