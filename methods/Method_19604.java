private static String getSnapModeString(int snapMode){
  for (int i=0; i < SNAP_MODE_INT.length; i++) {
    if (snapMode == SNAP_MODE_INT[i]) {
      return SNAP_MODE_STRING[i];
    }
  }
  return SNAP_MODE_STRING[0];
}
