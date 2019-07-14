private Fix buildFix(NewClassTree tree,VisitorState state){
  boolean autoboxFix=shouldAutoboxFix(state);
  Types types=state.getTypes();
  Type type=types.unboxedTypeOrType(getType(tree));
  if (types.isSameType(type,state.getSymtab().booleanType)) {
    Object value=literalValue(tree.getArguments().iterator().next());
    if (value instanceof Boolean) {
      return SuggestedFix.replace(tree,literalFix((boolean)value,autoboxFix));
    }
 else     if (value instanceof String) {
      return SuggestedFix.replace(tree,literalFix(Boolean.parseBoolean((String)value),autoboxFix));
    }
  }
  JCTree.JCExpression arg=(JCTree.JCExpression)getOnlyElement(tree.getArguments());
  Type argType=getType(arg);
  if (autoboxFix && argType.isPrimitive()) {
    return SuggestedFix.builder().replace(((JCTree)tree).getStartPosition(),arg.getStartPosition(),maybeCast(state,type,argType)).replace(state.getEndPosition(arg),state.getEndPosition(tree),"").build();
  }
  JCTree parent=(JCTree)state.getPath().getParentPath().getParentPath().getLeaf();
  if (TO_STRING.matches(parent,state)) {
    return SuggestedFix.builder().replace(parent.getStartPosition(),arg.getStartPosition(),"String.valueOf(").replace(state.getEndPosition(arg),state.getEndPosition(parent),")").build();
  }
  String typeName=state.getSourceForNode(tree.getIdentifier());
  DoubleAndFloatStatus doubleAndFloatStatus=doubleAndFloatStatus(state,type,argType);
  if (HASH_CODE.matches(parent,state)) {
    SuggestedFix.Builder fix=SuggestedFix.builder();
    String replacement;
    String optionalCast="";
    String optionalSuffix="";
switch (doubleAndFloatStatus) {
case PRIMITIVE_DOUBLE_INTO_FLOAT:
      optionalCast="(float) ";
    break;
case BOXED_DOUBLE_INTO_FLOAT:
  optionalSuffix=".floatValue()";
break;
default :
break;
}
if (shouldUseGuavaHashCode(state.context)) {
fix.addImport("com.google.common.primitives." + typeName + "s");
replacement=String.format("%ss.hashCode(",typeName);
}
 else {
replacement=String.format("%s.hashCode(",typeName);
}
return fix.replace(parent.getStartPosition(),arg.getStartPosition(),replacement + optionalCast).replace(state.getEndPosition(arg),state.getEndPosition(parent),optionalSuffix + ")").build();
}
if (COMPARE_TO.matches(parent,state) && ASTHelpers.getReceiver((ExpressionTree)parent).equals(tree)) {
JCMethodInvocation compareTo=(JCMethodInvocation)parent;
JCTree.JCExpression rhs=getOnlyElement(compareTo.getArguments());
String optionalCast="";
String optionalSuffix="";
switch (doubleAndFloatStatus) {
case PRIMITIVE_DOUBLE_INTO_FLOAT:
optionalCast="(float) ";
break;
case BOXED_DOUBLE_INTO_FLOAT:
optionalSuffix=".floatValue()";
break;
default :
break;
}
return SuggestedFix.builder().replace(compareTo.getStartPosition(),arg.getStartPosition(),String.format("%s.compare(%s",typeName,optionalCast)).replace(state.getEndPosition(arg),rhs.getStartPosition(),String.format("%s, ",optionalSuffix)).replace(state.getEndPosition(rhs),state.getEndPosition(compareTo),")").build();
}
String prefixToArg;
String suffix="";
switch (doubleAndFloatStatus) {
case PRIMITIVE_DOUBLE_INTO_FLOAT:
prefixToArg=String.format("%s.valueOf(%s",typeName,"(float) ");
break;
case BOXED_DOUBLE_INTO_FLOAT:
prefixToArg="";
suffix=".floatValue(";
break;
default :
prefixToArg=String.format("%s.valueOf(",typeName);
break;
}
return SuggestedFix.builder().replace(((JCTree)tree).getStartPosition(),arg.getStartPosition(),prefixToArg).postfixWith(arg,suffix).build();
}
