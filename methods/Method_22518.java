private void validateSpecial(){
  for (  AvailableContribution available : ContributionListing.getInstance().advertisedContributions) {
    if (available.getName().equals(name)) {
      if (!available.isSpecial()) {
        categories.removeValue(SPECIAL_CATEGORY);
      }
    }
    break;
  }
}
