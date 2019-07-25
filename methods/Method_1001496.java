public static NumberAnalyzed load(String name,Preferences prefs){
  final String value=prefs.get(name + ".saved","");
  if (value.length() == 0) {
    Log.info("Cannot load " + name);
    return null;
  }
  try {
    final StringTokenizer st=new StringTokenizer(value,";");
    return new NumberAnalyzed(name,Long.parseLong(st.nextToken(),36),Long.parseLong(st.nextToken(),36),Long.parseLong(st.nextToken(),36),Long.parseLong(st.nextToken(),36),Long.parseLong(st.nextToken(),36),Long.parseLong(st.nextToken(),36));
  }
 catch (  Exception e) {
    e.printStackTrace();
    Log.info("Error reading " + value);
    return null;
  }
}
