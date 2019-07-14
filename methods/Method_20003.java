@Nullable private String getSelectedSortBy(){
  String selected=(String)mSortSpinner.getSelectedItem();
  if (getString(R.string.sort_by_rating).equals(selected)) {
    return Restaurant.FIELD_AVG_RATING;
  }
  if (getString(R.string.sort_by_price).equals(selected)) {
    return Restaurant.FIELD_PRICE;
  }
  if (getString(R.string.sort_by_popularity).equals(selected)) {
    return Restaurant.FIELD_POPULARITY;
  }
  return null;
}
