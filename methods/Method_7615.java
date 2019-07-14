public void hideSubItem(int id){
  if (popupLayout == null) {
    return;
  }
  View view=popupLayout.findViewWithTag(id);
  if (view != null && view.getVisibility() != GONE) {
    view.setVisibility(GONE);
  }
}
