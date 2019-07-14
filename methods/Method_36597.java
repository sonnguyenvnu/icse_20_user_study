private static String getImplementationVersion(JarFile jarFile) throws IOException {
  return jarFile.getManifest().getMainAttributes().getValue(Attributes.Name.IMPLEMENTATION_VERSION);
}
