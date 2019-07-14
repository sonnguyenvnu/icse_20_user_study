private JPanel makeResultsPanel(){
  JPanel resultsPanel=new JPanel();
  resultsPanel.setLayout(new BorderLayout());
  JScrollPane areaScrollPane=new JScrollPane(resultsTextArea);
  resultsTextArea.setEditable(false);
  areaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
  areaScrollPane.setPreferredSize(new Dimension(600,300));
  resultsPanel.add(makeMatchList(),BorderLayout.WEST);
  resultsPanel.add(areaScrollPane,BorderLayout.CENTER);
  return resultsPanel;
}
