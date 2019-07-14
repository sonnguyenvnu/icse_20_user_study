public boolean isSectionIndexValid(String globalKey,int index){
  return index < findSectionForKey(globalKey).mSection.getCount() && index >= 0;
}
