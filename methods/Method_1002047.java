public Client map(ClientsRecord r){
  ApiDate lastSeen=Optional.ofNullable(r.getLastseen()).map(ApiDate::new).orElse(null);
  ApiDate expiration=Optional.ofNullable(r.getExpiration()).map(ApiDate::new).orElse(null);
  return new Client(r.getId(),r.getName(),r.getDescription(),new ApiDate(r.getCreatedat()),r.getCreatedby(),new ApiDate(r.getUpdatedat()),r.getUpdatedby(),lastSeen,expiration,r.getEnabled(),r.getAutomationallowed());
}
