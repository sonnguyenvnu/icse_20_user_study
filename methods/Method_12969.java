/** 
 * ??assets????????,????,???????
 * @param c context
 * @param urlStr ??
 * @return ?????
 */
public static String assetFile2Str(Context c,String urlStr){
  InputStream in=null;
  try {
    in=c.getAssets().open(urlStr);
    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
    String line=null;
    StringBuilder sb=new StringBuilder();
    do {
      line=bufferedReader.readLine();
      if (line != null && !line.matches("^\\s*\\/\\/.*")) {
        sb.append(line);
      }
    }
 while (line != null);
    bufferedReader.close();
    in.close();
    return sb.toString();
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
 finally {
    if (in != null) {
      try {
        in.close();
      }
 catch (      IOException e) {
      }
    }
  }
  return null;
}
