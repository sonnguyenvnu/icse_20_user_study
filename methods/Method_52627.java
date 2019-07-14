/** 
 * Example XPath 1.0:  {@code //ClassOrInterfaceType[typeof(@Image, 'java.lang.String', 'String')]}<p> Example XPath 2.0:  {@code //ClassOrInterfaceType[pmd-java:typeof(@Image, 'java.lang.String', 'String')]}
 * @param n
 * @param nodeTypeName Usually the {@code @Image} attribute of the node
 * @param fullTypeName The fully qualified name of the class or any supertype
 * @param shortTypeName The simple class name, might be <code>null</code>
 * @return
 */
public static boolean typeof(Node n,String nodeTypeName,String fullTypeName,String shortTypeName){
  nagDeprecatedFunction();
  if (n instanceof TypeNode) {
    Class<?> type=((TypeNode)n).getType();
    if (type == null) {
      return nodeTypeName != null && (nodeTypeName.equals(fullTypeName) || nodeTypeName.equals(shortTypeName));
    }
    if (type.getName().equals(fullTypeName)) {
      return true;
    }
    List<Class<?>> implementors=Arrays.asList(type.getInterfaces());
    if (implementors.contains(type)) {
      return true;
    }
    Class<?> superC=type.getSuperclass();
    while (superC != null && !superC.equals(Object.class)) {
      if (superC.getName().equals(fullTypeName)) {
        return true;
      }
      superC=superC.getSuperclass();
    }
  }
 else {
    throw new IllegalArgumentException("typeof function may only be called on a TypeNode.");
  }
  return false;
}
