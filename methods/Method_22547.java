@Override public void contributionRemoved(final Contribution contribution){
  super.contributionRemoved(contribution);
  ((UpdateStatusPanel)contributionTab.statusPanel).update();
}
