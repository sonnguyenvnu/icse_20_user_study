private JComponent createXPathResultPanel(){
  xpathResults.addElement("No XPath results yet, run an XPath Query first.");
  xpathResultList.setBorder(BorderFactory.createLineBorder(Color.black));
  xpathResultList.setFixedCellWidth(300);
  xpathResultList.setCellRenderer(new ASTListCellRenderer());
  xpathResultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  xpathResultList.getSelectionModel().addListSelectionListener(new ASTSelectionListener());
  JScrollPane scrollPane=new JScrollPane();
  scrollPane.getViewport().setView(xpathResultList);
  return scrollPane;
}
