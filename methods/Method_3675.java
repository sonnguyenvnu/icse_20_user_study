/** 
 * ?????????
 * @param sentence
 * @param nature
 * @return
 */
public static boolean hasNature(List<Term> sentence,Nature nature){
  for (  Term term : sentence) {
    if (term.nature == nature) {
      return true;
    }
  }
  return false;
}
