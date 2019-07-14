/** 
 * ??????
 * @param name
 * @param activeLine
 * @param wordNetOptimum
 * @param wordNetAll
 */
private static void insertName(String name,int activeLine,WordNet wordNetOptimum,WordNet wordNetAll){
  if (isBadCase(name))   return;
  wordNetOptimum.insert(activeLine,new Vertex(Predefine.TAG_PEOPLE,name,new CoreDictionary.Attribute(Nature.nrj),WORD_ID),wordNetAll);
}
