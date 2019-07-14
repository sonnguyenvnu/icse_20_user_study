private void emitPending(){
  try {
    for (    Event e : pendingEmit) {
      Log.i("SyncManager","Emitting: " + e.message);
      socket.emit(EVENT_POST_EVENT,new JSONObject(e.message));
      pendingConfirmation.add(e);
    }
    pendingEmit.clear();
  }
 catch (  JSONException e) {
    throw new RuntimeException(e);
  }
}
