public static @Nullable Projection decode(byte[] projectionData,@C.StereoMode int stereoMode){
  ParsableByteArray input=new ParsableByteArray(projectionData);
  ArrayList<Mesh> meshes=null;
  try {
    meshes=isProj(input) ? parseProj(input) : parseMshp(input);
  }
 catch (  ArrayIndexOutOfBoundsException ignored) {
  }
  if (meshes == null) {
    return null;
  }
 else {
switch (meshes.size()) {
case 1:
      return new Projection(meshes.get(0),stereoMode);
case 2:
    return new Projection(meshes.get(0),meshes.get(1),stereoMode);
case 0:
default :
  return null;
}
}
}
