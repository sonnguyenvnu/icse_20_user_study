@Override public String[] directories(){
  String installDir=getVlcInstallDir();
  return installDir != null ? new String[]{installDir} : new String[0];
}
