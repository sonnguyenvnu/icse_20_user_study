public void setVertexShader(URL vertURL){
  this.vertexURL=vertURL;
  vertexShaderSource=pgl.loadVertexShader(vertURL);
}
