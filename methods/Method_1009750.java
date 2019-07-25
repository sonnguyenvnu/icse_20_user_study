public void initialize(OnNumberSelectedListener listener){
  if (mIsInitialized) {
    Log.e(TAG,"This NumbersGrid may only be initialized once.");
    return;
  }
  mSelectionListener=listener;
  mIsInitialized=true;
}
