/** 
 * Use recursive <code>ls</code> to get folder size. It is slow, it is stupid, and may be inaccurate (because of permission problems). Only for fallback use when <code>du</code> is not available.
 * @see HybridFile#folderSize(Context)
 * @return Folder size in bytes
 */
public static Long folderSizeSftp(SFTPClient client,String remotePath){
  Long retval=0L;
  try {
    for (    RemoteResourceInfo info : client.ls(remotePath)) {
      if (info.isDirectory())       retval+=folderSizeSftp(client,info.getPath());
 else       retval+=info.getAttributes().getSize();
    }
  }
 catch (  SFTPException e) {
    Log.e("folderSizeSftp","Problem accessing " + remotePath,e);
  }
 finally {
    return retval;
  }
}
