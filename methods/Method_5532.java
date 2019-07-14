private void handleG1Character(int characterCode){
  currentCueBuilder.append((char)(characterCode & 0xFF));
}
