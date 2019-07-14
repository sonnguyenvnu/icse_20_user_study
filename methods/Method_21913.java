private void setChildChecked(int position,TagView view){
  view.setChecked(true);
  mTagAdapter.onSelected(position,view.getTagView());
}
