protected ContributionTab getTab(ContributionType contributionType){
  if (contributionType == ContributionType.LIBRARY) {
    return librariesTab;
  }
 else   if (contributionType == ContributionType.MODE) {
    return modesTab;
  }
 else   if (contributionType == ContributionType.TOOL) {
    return toolsTab;
  }
 else   if (contributionType == ContributionType.EXAMPLES) {
    return examplesTab;
  }
  return updatesTab;
}
