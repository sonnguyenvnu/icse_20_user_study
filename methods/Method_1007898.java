public static String peek(){
  int next=getDepth();
  if (next == 0) {
    return "";
  }
  int last=next - 1;
  String key=PREFIX + last;
  String val=MDC.get(key);
  return val;
}
