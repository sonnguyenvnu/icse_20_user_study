public void attribPosition(String name,float x,float y,float z){
  if (recorder != null)   recorder.attribPosition(name,x,y,z);
  g.attribPosition(name,x,y,z);
}
