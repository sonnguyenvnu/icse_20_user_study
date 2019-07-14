/** 
 * ?????????
 */
public static void delFile(){
  String needDelFiel=FILE_SUFFIX.format(getDateBefore());
  File file=new File(LOG_FILE_PATH,needDelFiel + LOG_FILE_NAME);
  if (file.exists()) {
    file.delete();
  }
}
