private void onAddItemsClicked(){
  WriteBatch batch=mFirestore.batch();
  for (int i=0; i < 10; i++) {
    DocumentReference restRef=mFirestore.collection("restaurants").document();
    Restaurant randomRestaurant=RestaurantUtil.getRandom(this);
    List<Rating> randomRatings=RatingUtil.getRandomList(randomRestaurant.getNumRatings());
    randomRestaurant.setAvgRating(RatingUtil.getAverageRating(randomRatings));
    batch.set(restRef,randomRestaurant);
    for (    Rating rating : randomRatings) {
      batch.set(restRef.collection("ratings").document(),rating);
    }
  }
  batch.commit().addOnCompleteListener(new OnCompleteListener<Void>(){
    @Override public void onComplete(    @NonNull Task<Void> task){
      if (task.isSuccessful()) {
        Log.d(TAG,"Write batch succeeded.");
      }
 else {
        Log.w(TAG,"write batch failed.",task.getException());
      }
    }
  }
);
}
