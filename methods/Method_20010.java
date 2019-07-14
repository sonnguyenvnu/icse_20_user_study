public String getOrderDescription(Context context){
  if (Restaurant.FIELD_PRICE.equals(sortBy)) {
    return context.getString(R.string.sorted_by_price);
  }
 else   if (Restaurant.FIELD_POPULARITY.equals(sortBy)) {
    return context.getString(R.string.sorted_by_popularity);
  }
 else {
    return context.getString(R.string.sorted_by_rating);
  }
}
