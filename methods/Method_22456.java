private void setAdvertisedList(File file){
  listingFile=file;
  advertisedContributions.clear();
  advertisedContributions.addAll(parseContribList(listingFile));
  for (  Contribution contribution : advertisedContributions) {
    addContribution(contribution);
  }
}
