/** 
 * ??????
 * @param strFilePath ????
 * @param file_digest_type ????
 * @return ??????
 */
public static String getAbstract(String strFilePath,String file_digest_type) throws IOException {
  PartSource file=new FilePartSource(new File(strFilePath));
  if (file_digest_type.equals("MD5")) {
    return DigestUtils.md5Hex(file.createInputStream());
  }
 else   if (file_digest_type.equals("SHA")) {
    return DigestUtils.sha256Hex(file.createInputStream());
  }
 else {
    return "";
  }
}
