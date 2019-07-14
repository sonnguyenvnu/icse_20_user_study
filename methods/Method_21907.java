@Deprecated public void setSelectedList(Set<Integer> set){
  mCheckedPosList.clear();
  if (set != null) {
    mCheckedPosList.addAll(set);
  }
  notifyDataChanged();
}
