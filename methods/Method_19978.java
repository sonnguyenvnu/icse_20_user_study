@Override public Query getQuery(DatabaseReference databaseReference){
  return databaseReference.child("user-posts").child(getUid());
}
