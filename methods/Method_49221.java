/** 
 * Configures the composite sort key for this label. <p> Specifying the sort key of a type allows relations of this type to be efficiently retrieved in the order of the sort key. <br> For instance, if the edge label <i>friend</i> has the sort key (<i>since</i>), which is a property key with a timestamp data type, then one can efficiently retrieve all edges with label <i>friend</i> in a specified time interval using  {@link org.janusgraph.core.JanusGraphVertexQuery#interval}. <br> In other words, relations are stored on disk in the order of the configured sort key. The sort key is empty by default. <br> If multiple types are specified as sort key, then those are considered as a <i>composite</i> sort key, i.e. taken jointly in the given order. <p> {@link org.janusgraph.core.RelationType}s used in the sort key must be either property out-unique keys or out-unique unidirected edge lables.
 * @param keys JanusGraphTypes composing the sort key. The order is relevant.
 * @return this LabelMaker
 */
public StandardRelationTypeMaker sortKey(PropertyKey... keys){
  Preconditions.checkArgument(keys != null && keys.length > 0);
  sortKey.addAll(Arrays.asList(keys));
  return this;
}
