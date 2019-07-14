public String getUid(){
  return FirebaseAuth.getInstance().getCurrentUser().getUid();
}
