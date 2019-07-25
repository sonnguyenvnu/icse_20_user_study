private void init(String error){
  myErrorString=error;
  setLayout(new BorderLayout());
  myField=new JTextField(error);
  myField.setEditable(false);
  myField.addKeyListener(myEscapeListener);
  JButton button=new JButton(new AbstractAction("OK"){
    @Override public void actionPerformed(    ActionEvent e){
      dispose();
    }
  }
);
  getRootPane().setDefaultButton(button);
  myButtons.add(button);
}
