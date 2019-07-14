private void setup(){
  input.getDocument().addDocumentListener(new DocumentListener(){
    @Override public void insertUpdate(    DocumentEvent e){
      format();
    }
    @Override public void removeUpdate(    DocumentEvent e){
      format();
    }
    @Override public void changedUpdate(    DocumentEvent e){
      format();
    }
  }
);
  ActionListener settingsAction=e -> format();
  radioConst.addActionListener(settingsAction);
  radioFunc.addActionListener(settingsAction);
  radioDoc.addActionListener(settingsAction);
  prefix.addActionListener(settingsAction);
  prefixTypes.addActionListener(settingsAction);
}
