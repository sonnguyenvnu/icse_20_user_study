@Override protected Map.Entry<String,String> onGenerateEntry(String line){
  String[] paramArray=line.split(separator,2);
  if (paramArray.length != 2) {
    logger.warning("?????????? " + line);
    return null;
  }
  return new AbstractMap.SimpleEntry<String,String>(paramArray[0],paramArray[1]);
}
