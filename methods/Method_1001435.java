/** 
 * Convenient method to create a <tt>DataPlot</tt> based on the specified config parameters. It is a short-cut of  <tt>new DataPlot(config.getNode("data"))</tt>.
 */
public static DataPlot create(ConfigParameters config){
  return new DataPlot(config.getNode(DATA_KEY));
}
