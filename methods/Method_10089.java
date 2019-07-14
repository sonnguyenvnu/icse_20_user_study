/** 
 * Counts revision specified by the given data id and data type.
 * @param dataId   the given data id
 * @param dataType the given data type
 * @return count result
 */
public int count(final String dataId,final int dataType){
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Revision.REVISION_DATA_ID,FilterOperator.EQUAL,dataId),new PropertyFilter(Revision.REVISION_DATA_TYPE,FilterOperator.EQUAL,dataType)));
  Stopwatchs.start("Revision count");
  try {
    return (int)revisionRepository.count(query);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Counts revisions failed",e);
    return 0;
  }
 finally {
    Stopwatchs.end();
  }
}
