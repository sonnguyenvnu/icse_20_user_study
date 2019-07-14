/** 
 * Tries to establish whether  {@code expression} is side-effect free. The heuristics here are veryconservative.
 */
public static boolean hasSideEffect(ExpressionTree expression){
  if (expression == null) {
    return false;
  }
  SideEffectAnalysis analyzer=new SideEffectAnalysis();
  expression.accept(analyzer,null);
  return analyzer.hasSideEffect;
}
