/** 
 * ???????????
 * @param roleTagList
 * @return
 */
public static List<NT> viterbiCompute(List<EnumItem<NT>> roleTagList){
  return Viterbi.computeEnum(roleTagList,OrganizationDictionary.transformMatrixDictionary);
}
