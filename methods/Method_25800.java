@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!FIRST_ORDER_MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  MatchResult directResult=firstNonNullMatchResult(DIRECT_MATCHERS,tree,state);
  MatchResult typeArgResult=null;
  if (directResult == null) {
    typeArgResult=firstNonNullMatchResult(TYPE_ARG_MATCHERS,tree,state);
  }
  if (directResult == null && typeArgResult == null) {
    return Description.NO_MATCH;
  }
  Verify.verify(directResult == null ^ typeArgResult == null);
  MatchResult result=MoreObjects.firstNonNull(directResult,typeArgResult);
  Types types=state.getTypes();
  TypeCompatibilityReport compatibilityReport=EqualsIncompatibleType.compatibilityOfTypes(result.targetType(),result.sourceType(),state);
  if (compatibilityReport.compatible()) {
    return Description.NO_MATCH;
  }
  String sourceTreeType=Signatures.prettyType(ASTHelpers.getType(result.sourceTree()));
  String sourceType=Signatures.prettyType(result.sourceType());
  String targetType=Signatures.prettyType(result.targetType());
  if (sourceType.equals(targetType)) {
    sourceType=result.sourceType().toString();
    targetType=result.targetType().toString();
  }
  Description.Builder description=buildDescription(tree);
  if (typeArgResult != null) {
    description.setMessage(String.format("Argument '%s' should not be passed to this method; its type %s has a type argument " + "%s that is not compatible with its collection's type argument %s",result.sourceTree(),sourceTreeType,sourceType,targetType));
  }
 else {
    description.setMessage(String.format("Argument '%s' should not be passed to this method; its type %s is not compatible " + "with its collection's type argument %s",result.sourceTree(),sourceType,targetType));
  }
switch (fixType) {
case PRINT_TYPES_AS_COMMENT:
    description.addFix(SuggestedFix.prefixWith(tree,String.format("/* expected: %s, actual: %s */",ASTHelpers.getUpperBound(result.targetType(),types),result.sourceType())));
  break;
case CAST:
Fix fix;
if (typeArgResult != null) {
TypeArgOfMethodArgMatcher matcher=(TypeArgOfMethodArgMatcher)typeArgResult.matcher();
String fullyQualifiedType=matcher.getMethodArgTypeName();
String simpleType=Iterables.getLast(Splitter.on('.').split(fullyQualifiedType));
fix=SuggestedFix.builder().prefixWith(result.sourceTree(),String.format("(%s<?>) ",simpleType)).addImport(fullyQualifiedType).build();
}
 else {
fix=SuggestedFix.prefixWith(result.sourceTree(),"(Object) ");
}
description.addFix(fix);
break;
case SUPPRESS_WARNINGS:
SuggestedFix.Builder builder=SuggestedFix.builder();
builder.prefixWith(result.sourceTree(),String.format("/* expected: %s, actual: %s */ ",targetType,sourceType));
addSuppressWarnings(builder,state,"CollectionIncompatibleType");
description.addFix(builder.build());
break;
case NONE:
break;
}
return description.build();
}
