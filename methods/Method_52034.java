/** 
 * Creates a new class qname from the current context (fields). 
 */
private JavaTypeQualifiedName contextClassQName(){
  return new JavaTypeQualifiedName(packages,classNames,localIndices,classLoader);
}
