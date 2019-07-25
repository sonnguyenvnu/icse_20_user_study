/** 
 * ????log????
 * @param dirPath ?????
 */
public static void init(String dirPath){
  sLogEnable=true;
  File file=new File(dirPath);
  if (!file.exists() || !file.isDirectory()) {
    throw new InvalidParameterException();
  }
  sLogFileManager=new LogFileManager(dirPath);
}
