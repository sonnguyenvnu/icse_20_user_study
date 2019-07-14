private SuggestedFix.Builder convertMethodToBinds(MethodTree method,VisitorState state){
  SuggestedFix.Builder fix=SuggestedFix.builder();
  JCModifiers modifiers=((JCMethodDecl)method).getModifiers();
  ImmutableList.Builder<String> modifierStringsBuilder=ImmutableList.<String>builder().add("@Binds");
  for (  JCAnnotation annotation : modifiers.annotations) {
    Name annotationQualifiedName=getSymbol(annotation).getQualifiedName();
    if (annotationQualifiedName.contentEquals(PROVIDES_CLASS_NAME) || annotationQualifiedName.contentEquals(PRODUCES_CLASS_NAME)) {
      List<JCExpression> arguments=annotation.getArguments();
      if (!arguments.isEmpty()) {
        JCExpression argument=Iterables.getOnlyElement(arguments);
        checkState(argument.getKind().equals(ASSIGNMENT));
        JCAssign assignment=(JCAssign)argument;
        checkState(getSymbol(assignment.getVariable()).getSimpleName().contentEquals("type"));
        String typeName=getSymbol(assignment.getExpression()).getSimpleName().toString();
switch (typeName) {
case "SET":
          modifierStringsBuilder.add("@IntoSet");
        fix.addImport(INTO_SET_CLASS_NAME);
      break;
case "SET_VALUES":
    modifierStringsBuilder.add("@ElementsIntoSet");
  fix.addImport(ELEMENTS_INTO_SET_CLASS_NAME);
break;
case "MAP":
modifierStringsBuilder.add("@IntoMap");
fix.addImport(INTO_MAP_CLASS_NAME);
break;
default :
throw new AssertionError("Unknown type name: " + typeName);
}
}
}
 else {
modifierStringsBuilder.add(state.getSourceForNode(annotation));
}
}
EnumSet<Flag> methodFlags=Flags.asFlagSet(modifiers.flags);
methodFlags.remove(Flags.Flag.STATIC);
methodFlags.remove(Flags.Flag.FINAL);
methodFlags.add(Flags.Flag.ABSTRACT);
for (Flag flag : methodFlags) {
modifierStringsBuilder.add(flag.toString());
}
fix.replace(modifiers,Joiner.on(' ').join(modifierStringsBuilder.build()));
fix.replace(method.getBody(),";");
return fix;
}
