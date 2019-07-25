void invite(){
  ContactViewModel contactViewModel=ViewModelProviders.of(this).get(ContactViewModel.class);
  contactViewModel.invite(userInfo.uid,introTextView.getText().toString()).observe(this,new Observer<Boolean>(){
    @Override public void onChanged(    @Nullable Boolean aBoolean){
      if (aBoolean) {
        Toast.makeText(InviteFriendActivity.this,"???????",Toast.LENGTH_SHORT).show();
        finish();
      }
 else {
        Toast.makeText(InviteFriendActivity.this,"??????",Toast.LENGTH_SHORT).show();
      }
    }
  }
);
}
