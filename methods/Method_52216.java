private Object checkVariableDeclaratorId(List<String> prefixes,List<String> suffixes,boolean isStatic,boolean isFinal,ASTVariableDeclaratorId variableDeclaratorId,Object data){
  String varName=variableDeclaratorId.getImage();
  if ("serialVersionUID".equals(varName)) {
    return data;
  }
  if (isStatic && isFinal) {
    if (!varName.equals(varName.toUpperCase(Locale.ROOT))) {
      addViolationWithMessage(data,variableDeclaratorId,"Variables that are final and static should be all capitals, ''{0}'' is not all capitals.",new Object[]{varName});
    }
    return data;
  }
 else   if (!isFinal) {
    String normalizedVarName=normalizeVariableName(varName,prefixes,suffixes);
    if (normalizedVarName.indexOf('_') >= 0) {
      addViolationWithMessage(data,variableDeclaratorId,"Only variables that are final should contain underscores (except for underscores in standard prefix/suffix), ''{0}'' is not final.",new Object[]{varName});
    }
    if (Character.isUpperCase(varName.charAt(0))) {
      addViolationWithMessage(data,variableDeclaratorId,"Variables should start with a lowercase character, ''{0}'' starts with uppercase character.",new Object[]{varName});
    }
  }
  return data;
}
