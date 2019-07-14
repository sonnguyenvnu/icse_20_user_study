protected PersonnelAuthentication getPersonnelAuthorization(){
  return PersonnelAuthentication.current().orElseThrow(AccessDenyException::new);
}
