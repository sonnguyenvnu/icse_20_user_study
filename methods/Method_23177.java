@Override public void initFrame(final PApplet sketch){
  this.sketch=sketch;
  GraphicsEnvironment environment=GraphicsEnvironment.getLocalGraphicsEnvironment();
  int displayNum=sketch.sketchDisplay();
  if (displayNum > 0) {
    GraphicsDevice[] devices=environment.getScreenDevices();
    if (displayNum <= devices.length) {
      displayDevice=devices[displayNum - 1];
    }
 else {
      System.err.format("Display %d does not exist, " + "using the default display instead.%n",displayNum);
      for (int i=0; i < devices.length; i++) {
        System.err.format("Display %d is %s%n",(i + 1),devices[i]);
      }
    }
  }
  if (displayDevice == null) {
    displayDevice=environment.getDefaultScreenDevice();
  }
  boolean spanDisplays=sketch.sketchDisplay() == PConstants.SPAN;
  screenRect=spanDisplays ? getDisplaySpan() : displayDevice.getDefaultConfiguration().getBounds();
  sketch.displayWidth=screenRect.width;
  sketch.displayHeight=screenRect.height;
  windowScaleFactor=PApplet.platform == PConstants.MACOSX ? 1 : sketch.pixelDensity;
  sketchWidth=sketch.sketchWidth() * windowScaleFactor;
  sketchHeight=sketch.sketchHeight() * windowScaleFactor;
  boolean fullScreen=sketch.sketchFullScreen();
  if (fullScreen || spanDisplays) {
    sketchWidth=screenRect.width;
    sketchHeight=screenRect.height;
  }
  frame=new JFrame(displayDevice.getDefaultConfiguration());
  final Color windowColor=new Color(sketch.sketchWindowColor(),false);
  if (frame instanceof JFrame) {
    ((JFrame)frame).getContentPane().setBackground(windowColor);
  }
 else {
    frame.setBackground(windowColor);
  }
  setProcessingIcon(frame);
  frame.add(canvas);
  setSize(sketchWidth / windowScaleFactor,sketchHeight / windowScaleFactor);
  frame.setLayout(null);
  if (fullScreen) {
    frame.invalidate();
  }
 else {
  }
  frame.setResizable(false);
  frame.addWindowListener(new WindowAdapter(){
    @Override public void windowClosing(    WindowEvent e){
      sketch.exit();
    }
  }
);
}
