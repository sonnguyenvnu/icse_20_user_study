@Override public void notify(String message){
  LOGGER.info(message);
  statusLine.fireEvent(new SnackbarEvent(new JFXSnackbarLayout(message),TOAST_MESSAGE_DISPLAY_TIME,null));
}
