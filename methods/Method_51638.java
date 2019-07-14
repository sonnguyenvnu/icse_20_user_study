private JComponent createCodeEditorPanel(){
  JPanel p=new JPanel();
  p.setLayout(new BorderLayout());
  codeEditorPane.setBorder(BorderFactory.createLineBorder(Color.black));
  makeTextComponentUndoable(codeEditorPane);
  p.add(new JLabel("Source code:"),BorderLayout.NORTH);
  p.add(new JScrollPane(codeEditorPane),BorderLayout.CENTER);
  return p;
}
