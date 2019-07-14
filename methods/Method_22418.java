public void populateSketchbookMenu(JMenu menu){
  boolean found=false;
  try {
    found=addSketches(menu,sketchbookFolder,false);
  }
 catch (  IOException e) {
    Messages.showWarning("Sketchbook Menu Error","An error occurred while trying to list the sketchbook.",e);
  }
  if (!found) {
    JMenuItem empty=new JMenuItem(Language.text("menu.file.sketchbook.empty"));
    empty.setEnabled(false);
    menu.add(empty);
  }
}
