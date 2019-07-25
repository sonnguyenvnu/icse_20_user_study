/** 
 * Scans the source ZIP file and executes the given callback for each entry. <p> Only the meta-data without the actual data is read. If you want to stop the loop then throw a ZipBreakException. This method is charset aware and uses Zips.charset.
 * @param callback callback to be called for each entry.
 * @see ZipInfoCallback
 * @see #iterate(ZipEntryCallback)
 */
public void iterate(ZipInfoCallback callback){
  ZipEntryOrInfoAdapter zipEntryAdapter=new ZipEntryOrInfoAdapter(null,callback);
  processAllEntries(zipEntryAdapter);
}
