public JComponent build(){
  ComponentOrientation orientation=ComponentOrientation.getOrientation(PMS.getLocale());
  String colSpec=FormLayoutUtil.getColSpec(COL_SPEC,orientation);
  FormLayout layout=new FormLayout(colSpec,ROW_SPEC);
  PanelBuilder builder=new PanelBuilder(layout);
  builder.border(Borders.DLU4);
  builder.opaque(true);
  CellConstraints cc=new CellConstraints();
  JComponent component;
  installedPluginsSeparator=(JPanel)builder.addSeparator(Messages.getString("PluginTab.0"),FormLayoutUtil.flip(cc.xyw(1,7,9),colSpec,orientation));
  installedPluginsSeparator.setVisible(false);
  component=(JComponent)installedPluginsSeparator.getComponent(0);
  component.setFont(component.getFont().deriveFont(Font.BOLD));
  pPlugins=new JPanel(new GridLayout());
  pPlugins.setVisible(false);
  builder.add(pPlugins,FormLayoutUtil.flip(cc.xyw(1,9,9),colSpec,orientation));
  component=builder.addSeparator(Messages.getString("PluginTab.8"),FormLayoutUtil.flip(cc.xyw(1,11,9),colSpec,orientation));
  component=(JComponent)component.getComponent(0);
  component.setFont(component.getFont().deriveFont(Font.BOLD));
  DefaultTableCellRenderer cellRenderer=(DefaultTableCellRenderer)credTable.getCellRenderer(0,0);
  FontMetrics metrics=cellRenderer.getFontMetrics(cellRenderer.getFont());
  credTable.setRowHeight(metrics.getLeading() + metrics.getMaxAscent() + metrics.getMaxDescent() + 4);
  credTable.setFillsViewportHeight(true);
  credTable.setIntercellSpacing(new Dimension(8,2));
  TableColumn ownerColumn=credTable.getColumnModel().getColumn(0);
  ownerColumn.setMinWidth(70);
  TableColumn tagColumn=credTable.getColumnModel().getColumn(2);
  tagColumn.setPreferredWidth(45);
  TableColumn usrColumn=credTable.getColumnModel().getColumn(2);
  usrColumn.setPreferredWidth(45);
  TableColumn pwdColumn=credTable.getColumnModel().getColumn(3);
  pwdColumn.setPreferredWidth(45);
  JScrollPane pane=new JScrollPane(credTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  pane.setBorder(BorderFactory.createEmptyBorder());
  pane.setPreferredSize(new Dimension(200,95));
  builder.add(pane,FormLayoutUtil.flip(cc.xyw(1,13,9),colSpec,orientation));
  CustomJButton add=new CustomJButton(Messages.getString("PluginTab.9"));
  builder.add(add,FormLayoutUtil.flip(cc.xy(1,15),colSpec,orientation));
  add.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      addEditDialog(credTable,-1);
    }
  }
);
  CustomJButton edit=new CustomJButton(Messages.getString("PluginTab.11"));
  builder.add(edit,FormLayoutUtil.flip(cc.xy(3,15),colSpec,orientation));
  edit.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      addEditDialog(credTable,credTable.getSelectedRow());
    }
  }
);
  CustomJButton del=new CustomJButton(Messages.getString("PluginTab.12"));
  builder.add(del,FormLayoutUtil.flip(cc.xy(5,15),colSpec,orientation));
  del.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      int[] rows=credTable.getSelectedRows();
      if (rows.length > 0) {
        int n=JOptionPane.showConfirmDialog(looksFrame,Messages.getString("PluginTab.13"),"",JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
          for (int i=0; i < rows.length; i++) {
            String key=(String)credTable.getValueAt(rows[i],0);
            if (StringUtils.isNotEmpty((String)credTable.getValueAt(rows[i],1))) {
              key=key + "." + (String)credTable.getValueAt(rows[i],1);
            }
            cred.clearProperty(key);
          }
        }
        try {
          cred.save();
        }
 catch (        ConfigurationException e1) {
          LOGGER.warn("Couldn't save credentials file {}",e1.getMessage());
          LOGGER.trace("",e1);
        }
        refreshCred(credTable);
      }
    }
  }
);
  CustomJButton credEdit=new CustomJButton(Messages.getString("NetworkTab.54"));
  credEdit.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      JPanel tPanel=new JPanel(new BorderLayout());
      final JTextArea textArea=new JTextArea();
      textArea.setFont(new Font("Courier",Font.PLAIN,12));
      JScrollPane scrollPane=new JScrollPane(textArea);
      scrollPane.setPreferredSize(new Dimension(900,450));
      try {
        configuration.initCred();
      }
 catch (      IOException e2) {
        LOGGER.error("Could not create credentials file: {}",e2.getMessage());
        LOGGER.trace("",e2);
        return;
      }
      try {
        cred.save();
      }
 catch (      ConfigurationException e3) {
        LOGGER.error("Could not save credentials file: {}",e3.getMessage());
        LOGGER.trace("",e3);
      }
      File f=configuration.getCredFile();
      try {
        try (BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(f),StandardCharsets.UTF_8))){
          String line;
          StringBuilder sb=new StringBuilder();
          while ((line=in.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
          }
          textArea.setText(sb.toString());
        }
       }
 catch (      IOException e1) {
        LOGGER.error("Could not read credentials file: {}",e1.getMessage());
        LOGGER.trace("",e1);
        return;
      }
      tPanel.add(scrollPane,BorderLayout.NORTH);
      Object[] options={Messages.getString("LooksFrame.9"),Messages.getString("NetworkTab.45")};
      if (JOptionPane.showOptionDialog(looksFrame,tPanel,Messages.getString("NetworkTab.54"),JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,null) == JOptionPane.OK_OPTION) {
        String text=textArea.getText();
        try {
          try (FileOutputStream fos=new FileOutputStream(f)){
            fos.write(text.getBytes(StandardCharsets.UTF_8));
            fos.flush();
          }
           PMS.getConfiguration().reload();
          try {
            cred.refresh();
          }
 catch (          ConfigurationException e2) {
            LOGGER.error("An error occurred while updating credentials: {}",e2);
            LOGGER.trace("",e2);
          }
          refreshCred(credTable);
        }
 catch (        Exception e1) {
          JOptionPane.showMessageDialog(looksFrame,Messages.getString("NetworkTab.55") + ": " + e1.getMessage());
        }
      }
    }
  }
);
  builder.add(credEdit,FormLayoutUtil.flip(cc.xy(7,15),colSpec,orientation));
  JPanel panel=builder.getPanel();
  JScrollPane scrollPane=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  scrollPane.setBorder(BorderFactory.createEmptyBorder());
  return scrollPane;
}
