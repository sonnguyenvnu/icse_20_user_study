static protected void init(final Base base){
  if (application == null) {
    application=Application.getApplication();
  }
  if (adapter == null) {
    adapter=new ThinkDifferent();
  }
  application.setAboutHandler(new AboutHandler(){
    public void handleAbout(    AboutEvent ae){
      new About(null);
    }
  }
);
  application.setPreferencesHandler(new PreferencesHandler(){
    public void handlePreferences(    PreferencesEvent arg0){
      base.handlePrefs();
    }
  }
);
  application.setOpenFileHandler(new OpenFilesHandler(){
    public void openFiles(    OpenFilesEvent event){
      for (      File file : event.getFiles()) {
        base.handleOpen(file.getAbsolutePath());
      }
    }
  }
);
  application.setPrintFileHandler(new PrintFilesHandler(){
    public void printFiles(    PrintFilesEvent event){
    }
  }
);
  application.setQuitHandler(new QuitHandler(){
    public void handleQuitRequestWith(    QuitEvent event,    QuitResponse response){
      if (base.handleQuit()) {
        response.performQuit();
      }
 else {
        response.cancelQuit();
      }
    }
  }
);
  JMenuBar defaultMenuBar=new JMenuBar();
  JMenu fileMenu=buildFileMenu(base);
  defaultMenuBar.add(fileMenu);
  Base.defaultFileMenu=fileMenu;
  try {
    application.setDefaultMenuBar(defaultMenuBar);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
