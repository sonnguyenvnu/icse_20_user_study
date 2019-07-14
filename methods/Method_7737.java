@Override public int getSectionCount(){
  int count;
  if (sortType == 2) {
    count=1;
  }
 else {
    ArrayList<String> sortedUsersSectionsArray=onlyUsers == 2 ? ContactsController.getInstance(currentAccount).sortedUsersMutualSectionsArray : ContactsController.getInstance(currentAccount).sortedUsersSectionsArray;
    count=sortedUsersSectionsArray.size();
  }
  if (onlyUsers == 0) {
    count++;
  }
  if (isAdmin) {
    count++;
  }
  if (needPhonebook) {
  }
  return count;
}
