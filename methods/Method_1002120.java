@Override public void log(IClearcutLoggerCallbacks callbacks,LogEventParcelable event) throws RemoteException {
  Log.d(TAG,"log: " + event);
  callbacks.onStatus(Status.SUCCESS);
}
