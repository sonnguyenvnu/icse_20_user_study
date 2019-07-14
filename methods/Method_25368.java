/** 
 * A matcher that matches any AST node. 
 */
public static <T extends Tree>Matcher<T> anything(){
  return (t,state) -> true;
}
