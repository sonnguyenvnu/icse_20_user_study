/** 
 * deserialization from file
 * @param filePath
 * @return
 * @throws RuntimeException if an error occurs
 */
public static Object deserialization(String filePath){
  ObjectInputStream in=null;
  try {
    in=new ObjectInputStream(new FileInputStream(filePath));
    Object o=in.readObject();
    in.close();
    return o;
  }
 catch (  FileNotFoundException e) {
    throw new RuntimeException("FileNotFoundException occurred. ",e);
  }
catch (  ClassNotFoundException e) {
    throw new RuntimeException("ClassNotFoundException occurred. ",e);
  }
catch (  IOException e) {
    throw new RuntimeException("IOException occurred. ",e);
  }
 finally {
    if (in != null) {
      try {
        in.close();
      }
 catch (      IOException e) {
        throw new RuntimeException("IOException occurred. ",e);
      }
    }
  }
}
