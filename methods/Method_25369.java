/** 
 * A matcher that matches no AST node. 
 */
public static <T extends Tree>Matcher<T> nothing(){
  return (t,state) -> false;
}
