private static void exception(@NotNull StringBuilder stringBuilder,int level,@NotNull ErrorBean errorBean){
  header(stringBuilder,level,"Exception");
  message(stringBuilder,level + 1,errorBean.getMessage());
  stacktrace(stringBuilder,level + 1,errorBean.getStackTrace());
}
