private void writeNewUser(String userId,String name,String email){
  User user=new User(name,email);
  mDatabase.child("users").child(userId).setValue(user);
}
