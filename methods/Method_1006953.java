/** 
 * Register a  {@link ItemStream} as one of the interesting providers underthe provided key.
 * @param stream an instance of {@link ItemStream} to be added to the list of streams.
 */
public void register(ItemStream stream){
synchronized (streams) {
    if (!streams.contains(stream)) {
      streams.add(stream);
    }
  }
}
