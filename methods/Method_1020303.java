@Override public void insert(int elementNum,String stringPart,Image imagePart){
synchronized (selected) {
    boolean select=selected.size() == 0 && listType != MULTIPLE;
    strings.add(elementNum,stringPart);
    images.add(elementNum,imagePart);
    selected.add(elementNum,select);
    if (select) {
      selectedIndex=elementNum;
    }
    if (list != null) {
      adapter.insert(elementNum,stringPart,imagePart);
    }
  }
}
