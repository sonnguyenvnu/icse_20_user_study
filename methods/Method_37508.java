/** 
 * Determines whether the specified file is a symbolic link rather than an actual file.
 * @deprecated {@link java.nio.file.Files#isSymbolicLink(java.nio.file.Path)} provides this functionality natively as of Java 1.7.
 */
@Deprecated public static boolean isSymlink(final File file){
  return Files.isSymbolicLink(file.toPath());
}
