public void load(File additions){
  String[] lines=PApplet.loadStrings(additions);
  for (  String line : lines) {
    if ((line.length() == 0) || (line.charAt(0) == '#'))     continue;
    int equals=line.indexOf('=');
    if (equals != -1) {
      String key=line.substring(0,equals).trim();
      String value=line.substring(equals + 1).trim();
      table.put(key,value);
    }
  }
  String platformExt="." + Platform.getName();
  int platformExtLength=platformExt.length();
  for (  String key : table.keySet()) {
    if (key.endsWith(platformExt)) {
      String actualKey=key.substring(0,key.length() - platformExtLength);
      String value=get(key);
      table.put(actualKey,value);
    }
  }
}
