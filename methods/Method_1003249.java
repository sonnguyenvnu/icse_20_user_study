/** 
 * Split the file name into algorithm, password, and base file name.
 * @param fileName the file name
 * @return an array with algorithm, password, and base file name
 */
private String[] parse(String fileName){
  if (!fileName.startsWith(getScheme())) {
    throw new IllegalArgumentException(fileName + " doesn't start with " + getScheme());
  }
  fileName=fileName.substring(getScheme().length() + 1);
  int idx=fileName.indexOf(':');
  String password;
  if (idx < 0) {
    throw new IllegalArgumentException(fileName + " doesn't contain encryption algorithm and password");
  }
  password=fileName.substring(0,idx);
  fileName=fileName.substring(idx + 1);
  return new String[]{password,fileName};
}
