public static DraweeEventTracker newInstance(){
  return sEnabled ? new DraweeEventTracker() : sInstance;
}
