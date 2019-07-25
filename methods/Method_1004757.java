@Override public Response execute(){
  StringBuilder path=new StringBuilder();
  if (indices.size() > 0) {
    path.append(StringUtils.concatenate(indices));
  }
 else {
    path.append("_all");
  }
  path.append("/_alias");
  if (aliases.size() > 0) {
    path.append("/").append(StringUtils.concatenate(aliases));
  }
  return new Response((Map<String,Object>)client.get(path.toString(),null));
}
