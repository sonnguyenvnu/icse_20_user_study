@Override void tabulate(){
  for (  ImportColumn c : columns.values()) {
    c.tabulate();
    nonBlankCount=Math.max(nonBlankCount,c.nonBlankCount);
  }
  for (  ImportColumnGroup g : subgroups.values()) {
    g.tabulate();
    nonBlankCount=Math.max(nonBlankCount,g.nonBlankCount);
  }
}
