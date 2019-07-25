public synchronized void sync(Context context){
  SharedPreferences settings=context.getSharedPreferences(QueryTracker.class.getSimpleName(),MODE_PRIVATE);
  long storedNumRequests=settings.getLong(NUM_REQUESTS,0);
  if (storedNumRequests >= numRequests) {
    numRequests=storedNumRequests;
  }
 else {
    SharedPreferences.Editor editor=settings.edit();
    editor.putLong(NUM_REQUESTS,numRequests);
    editor.apply();
  }
}
