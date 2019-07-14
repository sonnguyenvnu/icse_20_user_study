void updateBackgroundColor(){
  TerminalSession session=getCurrentTermSession();
  if (session != null && session.getEmulator() != null) {
    getWindow().getDecorView().setBackgroundColor(session.getEmulator().mColors.mCurrentColors[TextStyle.COLOR_INDEX_BACKGROUND]);
  }
}
