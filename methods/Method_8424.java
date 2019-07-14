public void setInfo(TLRPC.User user){
  if (user != null) {
    setInfo(user.id,user.first_name,user.last_name,false,null);
  }
}
