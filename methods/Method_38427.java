@Override public void end(){
  if (!regionMarkers.isEmpty()) {
    throw new DecoraException("Invalid regions detected: " + regionMarkers.getLast().name);
  }
}
