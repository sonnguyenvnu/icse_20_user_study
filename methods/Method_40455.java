private String sourceString(int beg,int end){
  int a=Math.max(beg,0);
  int b=Math.min(end,source.length());
  b=Math.max(b,0);
  try {
    return source.substring(a,b);
  }
 catch (  StringIndexOutOfBoundsException sx) {
    System.out.println("whoops: beg=" + a + ", end=" + b + ", len=" + source.length());
    return "";
  }
}
