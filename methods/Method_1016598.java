private void check(Formatter formatter,List<File> outOfDate) throws Exception {
  List<File> problemFiles=new ArrayList<>();
  for (  File file : outOfDate) {
    getLogger().debug("Checking format on " + file);
    if (!formatter.isClean(file)) {
      problemFiles.add(file);
    }
  }
  if (paddedCell) {
    PaddedCellGradle.check(this,formatter,problemFiles);
  }
 else {
    if (!problemFiles.isEmpty()) {
      if (PaddedCellBulk.anyMisbehave(formatter,problemFiles)) {
        throw PaddedCellGradle.youShouldTurnOnPaddedCell(this);
      }
 else {
        throw formatViolationsFor(formatter,problemFiles);
      }
    }
  }
}
