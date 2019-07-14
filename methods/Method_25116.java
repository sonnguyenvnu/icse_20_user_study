public void handleFix(Fix fix){
  importsToAdd.addAll(fix.getImportsToAdd());
  importsToRemove.addAll(fix.getImportsToRemove());
  for (  Replacement replacement : fix.getReplacements(endPositions)) {
    try {
      replacements.add(replacement,Replacements.CoalescePolicy.EXISTING_FIRST);
    }
 catch (    IllegalArgumentException iae) {
      if (!ignoreOverlappingFixes) {
        throw iae;
      }
    }
  }
}
