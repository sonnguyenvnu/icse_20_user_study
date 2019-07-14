void updateTabName(String name){
  if (DEBUG) {
    System.out.println(">>>>>" + name + "  " + MainPanel.getTabbIndex(this));
    StackTraceElement[] s=new Throwable().getStackTrace();
    System.out.println("  .(" + s[1].getFileName() + ":" + s[1].getLineNumber() + ") ");
    System.out.println("  .(" + s[2].getFileName() + ":" + s[2].getLineNumber() + ") ");
  }
  Container tabb=getParent();
  while (!(tabb instanceof JTabbedPane)) {
    tabb=tabb.getParent();
  }
  JTabbedPane tabbedPane=(JTabbedPane)tabb;
  int index=MainPanel.getTabbIndex(this);
  tabbedPane.setTitleAt(index,name);
}
