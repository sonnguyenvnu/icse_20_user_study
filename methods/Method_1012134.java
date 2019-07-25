@Override public void init(){
  JPanel content=new JPanel();
  content.setLayout(new GridLayoutManager(1,2,JBUI.emptyInsets(),-1,-1));
  JBLabel label=new JBLabel("Plugin ID:");
  content.add(label,new GridConstraints(0,0,1,1,GridConstraints.ANCHOR_WEST,GridConstraints.FILL_NONE,GridConstraints.SIZEPOLICY_FIXED,GridConstraints.SIZEPOLICY_FIXED,null,null,null,0,false));
  myTextField=new JTextField(myIdeaPluginModuleFacet.getPluginId());
  content.add(myTextField,new GridConstraints(0,1,1,1,GridConstraints.ANCHOR_WEST,GridConstraints.FILL_HORIZONTAL,GridConstraints.SIZEPOLICY_CAN_GROW,GridConstraints.SIZEPOLICY_FIXED,null,null,null,0,false));
  JPanel outerPanel=new JPanel();
  outerPanel.setLayout(new GridLayoutManager(1,1,new JBInsets(10,10,10,10),-1,-1));
  outerPanel.add(content,new GridConstraints(0,0,1,1,GridConstraints.ANCHOR_NORTHWEST,GridConstraints.FILL_HORIZONTAL,GridConstraints.SIZEPOLICY_WANT_GROW,GridConstraints.SIZEPOLICY_FIXED,null,new Dimension(150,-1),null,0,false));
  setTabComponent(outerPanel);
}
