public String[] extractor(String name){
  if (req == null)   return new String[0];
  return req.getParameterValues(name);
}
