private void setBucketSizeFromPosition(int position){
  if (prefs == null)   return;
  prefs.setDefaultScoreSpinnerPosition(position);
  bucketSize=BUCKET_SIZES[position];
}
