/** 
 * serialize to file
 * @param filePath
 * @param obj
 * @return
 * @throws RuntimeException if an error occurs
 */
public static void serialization(String filePath,Object obj){
  ObjectOutputStream out=null;
  try {
    out=new ObjectOutputStream(new FileOutputStream(filePath));
    out.writeObject(obj);
    out.close();
  }
 catch (  FileNotFoundException e) {
    throw new RuntimeException("FileNotFoundException occurred. ",e);
  }
catch (  IOException e) {
    throw new RuntimeException("IOException occurred. ",e);
  }
 finally {
    if (out != null) {
      try {
        out.close();
      }
 catch (      IOException e) {
        throw new RuntimeException("IOException occurred. ",e);
      }
    }
  }
}
