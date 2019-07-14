/** 
 * https://docs.oracle.com/javase/specs/jls/se8/html/jls-18.html#jls-18.3
 */
public static List<Constraint> incorporateBounds(List<Bound> currentBounds,List<Bound> newBounds){
  List<Constraint> newConstraints=new ArrayList<>();
  for (  Bound first : currentBounds) {
    for (    Bound second : newBounds) {
      Sides sides=getUnequalSides(first,second);
      if (sides == null) {
        continue;
      }
      if (first.ruleType() == EQUALITY && second.ruleType() == EQUALITY) {
        newConstraints.add(copyConstraint(first,second,getUnequalSides(first,second),EQUALITY));
      }
 else       if (first.ruleType() == EQUALITY && second.ruleType() == SUBTYPE) {
        if (sides.second == Side.RIGHT) {
          newConstraints.add(copyConstraint(first,second,sides,SUBTYPE));
        }
 else {
          newConstraints.add(copyConstraint(second,first,sides.copySwap(),SUBTYPE));
        }
      }
 else       if (first.ruleType() == SUBTYPE && second.ruleType() == EQUALITY) {
        if (sides.first == Side.RIGHT) {
          newConstraints.add(copyConstraint(second,first,sides.copySwap(),SUBTYPE));
        }
 else {
          newConstraints.add(copyConstraint(first,second,sides,SUBTYPE));
        }
      }
 else       if (first.ruleType() == SUBTYPE && second.ruleType() == SUBTYPE) {
        if (sides.first == Side.LEFT && sides.second == Side.RIGHT) {
          newConstraints.add(copyConstraint(first,second,sides,SUBTYPE));
        }
 else         if (sides.first == Side.RIGHT && sides.second == Side.LEFT) {
          newConstraints.add(copyConstraint(second,first,sides.copySwap(),SUBTYPE));
        }
      }
    }
  }
  currentBounds.addAll(newBounds);
  return newConstraints;
}
