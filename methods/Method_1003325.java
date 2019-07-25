private static void upgrade(ConnectionInfo ci,Properties info) throws SQLException {
  String name=ci.getName();
  String data=name + Constants.SUFFIX_OLD_DATABASE_FILE;
  String index=name + ".index.db";
  String lobs=name + ".lobs.db";
  String backupData=data + ".backup";
  String backupIndex=index + ".backup";
  String backupLobs=lobs + ".backup";
  String script=null;
  try {
    if (scriptInTempDir) {
      new File(Utils.getProperty("java.io.tmpdir",".")).mkdirs();
      script=File.createTempFile("h2dbmigration","backup.sql").getAbsolutePath();
    }
 else {
      script=name + ".script.sql";
    }
    String oldUrl="jdbc:h2v1_1:" + name + ";UNDO_LOG=0;LOG=0;LOCK_MODE=0";
    String cipher=ci.getProperty("CIPHER",null);
    if (cipher != null) {
      oldUrl+=";CIPHER=" + cipher;
    }
    Connection conn=DriverManager.getConnection(oldUrl,info);
    Statement stat=conn.createStatement();
    String uuid=UUID.randomUUID().toString();
    if (cipher != null) {
      stat.execute("script to '" + script + "' cipher aes password '" + uuid + "' --hide--");
    }
 else {
      stat.execute("script to '" + script + "'");
    }
    conn.close();
    FileUtils.move(data,backupData);
    FileUtils.move(index,backupIndex);
    if (FileUtils.exists(lobs)) {
      FileUtils.move(lobs,backupLobs);
    }
    ci.removeProperty("IFEXISTS",false);
    conn=new JdbcConnection(ci,true);
    stat=conn.createStatement();
    if (cipher != null) {
      stat.execute("runscript from '" + script + "' cipher aes password '" + uuid + "' --hide--");
    }
 else {
      stat.execute("runscript from '" + script + "'");
    }
    stat.execute("analyze");
    stat.execute("shutdown compact");
    stat.close();
    conn.close();
    if (deleteOldDb) {
      FileUtils.delete(backupData);
      FileUtils.delete(backupIndex);
      FileUtils.deleteRecursive(backupLobs,false);
    }
  }
 catch (  Exception e) {
    if (FileUtils.exists(backupData)) {
      FileUtils.move(backupData,data);
    }
    if (FileUtils.exists(backupIndex)) {
      FileUtils.move(backupIndex,index);
    }
    if (FileUtils.exists(backupLobs)) {
      FileUtils.move(backupLobs,lobs);
    }
    FileUtils.delete(name + ".h2.db");
    throw DbException.toSQLException(e);
  }
 finally {
    if (script != null) {
      FileUtils.delete(script);
    }
  }
}
