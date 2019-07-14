private void init(){
  model.addViewerModelListener(this);
  setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),NLS.nls("SOURCE.PANEL.TITLE")));
  setLayout(new BorderLayout());
  sourceCodeArea=new JTextArea();
  add(new JScrollPane(sourceCodeArea),BorderLayout.CENTER);
}
