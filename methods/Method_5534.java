private void handleG3Character(int characterCode){
  if (characterCode == 0xA0) {
    currentCueBuilder.append('\u33C4');
  }
 else {
    Log.w(TAG,"Invalid G3 character: " + characterCode);
    currentCueBuilder.append('_');
  }
}
