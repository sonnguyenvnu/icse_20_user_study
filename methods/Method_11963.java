/** 
 * @return If this describes a method invocation,the name of the class of the test instance
 */
public String getClassName(){
  return fTestClass != null ? fTestClass.getName() : methodAndClassNamePatternGroupOrDefault(2,toString());
}
