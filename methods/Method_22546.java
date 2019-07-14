@Override public void contributionAdded(final Contribution contribution){
  if (contribFilter.matches(contribution)) {
    super.contributionAdded(contribution);
    ((UpdateStatusPanel)contributionTab.statusPanel).update();
  }
}
