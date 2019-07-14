@Override public String getLetter(int position){
  if (sortType == 2) {
    return null;
  }
  ArrayList<String> sortedUsersSectionsArray=onlyUsers == 2 ? ContactsController.getInstance(currentAccount).sortedUsersMutualSectionsArray : ContactsController.getInstance(currentAccount).sortedUsersSectionsArray;
  int section=getSectionForPosition(position);
  if (section == -1) {
    section=sortedUsersSectionsArray.size() - 1;
  }
  if (section > 0 && section <= sortedUsersSectionsArray.size()) {
    return sortedUsersSectionsArray.get(section - 1);
  }
  return null;
}
