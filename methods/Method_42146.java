@Override public View.OnClickListener getToolbarButtonListener(boolean editMode){
  if (editMode)   return null;
 else   return v -> adapter.clearSelected();
}
