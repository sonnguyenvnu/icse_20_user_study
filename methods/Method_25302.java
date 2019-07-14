@Override public Set<Replacement> getReplacements(EndPosTable endPositions){
  if (endPositions == null) {
    throw new IllegalArgumentException("Cannot produce correct replacements without endPositions.");
  }
  Replacements replacements=new Replacements();
  for (  FixOperation fix : fixes) {
    replacements.add(fix.getReplacement(endPositions),Replacements.CoalescePolicy.EXISTING_FIRST);
  }
  return replacements.descending();
}
