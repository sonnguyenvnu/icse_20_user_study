protected void addChildren(ArrayList<OBJFace> faces,ArrayList<OBJMaterial> materials,ArrayList<PVector> coords,ArrayList<PVector> normals,ArrayList<PVector> texcoords){
  int mtlIdxCur=-1;
  OBJMaterial mtl=null;
  for (int i=0; i < faces.size(); i++) {
    OBJFace face=faces.get(i);
    if (mtlIdxCur != face.matIdx || face.matIdx == -1) {
      mtlIdxCur=PApplet.max(0,face.matIdx);
      mtl=materials.get(mtlIdxCur);
    }
    PShape child=new PShapeOBJ(face,mtl,coords,normals,texcoords);
    addChild(child);
  }
}
