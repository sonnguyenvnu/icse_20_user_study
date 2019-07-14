/** 
 * Hex String
 * @param content origin string
 * @return hex
 */
private String hex(String content){
  return DigestUtils.md5DigestAsHex(content.getBytes(StandardCharsets.UTF_8));
}
