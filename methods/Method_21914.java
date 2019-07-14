private void setChildUnChecked(int position,TagView view){
  view.setChecked(false);
  mTagAdapter.unSelected(position,view.getTagView());
}
