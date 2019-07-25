@Override protected List<Network> execute(ListNetworksCmd command){
  WebTarget webTarget=getBaseResource().path("/networks");
  if (command.getFilters() != null && !command.getFilters().isEmpty()) {
    webTarget=webTarget.queryParam("filters",urlPathSegmentEscaper().escape(FiltersEncoder.jsonEncode(command.getFilters())));
  }
  LOGGER.trace("GET: {}",webTarget);
  List<Network> networks=webTarget.request().accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Network>>(){
  }
);
  LOGGER.trace("Response: {}",networks);
  return networks;
}
