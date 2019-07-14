private void makeProtectedPublic(MethodTree methodTree,VisitorState state,SuggestedFix.Builder suggestedFix){
  if (Matchers.<MethodTree>hasModifier(Modifier.PROTECTED).matches(methodTree,state)) {
    ModifiersTree modifiers=methodTree.getModifiers();
    CharSequence modifiersSource=state.getSourceForNode(modifiers);
    suggestedFix.replace(modifiers,modifiersSource.toString().replaceFirst("protected","public"));
  }
}
