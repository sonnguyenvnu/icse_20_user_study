/** 
 * Applies projection, works in-place, meaning that the forProjection may be mutated.
 */
private static DataMap project(DataMap forProjection,MaskTree projection){
  if (projection == null) {
    return forProjection;
  }
 else   if (projection.getDataMap().isEmpty()) {
    return EMPTY_DATAMAP;
  }
  try {
    new DataComplexProcessor(new Filter(),projection.getDataMap(),forProjection).run(false);
    return forProjection;
  }
 catch (  Exception e) {
    throw new RuntimeException("Error projecting fields",e);
  }
}
