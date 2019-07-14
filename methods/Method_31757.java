private void addTopArea(Container container){
  JPanel panel=new JPanel();
  panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
  panel.add(fixedHeight(new JLabel("Birthdate")));
  panel.add(Box.createHorizontalStrut(10));
  final JTextField birthdateField=new JTextField(iBirthdateStr + ' ');
  Document doc=birthdateField.getDocument();
  doc.addDocumentListener(new DocumentListener(){
    public void insertUpdate(    DocumentEvent e){
      update(e);
    }
    public void removeUpdate(    DocumentEvent e){
      update(e);
    }
    public void changedUpdate(    DocumentEvent e){
      update(e);
    }
    private void update(    DocumentEvent e){
      iBirthdateStr=birthdateField.getText();
      updateResults();
    }
  }
);
  panel.add(fixedHeight(birthdateField));
  panel.add(Box.createHorizontalStrut(10));
  Object[] ids=DateTimeZone.getAvailableIDs().toArray();
  final JComboBox zoneSelector=new JComboBox(ids);
  zoneSelector.setSelectedItem(DateTimeZone.getDefault().getID());
  panel.add(fixedSize(zoneSelector));
  zoneSelector.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      String id=(String)zoneSelector.getSelectedItem();
      iChronology=ISOChronology.getInstance(DateTimeZone.forID(id));
      updateResults();
    }
  }
);
  container.add(fixedHeight(panel));
}
