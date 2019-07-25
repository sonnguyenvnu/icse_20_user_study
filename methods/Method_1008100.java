/** 
 * Since custom metadata can be loaded by a plugin class loader that resides in a sub-node, we need to marshal this object into something the tribe node can work with
 */
private MetaData.Custom marshal(MetaData.Custom custom){
  try (BytesStreamOutput bytesStreamOutput=new BytesStreamOutput()){
    bytesStreamOutput.writeNamedWriteable(custom);
    try (StreamInput input=bytesStreamOutput.bytes().streamInput()){
      StreamInput namedInput=new NamedWriteableAwareStreamInput(input,namedWriteableRegistry);
      MetaData.Custom marshaled=namedInput.readNamedWriteable(MetaData.Custom.class);
      return marshaled;
    }
   }
 catch (  IOException ex) {
    throw new IllegalStateException("cannot marshal object with type " + custom.getWriteableName() + " to tribe node");
  }
}
