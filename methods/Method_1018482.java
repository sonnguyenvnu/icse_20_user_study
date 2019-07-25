private void update(){
  boolean was=isPortrait;
  isPortrait=calculateIsPortrait();
  if (was != isPortrait) {
    for (    CommandSet helper : commandSets) {
      helper.fire();
    }
  }
}
