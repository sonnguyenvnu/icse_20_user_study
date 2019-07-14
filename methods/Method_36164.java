@JsonIgnore public Map<String,Parameters> getPostServeActions(){
  return stubMapping != null && stubMapping.getPostServeActions() != null ? getStubMapping().getPostServeActions() : Collections.<String,Parameters>emptyMap();
}
