@OnLongClick(R.id.fab) boolean onFabLongClick(){
  if (navType == RepoPagerMvp.ISSUES) {
    onAddSelected();
    return true;
  }
  return false;
}
