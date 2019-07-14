private void bindReferenceBean(ReferenceBean<GenericService> referenceBean,Map<String,Object> dubboTranslatedAttributes){
  DataBinder dataBinder=new DataBinder(referenceBean);
  dataBinder.registerCustomEditor(String.class,"filter",new StringTrimmerEditor(true));
  dataBinder.registerCustomEditor(String.class,"listener",new StringTrimmerEditor(true));
  dataBinder.registerCustomEditor(Map.class,"parameters",new PropertyEditorSupport(){
    @Override public void setAsText(    String text) throws java.lang.IllegalArgumentException {
      String content=StringUtils.trimAllWhitespace(text);
      if (!StringUtils.hasText(content)) {
        return;
      }
      content=StringUtils.replace(content,"=",",");
      content=StringUtils.replace(content,":",",");
      Map<String,String> parameters=CollectionUtils.toStringMap(commaDelimitedListToStringArray(content));
      setValue(parameters);
    }
  }
);
  dataBinder.setDisallowedFields("registries");
  dataBinder.bind(new MutablePropertyValues(dubboTranslatedAttributes));
  registryConfigs.ifAvailable(referenceBean::setRegistries);
}
