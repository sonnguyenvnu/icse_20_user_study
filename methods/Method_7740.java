@Override public int getItemViewType(int section,int position){
  HashMap<String,ArrayList<TLRPC.TL_contact>> usersSectionsDict=onlyUsers == 2 ? ContactsController.getInstance(currentAccount).usersMutualSectionsDict : ContactsController.getInstance(currentAccount).usersSectionsDict;
  ArrayList<String> sortedUsersSectionsArray=onlyUsers == 2 ? ContactsController.getInstance(currentAccount).sortedUsersMutualSectionsArray : ContactsController.getInstance(currentAccount).sortedUsersSectionsArray;
  if (onlyUsers != 0 && !isAdmin) {
    ArrayList<TLRPC.TL_contact> arr=usersSectionsDict.get(sortedUsersSectionsArray.get(section));
    return position < arr.size() ? 0 : 3;
  }
 else {
    if (section == 0) {
      if ((needPhonebook || isAdmin) && position == 1 || position == 3) {
        return 2;
      }
    }
 else {
      if (sortType == 2) {
        if (section == 1) {
          return position < onlineContacts.size() ? 0 : 3;
        }
      }
 else {
        if (section - 1 < sortedUsersSectionsArray.size()) {
          ArrayList<TLRPC.TL_contact> arr=usersSectionsDict.get(sortedUsersSectionsArray.get(section - 1));
          return position < arr.size() ? 0 : 3;
        }
      }
    }
  }
  return 1;
}
