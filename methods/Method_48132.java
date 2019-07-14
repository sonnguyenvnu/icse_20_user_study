/** 
 * Returns a  {@link Builder} that allows to set the configuration options for opening a JanusGraph graph database.<p> In the builder, the configuration options for the graph can be set individually. Once all options are configured, the graph can be opened with  {@link org.janusgraph.core.JanusGraphFactory.Builder#open()}.
 * @return
 */
public static Builder build(){
  return new Builder();
}
