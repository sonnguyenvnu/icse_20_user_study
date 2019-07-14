@JsonIgnore public byte[] getByteBodyIfBinary(){
  return body.isBinary() ? body.asBytes() : null;
}
