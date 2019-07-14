private void unknownSequence(int b){
  logError("Unknown sequence char '" + (char)b + "' (numeric value=" + b + ")");
  finishSequence();
}
