public void setVertexShader(String vertFilename){
  this.vertexFilename=vertFilename;
  vertexShaderSource=pgl.loadVertexShader(vertFilename);
}
