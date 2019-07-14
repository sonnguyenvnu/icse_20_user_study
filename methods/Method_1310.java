/** 
 * Creates an instance of  {@link ToStringHelper} in the same manner as{@link Objects#toStringHelper(Object)}, but using  {@code className} insteadof using an instance's  {@link Object#getClass()}.
 * @param className the name of the instance type
 * @since 7.0 (source-compatible since 2.0)
 */
public static ToStringHelper toStringHelper(String className){
  return new ToStringHelper(className);
}
