private Task<Void> addRating(final DocumentReference restaurantRef,final Rating rating){
  final DocumentReference ratingRef=restaurantRef.collection("ratings").document();
  return mFirestore.runTransaction(new Transaction.Function<Void>(){
    @Override public Void apply(    Transaction transaction) throws FirebaseFirestoreException {
      Restaurant restaurant=transaction.get(restaurantRef).toObject(Restaurant.class);
      int newNumRatings=restaurant.getNumRatings() + 1;
      double oldRatingTotal=restaurant.getAvgRating() * restaurant.getNumRatings();
      double newAvgRating=(oldRatingTotal + rating.getRating()) / newNumRatings;
      restaurant.setNumRatings(newNumRatings);
      restaurant.setAvgRating(newAvgRating);
      transaction.set(restaurantRef,restaurant);
      transaction.set(ratingRef,rating);
      return null;
    }
  }
);
}
