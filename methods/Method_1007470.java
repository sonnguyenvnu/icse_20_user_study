@Override FileLister.Entry open(String relativeFileName) throws IOException {
  JarEntry e=jarFile.getJarEntry(relativeFileName);
  return e == null ? null : new JEntry(e);
}
