/** 
 * ???????????
 * @param roleTagList
 * @return
 */
public static List<NR> viterbiCompute(List<EnumItem<NR>> roleTagList){
  return Viterbi.computeEnum(roleTagList,PersonDictionary.transformMatrixDictionary);
}
