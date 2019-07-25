/** 
 */
public void render(VertexBuffer vertices,IndexBuffer primitives,Appearance appearance,Transform transform){
  integrityCheck();
  render(vertices,primitives,appearance,transform,-1);
}
