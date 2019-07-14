private void writeNewPost(String userId,String username,String title,String body){
  String key=mDatabase.child("posts").push().getKey();
  Post post=new Post(userId,username,title,body);
  Map<String,Object> postValues=post.toMap();
  Map<String,Object> childUpdates=new HashMap<>();
  childUpdates.put("/posts/" + key,postValues);
  childUpdates.put("/user-posts/" + userId + "/" + key,postValues);
  mDatabase.updateChildren(childUpdates);
}
