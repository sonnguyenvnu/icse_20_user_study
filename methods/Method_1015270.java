/** 
 * utf8URL?????
 */
private static String codetoword(String text){
  String result;
  if (utf8codecheck(text)) {
    byte[] code=new byte[3];
    code[0]=(byte)(Integer.parseInt(text.substring(1,3),16) - 256);
    code[1]=(byte)(Integer.parseInt(text.substring(4,6),16) - 256);
    code[2]=(byte)(Integer.parseInt(text.substring(7,9),16) - 256);
    result=new String(code,StandardCharsets.UTF_8);
  }
 else {
    result=text;
  }
  return result;
}
