private void unimplementedSequence(int b){
  logError("Unimplemented sequence char '" + (char)b + "' (U+" + String.format("%04x",b) + ")");
  finishSequence();
}
