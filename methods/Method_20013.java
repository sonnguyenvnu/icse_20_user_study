@Override public void onFilter(Filters filters){
  Query query=mFirestore.collection("restaurants");
  if (filters.hasCategory()) {
    query=query.whereEqualTo(Restaurant.FIELD_CATEGORY,filters.getCategory());
  }
  if (filters.hasCity()) {
    query=query.whereEqualTo(Restaurant.FIELD_CITY,filters.getCity());
  }
  if (filters.hasPrice()) {
    query=query.whereEqualTo(Restaurant.FIELD_PRICE,filters.getPrice());
  }
  if (filters.hasSortBy()) {
    query=query.orderBy(filters.getSortBy(),filters.getSortDirection());
  }
  query=query.limit(LIMIT);
  mAdapter.setQuery(query);
  mCurrentSearchView.setText(Html.fromHtml(filters.getSearchDescription(this)));
  mCurrentSortByView.setText(filters.getOrderDescription(this));
  mViewModel.setFilters(filters);
}
