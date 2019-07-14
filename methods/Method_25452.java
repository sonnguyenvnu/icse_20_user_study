@Override public boolean matches(MethodTree t,VisitorState state){
  Set<Modifier> modifiers=t.getModifiers().getFlags();
  if (visibility == Visibility.DEFAULT) {
    return !(modifiers.contains(Visibility.PUBLIC.toModifier()) || modifiers.contains(Visibility.PROTECTED.toModifier()) || modifiers.contains(Visibility.PRIVATE.toModifier()));
  }
 else {
    return modifiers.contains(visibility.toModifier());
  }
}
