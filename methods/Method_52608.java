/** 
 * @return true, if 'bounds' contains an equality between 'second' and an element from 'firstList'
 */
public static boolean boundsHaveAnEqualityBetween(List<Variable> firstList,Variable second,List<Bound> bounds){
  for (  Bound bound : bounds) {
    for (    Variable first : firstList) {
      if (bound.ruleType == EQUALITY && (bound.leftVariable() == first && bound.rightVariable() == second || bound.leftVariable() == second && bound.rightVariable() == first)) {
        return true;
      }
    }
  }
  return false;
}
