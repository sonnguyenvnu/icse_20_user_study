/** 
 * Matches a  {@code continue} statement. 
 */
public static Matcher<StatementTree> continueStatement(){
  return (statementTree,state) -> statementTree instanceof ContinueTree;
}
