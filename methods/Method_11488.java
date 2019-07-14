public void setTabData(String[] titles){
  if (titles == null || titles.length == 0) {
    throw new IllegalStateException("Titles can not be NULL or EMPTY !");
  }
  this.mTitles=titles;
  notifyDataSetChanged();
}
