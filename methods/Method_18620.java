private void debugLogStartingAnimations(){
  if (!AnimationsDebug.ENABLED) {
    throw new RuntimeException("Trying to debug log animations without debug flag set!");
  }
  Log.d(TAG,"Starting animations:");
}
