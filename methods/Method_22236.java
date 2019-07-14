@NonNull private String getStackTrace(@Nullable String msg,@Nullable Throwable th){
  final Writer result=new StringWriter();
  try (final PrintWriter printWriter=new PrintWriter(result)){
    if (msg != null && !TextUtils.isEmpty(msg)) {
      printWriter.println(msg);
    }
    if (th != null) {
      th.printStackTrace(printWriter);
    }
    return result.toString();
  }
 }
