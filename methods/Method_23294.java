public void attrib(String name,boolean... values){
  if (recorder != null)   recorder.attrib(name,values);
  g.attrib(name,values);
}
