public int index(String what){
  Integer found=indices.get(what);
  return (found == null) ? -1 : found.intValue();
}
