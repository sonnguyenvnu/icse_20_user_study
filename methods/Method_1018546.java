static CborObject deserialize(CborDecoder decoder,int maxGroupSize){
  try {
    CborType type=decoder.peekType();
switch (type.getMajorType()) {
case TYPE_TEXT_STRING:
      return new CborString(decoder.readTextString(maxGroupSize));
case CborConstants.TYPE_BYTE_STRING:
    return new CborByteArray(decoder.readByteString(maxGroupSize));
case CborConstants.TYPE_UNSIGNED_INTEGER:
  return new CborLong(decoder.readInt());
case CborConstants.TYPE_NEGATIVE_INTEGER:
return new CborLong(decoder.readInt());
case CborConstants.TYPE_FLOAT_SIMPLE:
if (type.getAdditionalInfo() == CborConstants.NULL) {
decoder.readNull();
return new CborNull();
}
if (type.getAdditionalInfo() == CborConstants.TRUE) {
decoder.readBoolean();
return new CborBoolean(true);
}
if (type.getAdditionalInfo() == CborConstants.FALSE) {
decoder.readBoolean();
return new CborBoolean(false);
}
throw new IllegalStateException("Unimplemented simple type! " + type.getAdditionalInfo());
case CborConstants.TYPE_MAP:
{
long nValues=decoder.readMapLength();
if (nValues > maxGroupSize) throw new IllegalStateException("Invalid cbor: more map elements than original bytes!");
SortedMap<CborObject,Cborable> result=new TreeMap<>();
for (long i=0; i < nValues; i++) {
CborObject key=deserialize(decoder,maxGroupSize);
CborObject value=deserialize(decoder,maxGroupSize);
result.put(key,value);
}
return new CborMap(result);
}
case CborConstants.TYPE_ARRAY:
long nItems=decoder.readArrayLength();
if (nItems > maxGroupSize) throw new IllegalStateException("Invalid cbor: more array elements than original bytes!");
List<CborObject> res=new ArrayList<>((int)nItems);
for (long i=0; i < nItems; i++) res.add(deserialize(decoder,maxGroupSize));
return new CborList(res);
case CborConstants.TYPE_TAG:
long tag=decoder.readTag();
if (tag == LINK_TAG) {
CborObject value=deserialize(decoder,maxGroupSize);
if (value instanceof CborString) return new CborMerkleLink(Cid.decode(((CborString)value).value));
if (value instanceof CborByteArray) {
byte[] bytes=((CborByteArray)value).value;
if (bytes[0] == 0) return new CborMerkleLink(Cid.cast(Arrays.copyOfRange(bytes,1,bytes.length)));
throw new IllegalStateException("Unknown Multibase decoding Merkle link: " + bytes[0]);
}
throw new IllegalStateException("Invalid type for merkle link: " + value);
}
throw new IllegalStateException("Unknown TAG in CBOR: " + type.getAdditionalInfo());
default :
throw new IllegalStateException("Unimplemented cbor type: " + type);
}
}
 catch (IOException e) {
throw new RuntimeException(e);
}
}
