protected int createShader(int shaderType,String source){
  int shader=createShader(shaderType);
  if (shader != 0) {
    shaderSource(shader,source);
    compileShader(shader);
    if (!compiled(shader)) {
      System.err.println("Could not compile shader " + shaderType + ":");
      System.err.println(getShaderInfoLog(shader));
      deleteShader(shader);
      shader=0;
    }
  }
  return shader;
}
