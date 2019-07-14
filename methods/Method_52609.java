/** 
 * @return A map of variable -&gt; proper type produced by searching for ? = T or T = ? bounds
 */
public static Map<Variable,JavaTypeDefinition> getInstantiations(List<Bound> bounds){
  Map<Variable,JavaTypeDefinition> result=new HashMap<>();
  for (  Bound bound : bounds) {
    if (bound.ruleType() == EQUALITY) {
      if (bound.isLeftVariable() && bound.isRightProper()) {
        result.put(bound.leftVariable(),bound.rightProper());
      }
 else       if (bound.isLeftProper() && bound.isRightVariable()) {
        result.put(bound.rightVariable(),bound.leftProper());
      }
    }
  }
  return result;
}
