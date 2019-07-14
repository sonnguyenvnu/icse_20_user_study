static protected void load() throws IOException {
  records=new ArrayList<>();
  if (file.exists()) {
    BufferedReader reader=PApplet.createReader(file);
    String version=reader.readLine();
    if (version != null && version.equals(VERSION)) {
      String line=null;
      while ((line=reader.readLine()) != null) {
        if (new File(line).exists()) {
          records.add(new Record(line));
        }
 else {
          Messages.log("ghost file: " + line);
        }
      }
    }
    reader.close();
  }
  updateMenu(mainMenu);
  updateMenu(toolbarMenu);
}
