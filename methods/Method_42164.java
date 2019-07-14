@Override public String getToolbarTitle(){
  return editMode() ? null : album.getName();
}
