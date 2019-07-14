/** 
 * TODO This needs to be called when the listing loads, and also whenever the contribs list has been updated (for whatever reason). In addition, the caller (presumably Base) should update all Editor windows with the correct information on the number of items available.
 * @return The number of contributions that have available updates.
 */
public int countUpdates(Base base){
  int count=0;
  for (  ModeContribution mc : base.getModeContribs()) {
    if (hasUpdates(mc)) {
      count++;
    }
  }
  for (  Library lib : base.getActiveEditor().getMode().contribLibraries) {
    if (hasUpdates(lib)) {
      count++;
    }
  }
  for (  Library lib : base.getActiveEditor().getMode().coreLibraries) {
    if (hasUpdates(lib)) {
      count++;
    }
  }
  for (  ToolContribution tc : base.getToolContribs()) {
    if (hasUpdates(tc)) {
      count++;
    }
  }
  for (  ExamplesContribution ec : base.getExampleContribs()) {
    if (hasUpdates(ec)) {
      count++;
    }
  }
  return count;
}
