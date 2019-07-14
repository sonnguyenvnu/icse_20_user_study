public void addFragmentList(List<?> fragment){
  this.mFragment.clear();
  this.mFragment=null;
  this.mFragment=fragment;
  notifyDataSetChanged();
}
