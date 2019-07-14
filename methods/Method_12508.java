@SuppressWarnings("unchecked") protected StatusInfo getStatusInfoFromStatus(HttpStatus httpStatus,Map<String,?> body){
  if (httpStatus.is2xxSuccessful()) {
    return StatusInfo.ofUp();
  }
  Map<String,Object> details=new LinkedHashMap<>();
  details.put("status",httpStatus.value());
  details.put("error",httpStatus.getReasonPhrase());
  if (body.get("details") instanceof Map) {
    details.putAll((Map<? extends String,?>)body.get("details"));
  }
 else {
    details.putAll(body);
  }
  return StatusInfo.ofDown(details);
}
