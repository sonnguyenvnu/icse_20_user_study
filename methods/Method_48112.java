public Shape readShape(DataInputStream dataInput) throws IOException {
  if (dataInput.readByte() == 0) {
    return binaryCodec.readJtsGeom(dataInput);
  }
 else {
    return binaryCodec.readShape(dataInput);
  }
}
