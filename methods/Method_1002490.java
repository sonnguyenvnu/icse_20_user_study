private RestRequest canonicalize(RestRequest req){
  return req.builder().buildCanonical();
}
