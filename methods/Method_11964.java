/** 
 * @return If this describes a method invocation,the name of the method (or null if not)
 */
public String getMethodName(){
  return methodAndClassNamePatternGroupOrDefault(1,null);
}
