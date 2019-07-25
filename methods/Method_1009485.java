/** 
 * Reads the source ZIP file and executes the given callback for each entry. <p> For each entry the corresponding input stream is also passed to the callback. If you want to stop the loop then throw a ZipBreakException. This method is charset aware and uses Zips.charset.
 * @param zipEntryCallback callback to be called for each entry.
 * @see ZipEntryCallback
 */
public void iterate(ZipEntryCallback zipEntryCallback){
  ZipEntryOrInfoAdapter zipEntryAdapter=new ZipEntryOrInfoAdapter(zipEntryCallback,null);
  processAllEntries(zipEntryAdapter);
}
