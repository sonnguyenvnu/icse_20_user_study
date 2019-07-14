static public void addDisabledItem(JMenu menu,String title){
  JMenuItem item=new JMenuItem(title);
  item.setEnabled(false);
  menu.add(item);
}
