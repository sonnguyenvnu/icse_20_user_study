public boolean addOrRemoveSelectedDialog(long did,View cell){
  if (selectedDialogs.contains(did)) {
    selectedDialogs.remove(did);
    if (cell instanceof DialogCell) {
      ((DialogCell)cell).setChecked(false,true);
    }
    return false;
  }
 else {
    selectedDialogs.add(did);
    if (cell instanceof DialogCell) {
      ((DialogCell)cell).setChecked(true,true);
    }
    return true;
  }
}
