@Override public RestResponse replay(RestRequest req){
  try {
    return _db.get(canonicalize(req));
  }
 catch (  Exception e) {
    _log.debug("Failed to canonicalize request: " + req,e);
    return null;
  }
}
