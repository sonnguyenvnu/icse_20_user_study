public static void info(@NonNull String message){
  info(RxTool.getContext(),message,Toast.LENGTH_SHORT,true).show();
}
