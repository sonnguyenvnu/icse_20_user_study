@Override public void contribute(Path projectRoot) throws IOException {
  Resource root=this.resolver.getResource(this.rootResource);
  Resource[] resources=this.resolver.getResources(this.rootResource + "/**");
  for (  Resource resource : resources) {
    String filename=resource.getURI().toString().substring(root.getURI().toString().length() + 1);
    if (resource.isReadable()) {
      Path output=projectRoot.resolve(filename);
      Files.createDirectories(output.getParent());
      Files.createFile(output);
      FileCopyUtils.copy(resource.getInputStream(),Files.newOutputStream(output));
      output.toFile().setExecutable(this.executable.test(filename));
    }
  }
}
