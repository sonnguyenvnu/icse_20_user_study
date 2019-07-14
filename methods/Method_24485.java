public void beginShape(int kind){
  shape=kind;
  if ((shape != LINES) && (shape != TRIANGLES) && (shape != POLYGON)) {
    String err="RawDXF can only be used with beginRaw(), " + "because it only supports lines and triangles";
    throw new RuntimeException(err);
  }
  if ((shape == POLYGON) && fill) {
    throw new RuntimeException("DXF Export only supports non-filled shapes.");
  }
  vertexCount=0;
}
