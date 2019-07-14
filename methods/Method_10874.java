/** 
 * ????????????????????????????????????? ??????????????
 */
public static final boolean ping(){
  String result=null;
  try {
    String ip="www.baidu.com";
    Process p=Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);
    InputStream input=p.getInputStream();
    BufferedReader in=new BufferedReader(new InputStreamReader(input));
    StringBuffer stringBuffer=new StringBuffer();
    String content="";
    while ((content=in.readLine()) != null) {
      stringBuffer.append(content);
    }
    int status=p.waitFor();
    if (status == 0) {
      result="success";
      return true;
    }
 else {
      result="failed";
    }
  }
 catch (  IOException e) {
    result="IOException";
  }
catch (  InterruptedException e) {
    result="InterruptedException";
  }
 finally {
  }
  return false;
}
