/** 
 * resolve the spi classes
 * @param importingClassMetadata importingClassMetadata
 * @return spi classes
 */
@Override @NonNull public String[] selectImports(@NonNull AnnotationMetadata importingClassMetadata){
  boolean enabledTxc=Boolean.valueOf(Objects.requireNonNull(importingClassMetadata.getAnnotationAttributes(EnableDistributedTransaction.class.getName())).get("enableTxc").toString());
  List<String> importClasses=new ArrayList<>();
  importClasses.add("com.codingapi.txlcn.txmsg.MessageConfiguration");
  if (enabledTxc) {
    importClasses.add(TxcConfiguration.class.getName());
  }
  return importClasses.toArray(new String[0]);
}
