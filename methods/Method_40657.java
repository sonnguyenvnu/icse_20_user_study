/** 
 * Each source file's AST is saved in an object file named for the MD5 checksum of the source file.  All that is needed is the MD5, but the file's base name is included for ease of debugging.
 */
@NotNull public String getCachePath(@NotNull String sourcePath){
  return getCachePath(_.getSHA(sourcePath),sourcePath);
}
