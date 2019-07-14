public void loadSuggestionsMap(){
  File suggestionsListFile=new File(getFolder() + File.separator + suggestionsFileName);
  if (!suggestionsListFile.exists()) {
    Messages.loge("Suggestions file not found! " + suggestionsListFile.getAbsolutePath());
    return;
  }
  try {
    BufferedReader br=new BufferedReader(new FileReader(suggestionsListFile));
    while (true) {
      String line=br.readLine();
      if (line == null) {
        break;
      }
      line=line.trim();
      if (line.startsWith("#")) {
        continue;
      }
 else {
        if (line.contains("=")) {
          String key=line.split("=")[0];
          String val=line.split("=")[1];
          if (suggestionsMap.containsKey(key)) {
            suggestionsMap.get(key).add(val);
          }
 else {
            HashSet<String> set=new HashSet<>();
            set.add(val);
            suggestionsMap.put(key,set);
          }
        }
      }
    }
    br.close();
  }
 catch (  IOException e) {
    Messages.loge("IOException while reading suggestions file:" + suggestionsListFile.getAbsolutePath());
  }
}
