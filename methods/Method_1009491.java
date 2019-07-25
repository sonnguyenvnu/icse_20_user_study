/** 
 * Compresses the given entries into an output stream.
 * @param entries ZIP entries added.
 * @param os output stream for the new ZIP (does not have to be buffered)
 * @since 1.9
 */
public static void pack(ZipEntrySource[] entries,OutputStream os){
  if (log.isDebugEnabled()) {
    log.debug("Creating stream from {}.",Arrays.asList(entries));
  }
  pack(entries,os,false);
}
