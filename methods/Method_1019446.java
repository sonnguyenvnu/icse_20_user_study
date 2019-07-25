private void post(Runnable r){
  if (Looper.getMainLooper() == Looper.myLooper()) {
    r.run();
  }
 else {
    handler.post(r);
  }
}
