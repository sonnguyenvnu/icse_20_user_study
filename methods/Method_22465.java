protected boolean hasUpdates(Contribution contribution){
  if (contribution.isInstalled()) {
    Contribution advertised=getAvailableContribution(contribution);
    if (advertised == null) {
      return false;
    }
    return advertised.getVersion() > contribution.getVersion() && advertised.isCompatible(Base.getRevision());
  }
  return false;
}
