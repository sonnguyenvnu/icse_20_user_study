/** 
 * Returns true if this node is a multi-catch statement, that is, it catches several unrelated exception types at the same time. Such a block can be declared like the following for example: <p> {@code} catch (IllegalStateException | IllegalArgumentException e) 
 * @return True if this node is a multi-catch statement
 */
public boolean isMulticatchStatement(){
  return getCaughtExceptionTypeNodes().size() > 1;
}
