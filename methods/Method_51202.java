/** 
 * Gets an options bundle from options.
 * @param option  Mandatory first argument
 * @param options Rest of the options
 * @return An options bundle
 */
public static MetricOptions ofOptions(MetricOption option,MetricOption... options){
  MetricOptionsBuilder builder=new MetricOptionsBuilder();
  builder.add(option);
  for (  MetricOption opt : options) {
    builder.add(opt);
  }
  return builder.build();
}
