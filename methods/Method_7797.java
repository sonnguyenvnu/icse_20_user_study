@Override public View getSectionHeaderView(int section,View view){
  HashMap<String,ArrayList<Object>> usersSectionsDict=ContactsController.getInstance(currentAccount).phoneBookSectionsDict;
  ArrayList<String> sortedUsersSectionsArray=ContactsController.getInstance(currentAccount).phoneBookSectionsArray;
  if (view == null) {
    view=new LetterSectionCell(mContext);
  }
  LetterSectionCell cell=(LetterSectionCell)view;
  if (section < sortedUsersSectionsArray.size()) {
    cell.setLetter(sortedUsersSectionsArray.get(section));
  }
 else {
    cell.setLetter("");
  }
  return view;
}
