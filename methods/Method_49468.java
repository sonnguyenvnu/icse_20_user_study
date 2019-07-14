/** 
 * Creates the properties for vertices, edges, and meta-properties.
 */
protected void createProperties(final JanusGraphManagement management){
  management.makePropertyKey("name").dataType(String.class).make();
  management.makePropertyKey("age").dataType(Integer.class).make();
  management.makePropertyKey("time").dataType(Integer.class).make();
  management.makePropertyKey("reason").dataType(String.class).make();
  management.makePropertyKey("place").dataType(Geoshape.class).make();
}
