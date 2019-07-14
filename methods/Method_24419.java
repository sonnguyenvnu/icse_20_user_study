public void initOffscreen(PApplet sketch){
  this.sketch=sketch;
  sketchWidth=sketch.sketchWidth();
  sketchHeight=sketch.sketchHeight();
  if (window != null) {
    canvas=new NewtCanvasAWT(window);
    canvas.setBounds(0,0,window.getWidth(),window.getHeight());
    canvas.setFocusable(true);
  }
}
