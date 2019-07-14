@Override protected Void doInBackground(String... params){
  String path=params[0];
  HybridFile file=new HybridFile(mOpenMode,path);
  file.generateMode(activity.get());
  if (file.isSmb())   return null;
  if (!isRegexEnabled) {
    search(file,mInput);
  }
 else {
    Pattern pattern=Pattern.compile(bashRegexToJava(mInput));
    if (!isMatchesEnabled)     searchRegExFind(file,pattern);
 else     searchRegExMatch(file,pattern);
  }
  return null;
}
