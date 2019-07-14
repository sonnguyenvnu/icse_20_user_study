public void resetFilters(){
  if (mRootView != null) {
    mCategorySpinner.setSelection(0);
    mCitySpinner.setSelection(0);
    mPriceSpinner.setSelection(0);
    mSortSpinner.setSelection(0);
  }
}
