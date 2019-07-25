/** 
 * Sets a variable value.
 * @param variable The variable to set.
 * @param value The variable value.
 * @return The expression, allows to chain methods.
 */
public Expression and(String variable,String value){
  return setVariable(variable,value);
}
