private void onSubmitClicked(View view){
  Rating rating=new Rating(FirebaseAuth.getInstance().getCurrentUser(),mRatingBar.getRating(),mRatingText.getText().toString());
  if (mRatingListener != null) {
    mRatingListener.onRating(rating);
  }
  dismiss();
}
