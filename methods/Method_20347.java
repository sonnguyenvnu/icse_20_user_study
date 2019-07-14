private void updatePagedListSnapshot(){
  list=pagedList == null ? Collections.<T>emptyList() : pagedList.snapshot();
  requestModelBuild();
}
