private Object checkName(List<String> prefixes,List<String> suffixes,ApexNode<?> node,boolean isStatic,boolean isFinal,Object data){
  String varName=node.getImage();
  if (varName == null || "serialVersionUID".equals(varName)) {
    return data;
  }
  if (isStatic && isFinal) {
    if (!varName.equals(varName.toUpperCase(Locale.ROOT))) {
      addViolationWithMessage(data,node,"Variables that are final and static should be all capitals, ''{0}'' is not all capitals.",new Object[]{varName});
    }
    return data;
  }
 else   if (!isFinal) {
    String normalizedVarName=normalizeVariableName(varName,prefixes,suffixes);
    if (normalizedVarName.indexOf('_') >= 0) {
      addViolationWithMessage(data,node,"Only variables that are final should contain underscores (except for underscores in standard prefix/suffix), ''{0}'' is not final.",new Object[]{varName});
    }
    if (Character.isUpperCase(varName.charAt(0))) {
      addViolationWithMessage(data,node,"Variables should start with a lowercase character, ''{0}'' starts with uppercase character.",new Object[]{varName});
    }
  }
  return data;
}
