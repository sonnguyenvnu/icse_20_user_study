static public void registerControl(String name,Control c){
  s_nameToControl.put(name,c);
  s_controlToName.put(c,name);
}
