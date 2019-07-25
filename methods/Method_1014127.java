private BindingInfoDTO map(BindingInfo bindingInfo,Locale locale){
  URI configDescriptionURI=bindingInfo.getConfigDescriptionURI();
  return new BindingInfoDTO(bindingInfo.getUID(),bindingInfo.getName(),bindingInfo.getAuthor(),bindingInfo.getDescription(),configDescriptionURI != null ? configDescriptionURI.toString() : null);
}
