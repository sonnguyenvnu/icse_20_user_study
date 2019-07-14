private void addSaveOptionsTo(JMenu menu){
  JMenuItem saveItem;
  for (int i=0; i < RENDERER_SETS.length; i++) {
    saveItem=new JMenuItem("Save as " + RENDERER_SETS[i][0]);
    saveItem.addActionListener(new SaveListener((CPDRenderer)RENDERER_SETS[i][1]));
    menu.add(saveItem);
  }
}
