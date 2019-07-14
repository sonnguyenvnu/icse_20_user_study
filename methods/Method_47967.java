public void stopListening(){
  commandRunner.removeListener(this);
  preferences.removeListener(this);
}
