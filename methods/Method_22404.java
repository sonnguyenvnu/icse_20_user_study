private List<Contribution> getInstalledContribs(){
  List<Contribution> contributions=new ArrayList<>();
  List<ModeContribution> modeContribs=getModeContribs();
  contributions.addAll(modeContribs);
  for (  ModeContribution modeContrib : modeContribs) {
    Mode mode=modeContrib.getMode();
    contributions.addAll(new ArrayList<>(mode.contribLibraries));
  }
  contributions.addAll(ToolContribution.loadAll(getSketchbookToolsFolder()));
  contributions.addAll(getExampleContribs());
  return contributions;
}
