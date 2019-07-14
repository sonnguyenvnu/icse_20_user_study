private void submitPost(){
  final String title=mTitleField.getText().toString();
  final String body=mBodyField.getText().toString();
  if (TextUtils.isEmpty(title)) {
    mTitleField.setError(REQUIRED);
    return;
  }
  if (TextUtils.isEmpty(body)) {
    mBodyField.setError(REQUIRED);
    return;
  }
  setEditingEnabled(false);
  Toast.makeText(this,"Posting...",Toast.LENGTH_SHORT).show();
  final String userId=getUid();
  mDatabase.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener(){
    @Override public void onDataChange(    DataSnapshot dataSnapshot){
      User user=dataSnapshot.getValue(User.class);
      if (user == null) {
        Log.e(TAG,"User " + userId + " is unexpectedly null");
        Toast.makeText(NewPostActivity.this,"Error: could not fetch user.",Toast.LENGTH_SHORT).show();
      }
 else {
        writeNewPost(userId,user.username,title,body);
      }
      setEditingEnabled(true);
      finish();
    }
    @Override public void onCancelled(    DatabaseError databaseError){
      Log.w(TAG,"getUser:onCancelled",databaseError.toException());
      setEditingEnabled(true);
    }
  }
);
}
