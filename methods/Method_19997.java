public void startListening(){
  if (mQuery != null && mRegistration == null) {
    mRegistration=mQuery.addSnapshotListener(this);
  }
}
