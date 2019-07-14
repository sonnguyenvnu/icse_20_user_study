public void onPressDeveloperCountdown(){
  developerCountdown--;
  if (developerCountdown == 0) {
    prefs.setDeveloper(true);
    screen.showMessage(Message.YOU_ARE_NOW_A_DEVELOPER);
  }
}
