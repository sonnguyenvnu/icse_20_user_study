private static JavaQualifiedName ofStringWithClassLoader(String name,ClassLoader classLoader){
  Matcher matcher=FORMAT.matcher(name);
  if (!matcher.matches()) {
    return null;
  }
  ImmutableList<String> packages=StringUtils.isBlank(matcher.group(PACKAGES_GROUP_INDEX)) ? ListFactory.<String>emptyList() : ListFactory.split(matcher.group(PACKAGES_GROUP_INDEX),"\\.");
  String operation=matcher.group(OPERATION_GROUP_INDEX) == null ? null : matcher.group(OPERATION_GROUP_INDEX).substring(1);
  boolean isLambda=operation != null && COMPILED_LAMBDA_PATTERN.matcher(operation).matches();
  ImmutableList<String> indexAndClasses=ListFactory.split(matcher.group(CLASSES_GROUP_INDEX),"\\$");
  ImmutableList<Integer> localIndices=ListFactory.emptyList();
  ImmutableList<String> classes=ListFactory.emptyList();
  for (  String clazz : indexAndClasses.reverse()) {
    Matcher localIndexMatcher=LOCAL_INDEX_PATTERN.matcher(clazz);
    if (localIndexMatcher.matches()) {
      localIndices=localIndices.prepend(Integer.parseInt(localIndexMatcher.group(1)));
      classes=classes.prepend(localIndexMatcher.group(2));
    }
 else {
      localIndices=localIndices.prepend(JavaTypeQualifiedName.NOTLOCAL_PLACEHOLDER);
      classes=classes.prepend(clazz);
    }
  }
  JavaTypeQualifiedName parent=new JavaTypeQualifiedName(packages,classes,localIndices,classLoader);
  return operation == null ? parent : new JavaOperationQualifiedName(parent,operation,isLambda);
}
