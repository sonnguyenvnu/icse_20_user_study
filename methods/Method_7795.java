@Override public int getSectionCount(){
  ArrayList<String> sortedUsersSectionsArray=ContactsController.getInstance(currentAccount).phoneBookSectionsArray;
  return sortedUsersSectionsArray.size();
}
