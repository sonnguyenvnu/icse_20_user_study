public void migrateTo(int newVersion){
  try {
    for (int v=db.getVersion() + 1; v <= newVersion; v++) {
      String fname=String.format(Locale.US,"/migrations/%02d.sql",v);
      for (      String command : SQLParser.parse(open(fname)))       db.execute(command);
    }
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
