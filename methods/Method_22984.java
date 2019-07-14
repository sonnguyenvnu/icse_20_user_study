/** 
 * @return the number (1..whatever, not 0-indexed) of the default display
 */
int updateDisplayList(){
  GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
  GraphicsDevice defaultDevice=ge.getDefaultScreenDevice();
  GraphicsDevice[] devices=ge.getScreenDevices();
  int defaultNum=-1;
  displayCount=devices.length;
  String[] items=new String[displayCount];
  for (int i=0; i < displayCount; i++) {
    DisplayMode mode=devices[i].getDisplayMode();
    String title=String.format("%d (%d \u2715 %d)",i + 1,mode.getWidth(),mode.getHeight());
    if (devices[i] == defaultDevice) {
      title+=" default";
      defaultNum=i + 1;
    }
    items[i]=title;
  }
  displaySelectionBox.setModel(new DefaultComboBoxModel<>(items));
  displaySelectionBox.setEnabled(displayCount != 1);
  return defaultNum;
}
