/** 
 * Check that a plaintext password matches a previously hashed one.
 * @param plaintext the plaintext password to verify
 * @param hashed the previously-hashed password
 * @return true if the passwords match, false otherwise
 */
public static boolean checkpw(String plaintext,String hashed){
  byte[] hashed_bytes;
  byte[] try_bytes;
  try {
    String try_pw=hashpw(plaintext,hashed);
    hashed_bytes=hashed.getBytes("UTF-8");
    try_bytes=try_pw.getBytes("UTF-8");
  }
 catch (  UnsupportedEncodingException uee) {
    return false;
  }
  if (hashed_bytes.length != try_bytes.length) {
    return false;
  }
  byte ret=0;
  for (int i=0; i < try_bytes.length; i++) {
    ret|=hashed_bytes[i] ^ try_bytes[i];
  }
  return ret == 0;
}
