/** 
 * Returns a  {@link AIExportDataBlob} view of the struct pointed to by the {@code next} field. 
 */
@Nullable @NativeType("struct aiExportDataBlob *") public AIExportDataBlob next(){
  return nnext(address());
}
