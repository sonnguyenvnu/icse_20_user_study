/** 
 * Returns a  {@link Statement}: apply all static fields assignable to  {@link TestRule}annotated with  {@link ClassRule}.
 * @param statement the base statement
 * @return a RunRules statement if any class-level {@link Rule}s are found, or the base statement
 */
private Statement withClassRules(Statement statement){
  List<TestRule> classRules=classRules();
  return classRules.isEmpty() ? statement : new RunRules(statement,classRules,getDescription());
}
