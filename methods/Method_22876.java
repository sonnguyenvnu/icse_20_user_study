protected Container createColorFields(String buttonName,ActionListener buttonListener){
  Box box=Box.createVerticalBox();
  box.setAlignmentY(0);
  final int GAP=Platform.isWindows() ? 5 : 0;
  final int BETWEEN=Platform.isWindows() ? 8 : 6;
  Box row;
  row=Box.createHorizontalBox();
  if (Platform.isMacOS()) {
    row.add(Box.createHorizontalStrut(17));
  }
 else {
    row.add(createFixedLabel(""));
  }
  colorPanel=new JPanel(){
    public void paintComponent(    Graphics g){
      g.setColor(new Color(red,green,blue));
      Dimension size=getSize();
      g.fillRect(0,0,size.width,size.height);
    }
  }
;
  colorPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
  Dimension dim=new Dimension(70,25);
  colorPanel.setMinimumSize(dim);
  colorPanel.setMaximumSize(dim);
  colorPanel.setPreferredSize(dim);
  row.add(colorPanel);
  row.add(Box.createHorizontalGlue());
  box.add(row);
  box.add(Box.createVerticalStrut(BETWEEN));
  row=Box.createHorizontalBox();
  row.add(createFixedLabel("H"));
  row.add(hueField=new NumberField(4,false));
  row.add(new JLabel(" \u00B0"));
  row.add(Box.createHorizontalGlue());
  box.add(row);
  box.add(Box.createVerticalStrut(GAP));
  row=Box.createHorizontalBox();
  row.add(createFixedLabel("S"));
  row.add(saturationField=new NumberField(4,false));
  row.add(new JLabel(" %"));
  row.add(Box.createHorizontalGlue());
  box.add(row);
  box.add(Box.createVerticalStrut(GAP));
  row=Box.createHorizontalBox();
  row.add(createFixedLabel("B"));
  row.add(brightnessField=new NumberField(4,false));
  row.add(new JLabel(" %"));
  row.add(Box.createHorizontalGlue());
  box.add(row);
  box.add(Box.createVerticalStrut(BETWEEN));
  row=Box.createHorizontalBox();
  row.add(createFixedLabel("R"));
  row.add(redField=new NumberField(4,false));
  row.add(Box.createHorizontalGlue());
  box.add(row);
  box.add(Box.createVerticalStrut(GAP));
  row=Box.createHorizontalBox();
  row.add(createFixedLabel("G"));
  row.add(greenField=new NumberField(4,false));
  row.add(Box.createHorizontalGlue());
  box.add(row);
  box.add(Box.createVerticalStrut(GAP));
  row=Box.createHorizontalBox();
  row.add(createFixedLabel("B"));
  row.add(blueField=new NumberField(4,false));
  row.add(Box.createHorizontalGlue());
  box.add(row);
  box.add(Box.createVerticalStrut(BETWEEN));
  row=Box.createHorizontalBox();
  row.add(createFixedLabel(""));
  final int hexCount=Platform.isWindows() ? 7 : 6;
  row.add(hexField=new NumberField(hexCount,true));
  row.add(Box.createHorizontalGlue());
  box.add(row);
  box.add(Box.createVerticalStrut(GAP));
  row=Box.createHorizontalBox();
  if (Platform.isMacOS()) {
    row.add(Box.createHorizontalStrut(11));
  }
 else {
    row.add(createFixedLabel(""));
  }
  JButton button=new JButton(buttonName);
  button.addActionListener(buttonListener);
  row.add(button);
  row.add(Box.createHorizontalGlue());
  box.add(row);
  row=Box.createHorizontalBox();
  if (Platform.isMacOS()) {
    row.add(Box.createHorizontalStrut(11));
  }
 else {
    row.add(createFixedLabel(""));
  }
  button=new JButton(Language.text("prompt.cancel"));
  button.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      ColorChooser.this.hide();
    }
  }
);
  row.add(button);
  row.add(Box.createHorizontalGlue());
  box.add(row);
  box.add(Box.createVerticalGlue());
  return box;
}
