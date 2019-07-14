@Override public void onRating(Rating rating){
  addRating(mRestaurantRef,rating).addOnSuccessListener(this,new OnSuccessListener<Void>(){
    @Override public void onSuccess(    Void aVoid){
      Log.d(TAG,"Rating added");
      hideKeyboard();
      mRatingsRecycler.smoothScrollToPosition(0);
    }
  }
).addOnFailureListener(this,new OnFailureListener(){
    @Override public void onFailure(    @NonNull Exception e){
      Log.w(TAG,"Add rating failed",e);
      hideKeyboard();
      Snackbar.make(findViewById(android.R.id.content),"Failed to add rating",Snackbar.LENGTH_SHORT).show();
    }
  }
);
}
