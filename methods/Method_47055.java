/** 
 * Helper method to check file existence in otg
 */
public boolean exists(Context context){
  if (isOtgFile()) {
    DocumentFile fileToCheck=OTGUtil.getDocumentFile(path,context,false);
    return fileToCheck != null;
  }
 else   return (exists());
}
