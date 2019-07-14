@Override public void writeTextFile(String name,String contents){
  writeTextFileAndTranslateExceptions(contents,writableFileFor(name));
}
