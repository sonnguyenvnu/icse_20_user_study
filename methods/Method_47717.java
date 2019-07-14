@OnItemSelected(R.id.numericalSpinner) public void onNumericalItemSelected(int position){
  bucketSize=NUMERICAL_BUCKET_SIZES[position];
  refreshData();
}
