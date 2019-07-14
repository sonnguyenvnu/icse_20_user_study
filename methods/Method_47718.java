@OnItemSelected(R.id.boolSpinner) public void onBoolItemSelected(int position){
  bucketSize=BOOLEAN_BUCKET_SIZES[position];
  refreshData();
}
