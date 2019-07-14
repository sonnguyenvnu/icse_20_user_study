@Override public void execute(@NonNull Runnable command){
  if (Looper.myLooper() == handler.getLooper()) {
    command.run();
  }
 else {
    handler.post(command);
  }
}
