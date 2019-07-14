/** 
 * Registers file consumer
 */
public FindFile onFile(final Consumer<File> fileConsumer){
  if (consumers == null) {
    consumers=Consumers.of(fileConsumer);
  }
 else {
    consumers.add(fileConsumer);
  }
  return this;
}
