private void createRuleXML(){
  CreateXMLRulePanel rulePanel=new CreateXMLRulePanel(xpathQueryArea,codeEditorPane);
  JFrame xmlframe=new JFrame("Create XML Rule");
  xmlframe.setContentPane(rulePanel);
  xmlframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  xmlframe.setSize(new Dimension(600,700));
  xmlframe.addComponentListener(new java.awt.event.ComponentAdapter(){
    @Override public void componentResized(    ComponentEvent e){
      JFrame tmp=(JFrame)e.getSource();
      if (tmp.getWidth() < 600 || tmp.getHeight() < 700) {
        tmp.setSize(600,700);
      }
    }
  }
);
  int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
  int screenWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
  xmlframe.pack();
  xmlframe.setLocation((screenWidth - xmlframe.getWidth()) / 2,(screenHeight - xmlframe.getHeight()) / 2);
  xmlframe.setVisible(true);
}
