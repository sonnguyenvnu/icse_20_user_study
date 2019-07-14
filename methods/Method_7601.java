public ActionBarMenuItem getItem(int id){
  View v=findViewWithTag(id);
  if (v instanceof ActionBarMenuItem) {
    return (ActionBarMenuItem)v;
  }
  return null;
}
