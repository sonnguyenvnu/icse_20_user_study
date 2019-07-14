JMenuItem createToolItem(final Tool tool){
  String title=tool.getMenuTitle();
  final JMenuItem item=new JMenuItem(title);
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      try {
        tool.run();
      }
 catch (      NoSuchMethodError nsme) {
        activeEditor.statusError("\"" + tool.getMenuTitle() + "\" is not" + "compatible with this version of Processing");
        Messages.loge("Incompatible tool found during tool.run()",nsme);
        item.setEnabled(false);
      }
catch (      Exception ex) {
        activeEditor.statusError("An error occurred inside \"" + tool.getMenuTitle() + "\"");
        ex.printStackTrace();
        item.setEnabled(false);
      }
    }
  }
);
  return item;
}
