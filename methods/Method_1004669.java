@Override public void contribute(Path projectRoot) throws IOException {
  Path output=projectRoot.resolve(this.filename);
  if (!Files.exists(output)) {
    Files.createDirectories(output.getParent());
    Files.createFile(output);
  }
  Resource resource=this.resolver.getResource(this.resourcePattern);
  FileCopyUtils.copy(resource.getInputStream(),Files.newOutputStream(output,StandardOpenOption.APPEND));
}
