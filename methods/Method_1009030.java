@Override public void process(Style s,SwitchProcessor sp){
  if (sp.isStyleFound()) {
    return;
  }
  TocEntry te=sp.getEntry();
  int level=sp.styleBasedOnHelper.getBasedOnHeading(s);
  if (level != -1) {
    if (fieldArgument == null) {
      te.setEntryLevel(level);
      sp.setStyleFound(true);
    }
 else     if (level >= getStartLevel() && level <= getEndLevel()) {
      te.setEntryLevel(level);
      sp.setStyleFound(true);
    }
  }
}
