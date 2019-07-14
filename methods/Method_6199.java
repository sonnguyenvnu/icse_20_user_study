String extractString(byte[] bytes,int offset,int length){
  try {
    String text=new String(bytes,offset,length,"ISO-8859-1");
    int zeroIndex=text.indexOf(0);
    return zeroIndex < 0 ? text : text.substring(0,zeroIndex);
  }
 catch (  Exception e) {
    return "";
  }
}
