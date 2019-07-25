public static OsArchitecture detect(){
  OS os=OSDetector.getOS();
  Architecture arch=OSDetector.getArchitecture();
  return new OsArchitecture(os,arch);
}
