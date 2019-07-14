@Override public int getCountForSection(int section){
  HashMap<String,ArrayList<TLRPC.TL_contact>> usersSectionsDict=onlyUsers == 2 ? ContactsController.getInstance(currentAccount).usersMutualSectionsDict : ContactsController.getInstance(currentAccount).usersSectionsDict;
  ArrayList<String> sortedUsersSectionsArray=onlyUsers == 2 ? ContactsController.getInstance(currentAccount).sortedUsersMutualSectionsArray : ContactsController.getInstance(currentAccount).sortedUsersSectionsArray;
  if (onlyUsers != 0 && !isAdmin) {
    if (section < sortedUsersSectionsArray.size()) {
      ArrayList<TLRPC.TL_contact> arr=usersSectionsDict.get(sortedUsersSectionsArray.get(section));
      int count=arr.size();
      if (section != (sortedUsersSectionsArray.size() - 1) || needPhonebook) {
        count++;
      }
      return count;
    }
  }
 else {
    if (section == 0) {
      if (needPhonebook || isAdmin) {
        return 2;
      }
 else {
        return 4;
      }
    }
 else {
      if (sortType == 2) {
        if (section == 1) {
          return onlineContacts.isEmpty() ? 0 : onlineContacts.size() + 1;
        }
      }
 else {
        if (section - 1 < sortedUsersSectionsArray.size()) {
          ArrayList<TLRPC.TL_contact> arr=usersSectionsDict.get(sortedUsersSectionsArray.get(section - 1));
          int count=arr.size();
          if (section - 1 != (sortedUsersSectionsArray.size() - 1) || needPhonebook) {
            count++;
          }
          return count;
        }
      }
    }
  }
  if (needPhonebook) {
    return ContactsController.getInstance(currentAccount).phoneBookContacts.size();
  }
  return 0;
}
