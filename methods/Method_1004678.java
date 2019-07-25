@Override public void contribute(Path projectRoot) throws IOException {
  Path settingsGradle=Files.createFile(projectRoot.resolve(this.settingsFileName));
  try (IndentingWriter writer=this.indentingWriterFactory.createIndentingWriter("gradle",Files.newBufferedWriter(settingsGradle))){
    this.settingsWriter.writeTo(writer,this.build);
  }
 }
