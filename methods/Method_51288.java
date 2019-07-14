/** 
 * Parses the given string as a database uri and returns a list of datasources.
 * @param uriString the URI to parse
 * @return list of data sources
 * @throws PMDException if the URI couldn't be parsed
 * @see DBURI
 */
public static List<DataSource> getURIDataSources(String uriString) throws PMDException {
  List<DataSource> dataSources=new ArrayList<>();
  try {
    DBURI dbUri=new DBURI(uriString);
    DBMSMetadata dbmsMetadata=new DBMSMetadata(dbUri);
    LOG.log(Level.FINE,"DBMSMetadata retrieved");
    List<SourceObject> sourceObjectList=dbmsMetadata.getSourceObjectList();
    LOG.log(Level.FINE,"Located {0} database source objects",sourceObjectList.size());
    for (    SourceObject sourceObject : sourceObjectList) {
      String falseFilePath=sourceObject.getPseudoFileName();
      LOG.log(Level.FINEST,"Adding database source object {0}",falseFilePath);
      try {
        dataSources.add(new ReaderDataSource(dbmsMetadata.getSourceCode(sourceObject),falseFilePath));
      }
 catch (      SQLException ex) {
        if (LOG.isLoggable(Level.WARNING)) {
          LOG.log(Level.WARNING,"Cannot get SourceCode for " + falseFilePath + "  - skipping ...",ex);
        }
      }
    }
  }
 catch (  URISyntaxException e) {
    throw new PMDException("Cannot get DataSources from DBURI - \"" + uriString + "\"",e);
  }
catch (  SQLException e) {
    throw new PMDException("Cannot get DataSources from DBURI, couldn't access the database - \"" + uriString + "\"",e);
  }
catch (  ClassNotFoundException e) {
    throw new PMDException("Cannot get DataSources from DBURI, probably missing database jdbc driver - \"" + uriString + "\"",e);
  }
catch (  Exception e) {
    throw new PMDException("Encountered unexpected problem with URI \"" + uriString + "\"",e);
  }
  return dataSources;
}
