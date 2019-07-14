@Override public Choice<Unifier> visitModifiers(ModifiersTree modifier,Unifier unifier){
  return Choice.condition(getFlags().equals(modifier.getFlags()),unifier);
}
