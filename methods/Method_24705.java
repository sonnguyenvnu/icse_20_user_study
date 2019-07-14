/** 
 * Displays a JDialog prompting the user to save when the user hits run/present/etc.
 */
protected void autoSave(){
  if (!JavaMode.autoSaveEnabled) {
    return;
  }
  try {
    if (sketch.isModified() && !sketch.isUntitled()) {
      if (JavaMode.autoSavePromptEnabled) {
        final JDialog autoSaveDialog=new JDialog(base.getActiveEditor(),getSketch().getName(),true);
        Container container=autoSaveDialog.getContentPane();
        JPanel panelMain=new JPanel();
        panelMain.setBorder(BorderFactory.createEmptyBorder(4,0,2,2));
        panelMain.setLayout(new BoxLayout(panelMain,BoxLayout.PAGE_AXIS));
        JPanel panelLabel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label=new JLabel("<html><body>&nbsp;There are unsaved" + " changes in your sketch.<br />" + "&nbsp;&nbsp;&nbsp; Do you want to save it before" + " running? </body></html>");
        label.setFont(new Font(label.getFont().getName(),Font.PLAIN,label.getFont().getSize() + 1));
        panelLabel.add(label);
        panelMain.add(panelLabel);
        final JCheckBox dontRedisplay=new JCheckBox("Remember this decision");
        JPanel panelButtons=new JPanel(new FlowLayout(FlowLayout.CENTER,8,2));
        JButton btnRunSave=new JButton("Save and Run");
        btnRunSave.addActionListener(new ActionListener(){
          @Override public void actionPerformed(          ActionEvent e){
            handleSave(true);
            if (dontRedisplay.isSelected()) {
              JavaMode.autoSavePromptEnabled=!dontRedisplay.isSelected();
              JavaMode.defaultAutoSaveEnabled=true;
              jmode.savePreferences();
            }
            autoSaveDialog.dispose();
          }
        }
);
        panelButtons.add(btnRunSave);
        JButton btnRunNoSave=new JButton("Run, Don't Save");
        btnRunNoSave.addActionListener(new ActionListener(){
          @Override public void actionPerformed(          ActionEvent e){
            if (dontRedisplay.isSelected()) {
              JavaMode.autoSavePromptEnabled=!dontRedisplay.isSelected();
              JavaMode.defaultAutoSaveEnabled=false;
              jmode.savePreferences();
            }
            autoSaveDialog.dispose();
          }
        }
);
        panelButtons.add(btnRunNoSave);
        panelMain.add(panelButtons);
        JPanel panelCheck=new JPanel();
        panelCheck.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        panelCheck.add(dontRedisplay);
        panelMain.add(panelCheck);
        container.add(panelMain);
        autoSaveDialog.setResizable(false);
        autoSaveDialog.pack();
        autoSaveDialog.setLocationRelativeTo(base.getActiveEditor());
        autoSaveDialog.setVisible(true);
      }
 else       if (JavaMode.defaultAutoSaveEnabled) {
        handleSave(true);
      }
    }
  }
 catch (  Exception e) {
    statusError(e);
  }
}
