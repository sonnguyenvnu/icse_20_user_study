/** 
 * ??????
 * @param strFilePath ????
 * @param file_digest_type ????
 * @return ??????
 */
public static String getAbstract(String strFilePath,String file_digest_type) throws IOException {
  PartSource file=new FilePartSource(new File(strFilePath));
  if ("MD5".equals(file_digest_type)) {
    return DigestUtils.md5Hex(file.createInputStream());
  }
 else   if ("SHA".equals(file_digest_type)) {
    return DigestUtils.sha256Hex(file.createInputStream());
  }
 else {
    return "";
  }
}
