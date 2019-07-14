public int getSelectedTypeIndex(){
  if (isPersonalProfile) {
    return typesListForPersonalProfile.indexOf(type);
  }
 else {
    return typesListForExternalProfile.indexOf(type);
  }
}
