@Override public boolean shadowing(Stereotype stereotype){
  if (colors.getShadowing() == null) {
    return super.shadowing(stereotype);
  }
  return colors.getShadowing();
}
