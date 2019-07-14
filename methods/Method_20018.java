/** 
 * Listener for the Restaurant document ( {@link #mRestaurantRef}).
 */
@Override public void onEvent(DocumentSnapshot snapshot,FirebaseFirestoreException e){
  if (e != null) {
    Log.w(TAG,"restaurant:onEvent",e);
    return;
  }
  onRestaurantLoaded(snapshot.toObject(Restaurant.class));
}
