public void reset(){
  char currentCode=(char)('A' + historical.size());
  if (historical.size() > 7) {
    final ParsedGenerated last=historical.get(0);
    final String lastName=last.parsed().getName();
    currentCode=lastName.charAt("histo.".length());
  }
  this.current=ParsedGenerated.loadDated(prefs,"histo." + currentCode);
  this.current.reset();
  final long maxId=getMaxId();
  this.current.parsedDated().setComment(Long.toString(maxId + 1,36) + "/" + Version.versionString());
}
