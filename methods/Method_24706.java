public void toggleDebug(){
  debugEnabled=!debugEnabled;
  rebuildToolbar();
  repaint();
  if (debugEnabled) {
    debugItem.setText(Language.text("menu.debug.disable"));
  }
 else {
    debugItem.setText(Language.text("menu.debug.enable"));
  }
  inspector.setVisible(debugEnabled);
  for (  Component item : debugMenu.getMenuComponents()) {
    if (item instanceof JMenuItem && item != debugItem) {
      item.setEnabled(debugEnabled);
    }
  }
}
