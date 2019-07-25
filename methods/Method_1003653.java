public static Context current(){
  return Execution.current().get(Context.TYPE);
}
