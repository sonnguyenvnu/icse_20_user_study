public JComponent config(){
  poll();
  JPanel top=new JPanel(new GridBagLayout());
  GridBagConstraints c=new GridBagConstraints();
  c.fill=GridBagConstraints.BOTH;
  c.insets=new Insets(0,5,0,5);
  c.ipadx=5;
  c.gridx=0;
  c.gridy=0;
  for (  Map.Entry<File,JCheckBox> item : items.entrySet()) {
    File file=item.getKey();
    boolean exists=file.exists();
    JCheckBox box=item.getValue();
    if (box == null) {
      box=new JCheckBox(file.getName(),exists);
      item.setValue(box);
    }
    if (!exists) {
      box.setSelected(false);
      box.setEnabled(false);
    }
    c.weightx=1.0;
    top.add(box,c);
    CustomJButton open=exists ? new CustomJButton(MetalIconFactory.getTreeLeafIcon()) : new CustomJButton("+");
    open.setActionCommand(file.getAbsolutePath());
    open.setToolTipText((exists ? "" : Messages.getString("DbgPacker.1") + " ") + file.getAbsolutePath());
    open.addActionListener(this);
    c.gridx++;
    c.weightx=0.0;
    top.add(open,c);
    c.gridx--;
    c.gridy++;
  }
  c.weightx=2.0;
  CustomJButton debugPack=new CustomJButton(Messages.getString("DbgPacker.2"));
  debugPack.setActionCommand("pack");
  debugPack.addActionListener(this);
  top.add(debugPack,c);
  openZip=new CustomJButton(MetalIconFactory.getTreeFolderIcon());
  openZip.setActionCommand("showzip");
  openZip.setToolTipText(Messages.getString("DbgPacker.3"));
  openZip.setEnabled(false);
  openZip.addActionListener(this);
  c.gridx++;
  c.weightx=0.0;
  top.add(openZip,c);
  return top;
}
