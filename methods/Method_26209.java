private static boolean isValidParameterTree(VariableTree variableTree){
  if (variableTree.getInitializer() != null) {
    return false;
  }
  Set<Modifier> flags=variableTree.getModifiers().getFlags();
  return flags.isEmpty() || (flags.size() == 1 && flags.contains(Modifier.FINAL));
}
