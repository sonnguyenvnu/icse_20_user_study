public static void tryCloseClassLoader(ClassLoader classLoader){
  if (classLoader instanceof Closeable) {
    IOUtils.closeQuietly((Closeable)classLoader);
  }
}
