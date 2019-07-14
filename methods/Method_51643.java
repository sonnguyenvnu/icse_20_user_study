private JPanel createXPathVersionPanel(){
  JPanel p=new JPanel();
  p.add(new JLabel("XPath Version:"));
  for (  Entry<String,String> values : XPathRule.VERSION_DESCRIPTOR.mappings().entrySet()) {
    JRadioButton b=new JRadioButton();
    b.setText(values.getKey());
    b.setActionCommand(b.getText());
    if (values.getKey().equals(XPathRule.VERSION_DESCRIPTOR.defaultValue())) {
      b.setSelected(true);
    }
    xpathVersionButtonGroup.add(b);
    p.add(b);
  }
  return p;
}
