/** 
 * Returns a descriptor like "(Ljava/lang/Class;[I)Ljava/lang/Object;".
 */
String descriptor(boolean includeThis){
  StringBuilder result=new StringBuilder();
  result.append("(");
  if (includeThis) {
    result.append(declaringType.name);
  }
  for (  TypeId t : parameters.types) {
    result.append(t.name);
  }
  result.append(")");
  result.append(returnType.name);
  return result.toString();
}
