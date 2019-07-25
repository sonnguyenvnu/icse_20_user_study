@Override public int append(String stringPart,Image imagePart){
synchronized (selected) {
    int index=selected.size();
    boolean select=index == 0 && choiceType != MULTIPLE;
    strings.add(stringPart);
    images.add(imagePart);
    selected.add(select);
    if (select) {
      selectedIndex=index;
    }
    if (buttongroup != null) {
      addButton(index,stringPart,imagePart,select);
    }
 else     if (spinner != null) {
      adapter.append(stringPart,imagePart);
      if (select) {
        spinner.setSelection(index);
      }
    }
    return index;
  }
}
