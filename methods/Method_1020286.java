@Override public void insert(int elementNum,String stringPart,Image imagePart){
synchronized (selected) {
    boolean select=selected.size() == 0 && choiceType != MULTIPLE;
    strings.add(elementNum,stringPart);
    images.add(elementNum,imagePart);
    selected.add(elementNum,select);
    if (select) {
      selectedIndex=elementNum;
    }
    if (buttongroup != null) {
      addButton(elementNum,stringPart,imagePart,select);
    }
 else     if (spinner != null) {
      adapter.insert(elementNum,stringPart,imagePart);
      if (select) {
        spinner.setSelection(elementNum);
      }
    }
  }
}
