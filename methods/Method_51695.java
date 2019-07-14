private void init(){
  model.addViewerModelListener(this);
  xPathArea=new JTextArea();
  setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),NLS.nls("XPATH.PANEL.TITLE")));
  add(new JScrollPane(xPathArea),NLS.nls("XPATH.PANEL.EXPRESSION"));
  add(new EvaluationResultsPanel(model),NLS.nls("XPATH.PANEL.RESULTS"));
  setPreferredSize(new Dimension(-1,200));
}
