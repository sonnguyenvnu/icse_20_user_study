@Override public void contribute(Path projectRoot) throws IOException {
  Path file=Files.createFile(projectRoot.resolve("settings.gradle"));
  try (PrintWriter writer=new PrintWriter(Files.newOutputStream(file))){
    writer.println("rootProject.name = '" + this.build.getArtifact() + "'");
  }
 }
