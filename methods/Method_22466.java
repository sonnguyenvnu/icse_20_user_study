protected String getLatestPrettyVersion(Contribution contribution){
  Contribution newestContrib=getAvailableContribution(contribution);
  if (newestContrib == null) {
    return null;
  }
  return newestContrib.getPrettyVersion();
}
