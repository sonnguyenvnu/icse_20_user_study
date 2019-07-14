private JPanel createXPathQueryPanel(){
  JPanel p=new JPanel();
  p.setLayout(new BorderLayout());
  xpathQueryArea.setBorder(BorderFactory.createLineBorder(Color.black));
  makeTextComponentUndoable(xpathQueryArea);
  JScrollPane scrollPane=new JScrollPane(xpathQueryArea);
  scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
  final JButton b=createGoButton();
  JPanel topPanel=new JPanel();
  topPanel.setLayout(new BorderLayout());
  topPanel.add(new JLabel("XPath Query (if any):"),BorderLayout.WEST);
  topPanel.add(createXPathVersionPanel(),BorderLayout.EAST);
  p.add(topPanel,BorderLayout.NORTH);
  p.add(scrollPane,BorderLayout.CENTER);
  p.add(b,BorderLayout.SOUTH);
  return p;
}
