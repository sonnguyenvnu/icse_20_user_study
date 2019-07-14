/** 
 * Attempts to move to the Trash on OS X, or the Recycle Bin on Windows. Also tries to find a suitable Trash location on Linux. If not possible, just deletes the file or folder instead.
 * @param file the folder or file to be removed/deleted
 * @return true if the folder was successfully removed
 * @throws IOException
 */
static public boolean deleteFile(File file) throws IOException {
  FileUtils fu=FileUtils.getInstance();
  if (fu.hasTrash()) {
    fu.moveToTrash(new File[]{file});
    return true;
  }
 else   if (file.isDirectory()) {
    Util.removeDir(file);
    return true;
  }
 else {
    return file.delete();
  }
}
