public boolean shouldBeGarbageCollected(){
  boolean should=isCancelled() || isFinished();
  if (should)   request.clear();
  return should;
}
