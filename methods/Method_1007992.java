static String endpoint(String[] indices,String[] types,String endpoint){
  return endpoint(String.join(",",indices),String.join(",",types),endpoint);
}
