static int getTypeIndex(ContributionType contributionType){
  int index;
  if (contributionType == ContributionType.LIBRARY) {
    index=0;
  }
 else   if (contributionType == ContributionType.MODE) {
    index=1;
  }
 else   if (contributionType == ContributionType.TOOL) {
    index=2;
  }
 else   if (contributionType == ContributionType.EXAMPLES) {
    index=3;
  }
 else {
    index=4;
  }
  return index;
}
