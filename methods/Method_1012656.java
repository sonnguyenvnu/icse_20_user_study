public synchronized void cleanup(){
  Connection conn=null;
  PreparedStatement ps=null;
  ResultSet rs=null;
  try {
    conn=getConnection();
    ps=conn.prepareStatement("SELECT COUNT(*) FROM FILES");
    rs=ps.executeQuery();
    dbCount=0;
    if (rs.next()) {
      dbCount=rs.getInt(1);
    }
    rs.close();
    ps.close();
    PMS.get().getFrame().setStatusLine(Messages.getString("DLNAMediaDatabase.2") + " 0%");
    int i=0;
    int oldpercent=0;
    if (dbCount > 0) {
      ps=conn.prepareStatement("SELECT FILENAME, MODIFIED, ID FROM FILES",ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
      rs=ps.executeQuery();
      List<Path> sharedFolders=configuration.getSharedFolders();
      boolean isFileStillShared=false;
      while (rs.next()) {
        String filename=rs.getString("FILENAME");
        long modified=rs.getTimestamp("MODIFIED").getTime();
        File file=new File(filename);
        if (!file.exists() || file.lastModified() != modified) {
          LOGGER.trace("Removing the file {} from our database because it is no longer on the hard drive",filename);
          rs.deleteRow();
        }
 else {
          for (          Path folder : sharedFolders) {
            if (filename.contains(folder.toString())) {
              isFileStillShared=true;
              break;
            }
          }
          if (!isFileStillShared) {
            LOGGER.trace("Removing the file {} from our database because it is no longer shared",filename);
            rs.deleteRow();
          }
        }
        i++;
        int newpercent=i * 100 / dbCount;
        if (newpercent > oldpercent) {
          PMS.get().getFrame().setStatusLine(Messages.getString("DLNAMediaDatabase.2") + newpercent + "%");
          oldpercent=newpercent;
        }
      }
      PMS.get().getFrame().setStatusLine(null);
    }
    ps=conn.prepareStatement("DELETE FROM THUMBNAILS " + "WHERE NOT EXISTS (" + "SELECT ID FROM FILES " + "WHERE FILES.THUMBID = THUMBNAILS.ID" + ");");
    ps.execute();
    ps=conn.prepareStatement("DELETE FROM FILES_STATUS " + "WHERE NOT EXISTS (" + "SELECT ID FROM FILES " + "WHERE FILES.FILENAME = FILES_STATUS.FILENAME" + ");");
    ps.execute();
  }
 catch (  SQLException se) {
    LOGGER.error(null,se);
  }
 finally {
    close(rs);
    close(ps);
    close(conn);
    PMS.get().getFrame().setStatusLine(null);
  }
}
