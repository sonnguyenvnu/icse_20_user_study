/** 
 * Sets the address of the specified  {@link AIExportDataBlob} to the {@code next} field. 
 */
public AIExportDataBlob next(@Nullable @NativeType("struct aiExportDataBlob *") AIExportDataBlob value){
  nnext(address(),value);
  return this;
}
