/** 
 * Query-normal-form (QNF) for JanusGraph is a variant of CNF (conjunctive normal form) with negation inlined where possible
 * @param condition
 * @return
 */
public static boolean isQueryNormalForm(Condition<?> condition){
  if (isQNFLiteralOrNot(condition)) {
    return true;
  }
  if (!(condition instanceof And)) {
    return false;
  }
  for (  final Condition<?> child : ((And<?>)condition).getChildren()) {
    if (!isQNFLiteralOrNot(child)) {
      if (child instanceof Or) {
        for (        final Condition<?> child2 : ((Or<?>)child).getChildren()) {
          if (!isQNFLiteralOrNot(child2))           return false;
        }
      }
 else {
        return false;
      }
    }
  }
  return true;
}
