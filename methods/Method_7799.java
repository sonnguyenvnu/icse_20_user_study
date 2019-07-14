@Override public String getLetter(int position){
  ArrayList<String> sortedUsersSectionsArray=ContactsController.getInstance(currentAccount).phoneBookSectionsArray;
  int section=getSectionForPosition(position);
  if (section == -1) {
    section=sortedUsersSectionsArray.size() - 1;
  }
  if (section >= 0 && section < sortedUsersSectionsArray.size()) {
    return sortedUsersSectionsArray.get(section);
  }
  return null;
}
