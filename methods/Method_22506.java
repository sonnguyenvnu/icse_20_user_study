private boolean isModeActive(Contribution contrib){
  if (contrib.getType() == ContributionType.MODE) {
    ModeContribution m=(ModeContribution)contrib;
    for (    Editor e : getBase().getEditors()) {
      if (e.getMode().equals(m.getMode())) {
        return true;
      }
    }
  }
  return false;
}
