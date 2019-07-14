/** 
 * Return all source code objects associated with the specified languages, schemas, source code types and source code names. <p> Each parameter may be null and the appropriate field from any related DBURI is assigned, defaulting to the normal SQL wildcard expression ("%"). </p>
 * @param languages Optional list of languages to search for
 * @param schemas Optional list of schemas to search for
 * @param sourceCodeTypes Optional list of source code types to search for
 * @param sourceCodeNames Optional list of source code names to search for
 */
public List<SourceObject> getSourceObjectList(List<String> languages,List<String> schemas,List<String> sourceCodeTypes,List<String> sourceCodeNames){
  List<SourceObject> sourceObjectsList=new ArrayList<>();
  List<String> searchLanguages=languages;
  List<String> searchSchemas=schemas;
  List<String> searchSourceCodeTypes=sourceCodeTypes;
  List<String> searchSourceCodeNames=sourceCodeNames;
  List<String> wildcardList=Arrays.asList("%");
  if (null == searchLanguages) {
    List<String> dbURIList=(null == dburi) ? null : dburi.getLanguagesList();
    if (null == dbURIList || dbURIList.isEmpty()) {
      searchLanguages=wildcardList;
    }
 else {
      searchLanguages=dbURIList;
    }
  }
  if (null == searchSchemas) {
    List<String> dbURIList=(null == dburi) ? null : dburi.getSchemasList();
    if (null == dbURIList || dbURIList.isEmpty()) {
      searchSchemas=wildcardList;
    }
 else {
      searchSchemas=dbURIList;
    }
  }
  if (null == searchSourceCodeTypes) {
    List<String> dbURIList=(null == dburi) ? null : dburi.getSourceCodeTypesList();
    if (null == dbURIList || dbURIList.isEmpty()) {
      searchSourceCodeTypes=wildcardList;
    }
 else {
      searchSourceCodeTypes=dbURIList;
    }
  }
  if (null == searchSourceCodeNames) {
    List<String> dbURIList=(null == dburi) ? null : dburi.getSourceCodeNamesList();
    if (null == dbURIList || dbURIList.isEmpty()) {
      searchSourceCodeNames=wildcardList;
    }
 else {
      searchSourceCodeNames=dbURIList;
    }
  }
  try {
    if (null != returnSourceCodeObjectsStatement) {
      LOGGER.log(Level.FINE,"Have bespoke returnSourceCodeObjectsStatement from DBURI: \"{0}\"",returnSourceCodeObjectsStatement);
      try (PreparedStatement sourceCodeObjectsStatement=getConnection().prepareStatement(returnSourceCodeObjectsStatement)){
        for (        String language : searchLanguages) {
          for (          String schema : searchSchemas) {
            for (            String sourceCodeType : searchSourceCodeTypes) {
              for (              String sourceCodeName : searchSourceCodeNames) {
                sourceObjectsList.addAll(findSourceObjects(sourceCodeObjectsStatement,language,schema,sourceCodeType,sourceCodeName));
              }
            }
          }
        }
      }
     }
 else {
      LOGGER.fine("Have dbUri - no returnSourceCodeObjectsStatement, reverting to DatabaseMetaData.getProcedures(...)");
      DatabaseMetaData metadata=connection.getMetaData();
      List<String> schemasList=dburi.getSchemasList();
      for (      String schema : schemasList) {
        for (        String sourceCodeName : dburi.getSourceCodeNamesList()) {
          sourceObjectsList.addAll(findSourceObjectFromMetaData(metadata,schema,sourceCodeName));
        }
      }
    }
    LOGGER.finer(String.format("Identfied=%d sourceObjects",sourceObjectsList.size()));
    return sourceObjectsList;
  }
 catch (  SQLException sqle) {
    throw new RuntimeException("Problem collecting list of source code objects",sqle);
  }
}
