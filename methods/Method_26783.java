@Override public void init(JavacTask javacTask,String... args){
  Iterator<String> itr=Arrays.asList(args).iterator();
  String path=null;
  while (itr.hasNext()) {
    if (itr.next().equals("--out")) {
      path=itr.next();
      break;
    }
  }
  checkArgument(path != null,"No --out specified");
  javacTask.addTaskListener(new RefasterRuleCompilerAnalyzer(((BasicJavacTask)javacTask).getContext(),FileSystems.getDefault().getPath(path)));
}
