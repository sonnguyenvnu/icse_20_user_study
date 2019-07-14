public long distance(List<CommonSynonymDictionary.SynonymItem> synonymItemListA,List<CommonSynonymDictionary.SynonymItem> synonymItemListB){
  return EditDistance.compute(synonymItemListA,synonymItemListB);
}
