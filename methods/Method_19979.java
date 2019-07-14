private void onStarClicked(DatabaseReference postRef){
  postRef.runTransaction(new Transaction.Handler(){
    @Override public Transaction.Result doTransaction(    MutableData mutableData){
      Post p=mutableData.getValue(Post.class);
      if (p == null) {
        return Transaction.success(mutableData);
      }
      if (p.stars.containsKey(getUid())) {
        p.starCount=p.starCount - 1;
        p.stars.remove(getUid());
      }
 else {
        p.starCount=p.starCount + 1;
        p.stars.put(getUid(),true);
      }
      mutableData.setValue(p);
      return Transaction.success(mutableData);
    }
    @Override public void onComplete(    DatabaseError databaseError,    boolean b,    DataSnapshot dataSnapshot){
      Log.d(TAG,"postTransaction:onComplete:" + databaseError);
    }
  }
);
}
