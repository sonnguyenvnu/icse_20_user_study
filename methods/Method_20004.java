@Nullable private Query.Direction getSortDirection(){
  String selected=(String)mSortSpinner.getSelectedItem();
  if (getString(R.string.sort_by_rating).equals(selected)) {
    return Query.Direction.DESCENDING;
  }
  if (getString(R.string.sort_by_price).equals(selected)) {
    return Query.Direction.ASCENDING;
  }
  if (getString(R.string.sort_by_popularity).equals(selected)) {
    return Query.Direction.DESCENDING;
  }
  return null;
}
