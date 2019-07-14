private String createReplacementClassModifiers(VisitorState state,JCModifiers enclosingClassModifiers){
  ImmutableList.Builder<String> classModifierStringsBuilder=ImmutableList.builder();
  for (  JCAnnotation annotation : enclosingClassModifiers.annotations) {
    classModifierStringsBuilder.add(state.getSourceForNode(annotation));
  }
  EnumSet<Flag> classFlags=Flags.asFlagSet(enclosingClassModifiers.flags);
  classFlags.remove(Flags.Flag.FINAL);
  classFlags.add(Flags.Flag.ABSTRACT);
  for (  Flag flag : classFlags) {
    classModifierStringsBuilder.add(flag.toString());
  }
  return Joiner.on(' ').join(classModifierStringsBuilder.build());
}
