public List<String> getTypesList(){
  if (isPersonalProfile) {
    return typesListForPersonalProfile;
  }
 else   if (isOrg) {
    return typesListForOrganizationProfile;
  }
 else {
    return typesListForExternalProfile;
  }
}
