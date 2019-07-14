@Override protected void defaultSettings(){
  super.defaultSettings();
  manipulatingCamera=false;
  textureMode(IMAGE);
  ambient(255);
  specular(125);
  emissive(0);
  shininess(1);
  setAmbient=false;
}
