public Logo file(String file,Integer duration,Integer opacity){
  return addFileSpec(String.format("%s,%s,%s",file,duration == null ? "" : duration,opacity == null ? "" : opacity));
}
