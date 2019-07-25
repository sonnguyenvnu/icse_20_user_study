public short[] transform(IndexMap indexMap,short[] encodedInstructions) throws DexException {
  DecodedInstruction[] decodedInstructions=DecodedInstruction.decodeAll(encodedInstructions);
  int size=decodedInstructions.length;
  this.indexMap=indexMap;
  mappedInstructions=new DecodedInstruction[size];
  mappedAt=0;
  reader.visitAll(decodedInstructions);
  ShortArrayCodeOutput out=new ShortArrayCodeOutput(size);
  for (  DecodedInstruction instruction : mappedInstructions) {
    if (instruction != null) {
      instruction.encode(out);
    }
  }
  this.indexMap=null;
  return out.getArray();
}
