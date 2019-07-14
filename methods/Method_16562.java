protected List<String> getAllOrg(){
  return serviceContext.getPersonService().selectAllOrgId(targetIdSupplier.get());
}
