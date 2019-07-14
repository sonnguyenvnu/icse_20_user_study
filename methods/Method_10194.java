/** 
 * Recognizes a single character from the specified image file path.
 * @param imagePath the specified image file path
 * @return the recognized character
 */
public static String recognizeCharacter(final String imagePath){
  Execs.exec("tesseract " + imagePath + " " + imagePath + " -l chi_sim -psm 10",1000 * 10);
  try {
    return StringUtils.trim(IOUtils.toString(new FileInputStream(imagePath + ".txt"),"UTF-8"));
  }
 catch (  final IOException e) {
    return "";
  }
}
