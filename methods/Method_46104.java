/** 
 * Check file's digest.
 * @param address    the address
 * @param lastDigest the update digest
 * @return true????false????
 */
public static boolean checkModified(String address,String lastDigest){
  String newDigest=calMD5Checksum(address);
  return !StringUtils.equals(newDigest,lastDigest);
}
