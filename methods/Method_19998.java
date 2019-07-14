public void stopListening(){
  if (mRegistration != null) {
    mRegistration.remove();
    mRegistration=null;
  }
  mSnapshots.clear();
  notifyDataSetChanged();
}
