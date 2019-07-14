static protected void save(){
  file.setWritable(true,false);
  PrintWriter writer=PApplet.createWriter(file);
  writer.println(VERSION);
  for (  Record record : records) {
    writer.println(record.path);
  }
  writer.flush();
  writer.close();
  updateMenu(mainMenu);
  updateMenu(toolbarMenu);
}
