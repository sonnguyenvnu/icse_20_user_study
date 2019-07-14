/** 
 * Validates file name special reserved characters shall not be allowed in the file names on FAT filesystems
 * @param fileName the filename, not the full path!
 * @return boolean if the file name is valid or invalid
 */
public static boolean isFileNameValid(String fileName){
  return !(fileName.contains(ASTERISK) || fileName.contains(BACKWARD_SLASH) || fileName.contains(COLON) || fileName.contains(FOREWARD_SLASH) || fileName.contains(GREATER_THAN) || fileName.contains(LESS_THAN) || fileName.contains(QUESTION_MARK) || fileName.contains(QUOTE));
}
