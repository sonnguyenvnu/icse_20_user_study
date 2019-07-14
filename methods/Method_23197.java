void handleSettings(){
  insideSettings=true;
  GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
  GraphicsDevice device=ge.getDefaultScreenDevice();
  displayDevices=ge.getScreenDevices();
  if (display > 0 && display <= displayDevices.length) {
    device=displayDevices[display - 1];
  }
  DisplayMode displayMode=device.getDisplayMode();
  displayWidth=displayMode.getWidth();
  displayHeight=displayMode.getHeight();
  settings();
  if (display == SPAN && platform == MACOSX) {
    Process p=exec("defaults","read","com.apple.spaces","spans-displays");
    BufferedReader outReader=createReader(p.getInputStream());
    BufferedReader errReader=createReader(p.getErrorStream());
    StringBuilder stdout=new StringBuilder();
    StringBuilder stderr=new StringBuilder();
    String line=null;
    try {
      while ((line=outReader.readLine()) != null) {
        stdout.append(line);
      }
      while ((line=errReader.readLine()) != null) {
        stderr.append(line);
      }
    }
 catch (    IOException e) {
      printStackTrace(e);
    }
    int resultCode=-1;
    try {
      resultCode=p.waitFor();
    }
 catch (    InterruptedException e) {
    }
    String result=trim(stdout.toString());
    if ("0".equals(result)) {
      EventQueue.invokeLater(new Runnable(){
        public void run(){
          checkLookAndFeel();
          final String msg="To use fullScreen(SPAN), first turn off “Displays have separate spaces”\n" + "in System Preferences \u2192 Mission Control. Then log out and log back in.";
          JOptionPane.showMessageDialog(null,msg,"Apple's Defaults Stink",JOptionPane.WARNING_MESSAGE);
        }
      }
);
    }
 else     if (!"1".equals(result)) {
      System.err.println("Could not check the status of “Displays have separate spaces.”");
      System.err.format("Received message '%s' and result code %d.%n",trim(stderr.toString()),resultCode);
    }
  }
  insideSettings=false;
}
