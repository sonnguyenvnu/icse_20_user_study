void update(Editor editor){
  editorBounds=editor.getBounds();
  dividerLocation=editor.getDividerLocation();
  GraphicsConfiguration config=editor.getGraphicsConfiguration();
  deviceBounds=config.getBounds();
}
