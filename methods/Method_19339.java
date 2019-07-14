@Override @UiThread @GuardedBy("this") public boolean isValidPosition(int position){
  return position >= 0 && position < mComponentTreeHolders.size();
}
