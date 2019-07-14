private CharSequence getTabTitle(int count,int formatResId,int emptyResId){
  return count > 0 ? getString(formatResId,count) : getString(emptyResId);
}
