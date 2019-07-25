@Override protected BindingInfo localize(Bundle bundle,BindingInfo bindingInfo,Locale locale){
  if (this.bindingI18nUtil == null) {
    return null;
  }
  String name=this.bindingI18nUtil.getName(bundle,bindingInfo.getUID(),bindingInfo.getName(),locale);
  String description=this.bindingI18nUtil.getDescription(bundle,bindingInfo.getUID(),bindingInfo.getDescription(),locale);
  return new BindingInfo(bindingInfo.getUID(),name,description,bindingInfo.getAuthor(),bindingInfo.getServiceId(),bindingInfo.getConfigDescriptionURI());
}
