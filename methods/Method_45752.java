/** 
 * ?????? ??
 * @param file ??
 * @param data ??
 * @return ??????
 * @throws IOException ??IO??
 */
public static boolean string2File(File file,String data) throws IOException {
  if (!file.getParentFile().exists()) {
    file.getParentFile().mkdirs();
  }
  FileWriter writer=null;
  try {
    writer=new FileWriter(file,false);
    writer.write(data);
  }
  finally {
    if (writer != null) {
      writer.close();
    }
  }
  return true;
}
