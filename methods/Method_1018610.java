static Transaction deserialize(byte[] data){
  CborObject cborObject=CborObject.fromByteArray(data);
  CborObject.CborMap map=(CborObject.CborMap)cborObject;
  Type type=Type.valueOf(map.getString("type"));
switch (type) {
case FILE_UPLOAD:
    return FileUploadTransaction.fromCbor(map);
default :
  throw new IllegalStateException("Unimplemented type " + type);
}
}
