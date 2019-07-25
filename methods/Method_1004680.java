@Override public void contribute(Path projectRoot) throws IOException {
  Path pomFile=Files.createFile(projectRoot.resolve("pom.xml"));
  writeBuild(Files.newBufferedWriter(pomFile));
}
