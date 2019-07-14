/** 
 * Parse a single file from a Reader. The default implementation just throws a NotImplementedException. Override in subclasses to implement.
 */
public void parseOneFile(Project project,ProjectMetadata metadata,ImportingJob job,String fileSource,Reader reader,ImportColumnGroup rootColumnGroup,int limit,ObjectNode options,List<Exception> exceptions){
  throw new NotImplementedException();
}
