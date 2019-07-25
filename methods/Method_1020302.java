@Override public int append(String stringPart,Image imagePart){
synchronized (selected) {
    int index=selected.size();
    boolean select=index == 0 && listType != MULTIPLE;
    strings.add(stringPart);
    images.add(imagePart);
    selected.add(select);
    if (select) {
      selectedIndex=index;
    }
    if (list != null) {
      adapter.append(stringPart,imagePart);
    }
    return index;
  }
}
