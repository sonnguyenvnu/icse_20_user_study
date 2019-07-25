void hotswap(){
  Map<ReferenceType,byte[]> map=newClassFiles;
  jvm.redefineClasses(map);
  newClassFiles=null;
}
