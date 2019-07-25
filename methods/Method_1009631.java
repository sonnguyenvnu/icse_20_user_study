@PostConstruct public void init(){
  addWindowListener(new WindowAdapter(){
    @Override public void windowClosing(    WindowEvent windowEvent){
      dispose();
    }
  }
);
  JScrollPane containerTreePane;
  containerTreePane=new JScrollPane(treeView.asUIComponent());
  containerTreePane.setMinimumSize(new Dimension(180,200));
  JSplitPane splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
  splitPane.setBorder(new EmptyBorder(10,10,10,10));
  splitPane.setLeftComponent(containerTreePane);
  splitPane.setRightComponent(detailView.asUIComponent());
  splitPane.setResizeWeight(0.65);
  setLayout(new BorderLayout());
  setPreferredSize(new Dimension(700,500));
  setMinimumSize(new Dimension(500,250));
  add(splitPane,BorderLayout.CENTER);
  pack();
  setVisible(true);
}
