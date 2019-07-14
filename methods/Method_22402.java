public List<Mode> getModeList(){
  List<Mode> allModes=new ArrayList<>();
  allModes.addAll(Arrays.asList(coreModes));
  if (modeContribs != null) {
    for (    ModeContribution contrib : modeContribs) {
      allModes.add(contrib.getMode());
    }
  }
  return allModes;
}
