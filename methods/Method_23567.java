static public void init(final PApplet sketch){
  if (application == null) {
    application=Application.getApplication();
  }
  application.setQuitHandler(new QuitHandler(){
    public void handleQuitRequestWith(    QuitEvent event,    QuitResponse response){
      sketch.exit();
      if (PApplet.uncaughtThrowable == null && !attemptedQuit) {
        response.cancelQuit();
        attemptedQuit=true;
      }
 else {
        response.performQuit();
      }
    }
  }
);
}
