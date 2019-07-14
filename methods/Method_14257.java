static public boolean sameValue(Object v1,Object v2){
  if (v1 == null) {
    return (v2 == null);
  }
 else   if (v2 == null) {
    return (v1 == null);
  }
 else {
    return v1.equals(v2);
  }
}
