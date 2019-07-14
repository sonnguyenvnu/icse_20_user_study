public static void unregisterUpdates(){
  if (BuildVars.DEBUG_VERSION) {
    UpdateManager.unregister();
  }
}
