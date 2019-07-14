/** 
 * Sets an output to receive metadata events, removing all existing outputs.
 * @param output The output.
 * @deprecated Use {@link #addMetadataOutput(MetadataOutput)}.
 */
@Deprecated public void setMetadataOutput(MetadataOutput output){
  metadataOutputs.retainAll(Collections.singleton(analyticsCollector));
  if (output != null) {
    addMetadataOutput(output);
  }
}
