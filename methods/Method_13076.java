protected void create(){
  setLayout(cardLayout=new CardLayout());
  add("panel",new JPanel());
  add("tabs",tabbedPane=createTabPanel());
}
