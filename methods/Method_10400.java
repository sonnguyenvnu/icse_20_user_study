protected final void debugResponse(String TAG,String response){
  if (response != null) {
    Log.d(TAG,"Response data:");
    Log.d(TAG,response);
    addView(getColoredView(LIGHTGREEN,response));
  }
}
