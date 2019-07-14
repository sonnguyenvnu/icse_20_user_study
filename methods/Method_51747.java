private void addIssue(Path file,int lineNo,String message){
  issues.add(String.format("%s:%2d: %s",pagesDirectory.relativize(file).toString(),lineNo,message));
}
