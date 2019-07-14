static private void updateMenu(JMenu menu){
  menu.removeAll();
  String sketchbookPath=Base.getSketchbookFolder().getAbsolutePath();
  for (  Record rec : records) {
    updateMenuRecord(menu,rec,sketchbookPath);
  }
}
