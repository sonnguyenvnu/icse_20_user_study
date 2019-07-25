/** 
 * Open a file and collect information about it (technical information and tags).
 * @param File_Name full name of the file to open
 * @return 1 if file was opened, 0 if file was not not opened
 */
public int Open(String File_Name){
  return MediaInfoDLL_Internal.INSTANCE.Open(Handle,new WString(File_Name));
}
