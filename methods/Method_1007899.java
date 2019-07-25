public static void push(String message){
  int next=getDepth();
  MDC.put(PREFIX + next,message);
}
