private void postComment(){
  final String uid=getUid();
  FirebaseDatabase.getInstance().getReference().child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener(){
    @Override public void onDataChange(    DataSnapshot dataSnapshot){
      User user=dataSnapshot.getValue(User.class);
      String authorName=user.username;
      String commentText=mCommentField.getText().toString();
      Comment comment=new Comment(uid,authorName,commentText);
      mCommentsReference.push().setValue(comment);
      mCommentField.setText(null);
    }
    @Override public void onCancelled(    DatabaseError databaseError){
    }
  }
);
}
