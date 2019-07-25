public String[] extractor(String name){
  if (null != map && map.containsKey(name)) {
    Object obj=map.get(name);
    if (obj instanceof String[])     return (String[])obj;
    if (obj == null)     return null;
    return new String[]{obj.toString()};
  }
  if (req == null)   return null;
  return req.getParameterValues(name);
}
