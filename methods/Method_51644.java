private JButton createGoButton(){
  JButton b=new JButton("Go");
  b.setMnemonic('g');
  b.addActionListener(new ShowListener());
  b.addActionListener(new XPathListener());
  b.addActionListener(new DFAListener());
  b.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      saveSettings();
    }
  }
);
  return b;
}
