public void init(Base base){
  this.base=base;
  Container paine=getContentPane();
  paine.setLayout(new BorderLayout());
  JPanel pain=new JPanel();
  pain.setBorder(new EmptyBorder(13,13,13,13));
  paine.add(pain,BorderLayout.CENTER);
  pain.setLayout(new BoxLayout(pain,BoxLayout.Y_AXIS));
  String labelText=Language.text("create_font.label");
  JTextArea textarea=new JTextArea(labelText);
  textarea.setBorder(new EmptyBorder(10,10,20,10));
  textarea.setBackground(null);
  textarea.setEditable(false);
  textarea.setHighlighter(null);
  textarea.setFont(new Font("Dialog",Font.PLAIN,12));
  pain.add(textarea);
  GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
  Font[] fonts=ge.getAllFonts();
  String[] fontList=new String[fonts.length];
  table=new HashMap<String,Font>();
  int index=0;
  for (int i=0; i < fonts.length; i++) {
    try {
      fontList[index++]=fonts[i].getPSName();
      table.put(fonts[i].getPSName(),fonts[i]);
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
  list=new String[index];
  System.arraycopy(fontList,0,list,0,index);
  fontSelector=new JList<String>(list);
  fontSelector.addListSelectionListener(new ListSelectionListener(){
    public void valueChanged(    ListSelectionEvent e){
      if (e.getValueIsAdjusting() == false) {
        selection=fontSelector.getSelectedIndex();
        okButton.setEnabled(true);
        update();
      }
    }
  }
);
  fontSelector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  fontSelector.setVisibleRowCount(12);
  JScrollPane fontScroller=new JScrollPane(fontSelector);
  pain.add(fontScroller);
  Dimension d1=new Dimension(13,13);
  pain.add(new Box.Filler(d1,d1,d1));
  sample=new SampleComponent(this);
  sample.setFont(new Font("Dialog",Font.PLAIN,12));
  pain.add(sample);
  Dimension d2=new Dimension(6,6);
  pain.add(new Box.Filler(d2,d2,d2));
  JPanel panel=new JPanel();
  panel.add(new JLabel(Language.text("create_font.size") + ":"));
  sizeSelector=new JTextField(" 48 ");
  sizeSelector.getDocument().addDocumentListener(new DocumentListener(){
    public void insertUpdate(    DocumentEvent e){
      update();
    }
    public void removeUpdate(    DocumentEvent e){
      update();
    }
    public void changedUpdate(    DocumentEvent e){
    }
  }
);
  panel.add(sizeSelector);
  smoothBox=new JCheckBox(Language.text("create_font.smooth"));
  smoothBox.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      smooth=smoothBox.isSelected();
      update();
    }
  }
);
  smoothBox.setSelected(smooth);
  panel.add(smoothBox);
  charsetButton=new JButton(Language.text("create_font.characters"));
  charsetButton.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      charSelector.setVisible(true);
    }
  }
);
  panel.add(charsetButton);
  pain.add(panel);
  JPanel filestuff=new JPanel();
  filestuff.add(new JLabel(Language.text("create_font.filename") + ":"));
  filestuff.add(filenameField=new JTextField(20));
  filestuff.add(new JLabel(".vlw"));
  pain.add(filestuff);
  JPanel buttons=new JPanel();
  JButton cancelButton=new JButton(Language.text("prompt.cancel"));
  cancelButton.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      setVisible(false);
    }
  }
);
  okButton=new JButton(Language.text("prompt.ok"));
  okButton.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      build();
    }
  }
);
  okButton.setEnabled(false);
  buttons.add(cancelButton);
  buttons.add(okButton);
  pain.add(buttons);
  JRootPane root=getRootPane();
  root.setDefaultButton(okButton);
  ActionListener disposer=new ActionListener(){
    public void actionPerformed(    ActionEvent actionEvent){
      setVisible(false);
    }
  }
;
  Toolkit.registerWindowCloseKeys(root,disposer);
  Toolkit.setIcon(this);
  pack();
  setResizable(false);
  sample.setFont(new Font(list[0],Font.PLAIN,48));
  fontSelector.setSelectedIndex(0);
  setLocationRelativeTo(null);
  charSelector=new CharacterSelector();
}
