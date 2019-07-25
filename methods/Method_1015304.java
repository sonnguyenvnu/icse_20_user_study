public void bling(final int index,String name){
  if (index >= 0 && index < getTabCount() && index != getSelectedIndex()) {
    Component tab=getTabComponentAt(index);
    if (tab != null) {
      SwingUtilities.invokeLater(new Runnable(){
        @Override public void run(){
          setBackgroundAt(index,UIManager.getColor("TabbedPane.selected"));
        }
      }
);
      new BlingTimer(tab,name).start();
    }
  }
}
