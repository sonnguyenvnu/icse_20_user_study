@Override public void init(ISafetyNetCallbacks callbacks) throws RemoteException {
  Log.d(TAG,"dummy Method: init");
  callbacks.onBoolean(Status.SUCCESS,true);
}
