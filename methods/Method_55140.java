/** 
 * Creates a  {@code CFURL} object using a local file system path string.
 * @param allocator   the allocator to use to allocate memory for the new object. Pass {@code NULL} or {@code kCFAllocatorDefault} to use the current default allocator.
 * @param filePath    the path string to convert to a {@code CFURL} object. If {@code filePath} is not absolute, the resulting URL will be considered relative to thecurrent working directory (evaluated when this function is being invoked).
 * @param pathStyle   the operating system path style used in {@code filePath}. One of:<br><table><tr><td> {@link #kCFURLPOSIXPathStyle}</td><td> {@link #kCFURLHFSPathStyle}</td><td> {@link #kCFURLWindowsPathStyle}</td></tr></table>
 * @param isDirectory a Boolean value that specifies whether filePath is treated as a directory path when resolving against relative path components. Pass true if thepathname indicates a directory, false otherwise.
 */
@NativeType("CFURLRef") public static long CFURLCreateWithFileSystemPath(@NativeType("CFAllocatorRef") long allocator,@NativeType("CFStringRef") long filePath,@NativeType("CFURLPathStyle") long pathStyle,@NativeType("Boolean") boolean isDirectory){
  if (CHECKS) {
    check(filePath);
  }
  return nCFURLCreateWithFileSystemPath(allocator,filePath,pathStyle,isDirectory);
}
