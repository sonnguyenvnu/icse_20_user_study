/** 
 * ????m3u8??????m3u8
 * @param context  ??
 * @param file     ???m3u8
 * @param pathList ???ts??
 * @return
 */
public static String getNativeM3u(final Context context,File file,List<File> pathList){
  InputStream in=null;
  int num=0;
  StringBuffer buf=new StringBuffer();
  try {
    if (file != null) {
      in=new FileInputStream(file);
    }
    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
    String line="";
    while ((line=reader.readLine()) != null) {
      if (line.length() > 0 && line.startsWith("http://")) {
        buf.append("file:" + pathList.get(num).getAbsolutePath() + "\r\n");
        num++;
      }
 else {
        buf.append(line + "\r\n");
      }
    }
    in.close();
    write(file.getAbsolutePath(),buf.toString());
    Log.d("ts??","ts????");
  }
 catch (  FileNotFoundException e1) {
    e1.printStackTrace();
  }
catch (  IOException e1) {
    e1.printStackTrace();
  }
  return buf.toString();
}
