/** 
 * Figure out the next location by sizing up the last editor in the list. If no editors are opened, it'll just open on the main screen.
 * @param editors List of editors currently opened
 */
void defaultLocation(List<Editor> editors){
  int defaultWidth=Toolkit.zoom(Preferences.getInteger("editor.window.width.default"));
  int defaultHeight=Toolkit.zoom(Preferences.getInteger("editor.window.height.default"));
  defaultWidth=Math.min(defaultWidth,deviceBounds.width);
  defaultHeight=Math.min(defaultHeight,deviceBounds.height);
  if (editors.size() == 0) {
    int editorX=deviceBounds.x + (deviceBounds.width - defaultWidth) / 2;
    int editorY=deviceBounds.y + (deviceBounds.height - defaultHeight) / 2;
    editorBounds=new Rectangle(editorX,editorY,defaultWidth,defaultHeight);
    dividerLocation=0;
  }
 else {
synchronized (editors) {
      Editor lastOpened=editors.get(editors.size() - 1);
      isMaximized=(lastOpened.getExtendedState() == Frame.MAXIMIZED_BOTH);
      editorBounds=lastOpened.getBounds();
      editorBounds.x+=WINDOW_OFFSET;
      editorBounds.y+=WINDOW_OFFSET;
      dividerLocation=lastOpened.getDividerLocation();
      if (!deviceBounds.contains(editorBounds)) {
        editorBounds.x=deviceBounds.x + (int)(Math.random() * (deviceBounds.width - defaultWidth));
        editorBounds.y=deviceBounds.y + (int)(Math.random() * (deviceBounds.height - defaultHeight));
      }
      if (isMaximized) {
        editorBounds.width=defaultWidth;
        editorBounds.height=defaultHeight;
      }
    }
  }
}
