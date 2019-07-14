private void undo(){
  clearStatusMessage();
  if (contrib instanceof LocalContribution) {
    LocalContribution installed=getLocalContrib();
    installed.setDeletionFlag(false);
    contribListing.replaceContribution(contrib,contrib);
    Iterator<Contribution> contribsListIter=contribListing.allContributions.iterator();
    boolean toBeRestarted=false;
    while (contribsListIter.hasNext()) {
      Contribution contribElement=contribsListIter.next();
      if (contrib.getType().equals(contribElement.getType())) {
        if (contribElement.isDeletionFlagged() || contribElement.isUpdateFlagged()) {
          toBeRestarted=!toBeRestarted;
          break;
        }
      }
    }
  }
}
