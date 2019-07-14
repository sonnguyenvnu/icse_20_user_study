@Override public Query getQuery(DatabaseReference databaseReference){
  Query recentPostsQuery=databaseReference.child("posts").limitToFirst(100);
  return recentPostsQuery;
}
