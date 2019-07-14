@Override public int getCountForSection(int section){
  HashMap<String,ArrayList<Object>> usersSectionsDict=ContactsController.getInstance(currentAccount).phoneBookSectionsDict;
  ArrayList<String> sortedUsersSectionsArray=ContactsController.getInstance(currentAccount).phoneBookSectionsArray;
  if (section < sortedUsersSectionsArray.size()) {
    ArrayList<Object> arr=usersSectionsDict.get(sortedUsersSectionsArray.get(section));
    int count=arr.size();
    if (section != sortedUsersSectionsArray.size() - 1) {
      count++;
    }
    return count;
  }
  return 0;
}
