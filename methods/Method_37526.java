/** 
 * Registers a  {@link ClassPathEntry class path entry} consumer.It will be called on each loaded entry.
 */
public ClassScanner registerEntryConsumer(final Consumer<ClassPathEntry> entryDataConsumer){
  entryDataConsumers.add(entryDataConsumer);
  return this;
}
