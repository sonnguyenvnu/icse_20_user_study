/** 
 * Queries database to find entry for the specific path
 * @param path the path to match with
 * @return the entry
 */
private static EncryptedEntry findEncryptedEntry(Context context,String path) throws Exception {
  CryptHandler handler=new CryptHandler(context);
  EncryptedEntry matchedEntry=null;
  for (  EncryptedEntry encryptedEntry : handler.getAllEntries()) {
    if (path.contains(encryptedEntry.getPath())) {
      if (matchedEntry == null || matchedEntry.getPath().length() < encryptedEntry.getPath().length()) {
        matchedEntry=encryptedEntry;
      }
    }
  }
  return matchedEntry;
}
