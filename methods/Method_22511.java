private static int getContributionStatusRank(Contribution c){
  int pos=4;
  if (c.isInstalled()) {
    pos=1;
    if (ContributionListing.getInstance().hasUpdates(c)) {
      pos=2;
    }
    if (!c.isCompatible(Base.getRevision())) {
      pos=3;
    }
  }
  return pos;
}
