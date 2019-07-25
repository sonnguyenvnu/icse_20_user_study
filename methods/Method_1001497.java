public static NumberAnalyzed2 load(String name,Preferences prefs){
  final String value=prefs.get(name + ".saved","");
  if (value.length() == 0) {
    System.err.println("Cannot load " + name);
    return null;
  }
  try {
    final StringTokenizer st=new StringTokenizer(value,";");
    return new NumberAnalyzed2(name,Long.parseLong(st.nextToken(),36),Long.parseLong(st.nextToken(),36),Long.parseLong(st.nextToken(),36),Long.parseLong(st.nextToken(),36),Long.parseLong(st.nextToken(),36),Long.parseLong(st.nextToken(),36));
  }
 catch (  Exception e) {
    e.printStackTrace();
    Log.info("Error reading " + value);
    return null;
  }
}
