@Override public View getSectionHeaderView(int section,View view){
  HashMap<String,ArrayList<TLRPC.TL_contact>> usersSectionsDict=onlyUsers == 2 ? ContactsController.getInstance(currentAccount).usersMutualSectionsDict : ContactsController.getInstance(currentAccount).usersSectionsDict;
  ArrayList<String> sortedUsersSectionsArray=onlyUsers == 2 ? ContactsController.getInstance(currentAccount).sortedUsersMutualSectionsArray : ContactsController.getInstance(currentAccount).sortedUsersSectionsArray;
  if (view == null) {
    view=new LetterSectionCell(mContext);
  }
  LetterSectionCell cell=(LetterSectionCell)view;
  if (sortType == 2) {
    cell.setLetter("");
  }
 else {
    if (onlyUsers != 0 && !isAdmin) {
      if (section < sortedUsersSectionsArray.size()) {
        cell.setLetter(sortedUsersSectionsArray.get(section));
      }
 else {
        cell.setLetter("");
      }
    }
 else {
      if (section == 0) {
        cell.setLetter("");
      }
 else       if (section - 1 < sortedUsersSectionsArray.size()) {
        cell.setLetter(sortedUsersSectionsArray.get(section - 1));
      }
 else {
        cell.setLetter("");
      }
    }
  }
  return view;
}
