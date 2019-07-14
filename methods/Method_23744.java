public boolean save(File file,String options) throws IOException {
  return save(PApplet.createOutput(file),Table.extensionOptions(false,file.getName(),options));
}
