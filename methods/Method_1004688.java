@Override public void contribute(Path projectRoot) throws IOException {
  if (this.buildMetadataResolver.hasFacet(this.build,"web")) {
    Files.createDirectories(projectRoot.resolve("src/main/resources/templates"));
    Files.createDirectories(projectRoot.resolve("src/main/resources/static"));
  }
}
