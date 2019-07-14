public boolean write(User user,boolean like,Context context){
  if (shouldWrite(user,context)) {
    add(new FollowUserWriter(user,like,this),context);
    return true;
  }
 else {
    return false;
  }
}
