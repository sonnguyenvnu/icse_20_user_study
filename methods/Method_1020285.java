@Override public void delete(int elementNum){
synchronized (selected) {
    strings.remove(elementNum);
    images.remove(elementNum);
    selected.remove(elementNum);
    if (selected.size() == 0) {
      selectedIndex=-1;
    }
    if (buttongroup != null) {
      buttons.remove(elementNum);
      buttongroup.removeViewAt(elementNum);
      updateButtonIDs(elementNum);
    }
 else     if (spinner != null) {
      adapter.delete(elementNum);
    }
  }
}
