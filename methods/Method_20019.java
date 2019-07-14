private void onRestaurantLoaded(Restaurant restaurant){
  mNameView.setText(restaurant.getName());
  mRatingIndicator.setRating((float)restaurant.getAvgRating());
  mNumRatingsView.setText(getString(R.string.fmt_num_ratings,restaurant.getNumRatings()));
  mCityView.setText(restaurant.getCity());
  mCategoryView.setText(restaurant.getCategory());
  mPriceView.setText(RestaurantUtil.getPriceString(restaurant));
  Glide.with(mImageView.getContext()).load(restaurant.getPhoto()).into(mImageView);
}
