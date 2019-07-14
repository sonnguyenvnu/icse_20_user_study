@Override public boolean load(API api,File file){
  String text=TextReader.getText(file);
  Pattern pattern=Pattern.compile("(?s)(.*\\s)?package\\s+(\\S+)\\s*;.*");
  Matcher matcher=pattern.matcher(text);
  if (matcher.matches()) {
    String pathInFile=matcher.group(2).replace(".",File.separator) + File.separator + file.getName();
    return load(api,file,pathInFile);
  }
 else {
    return load(api,file,file.getName());
  }
}
