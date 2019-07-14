private ReferenceBean<GenericService> build(String interfaceName,String version,String group,Map<String,Object> dubboTranslatedAttributes){
  Integer key=Objects.hash(interfaceName,version,group,dubboTranslatedAttributes);
  return cache.computeIfAbsent(key,k -> {
    ReferenceBean<GenericService> referenceBean=new ReferenceBean<>();
    referenceBean.setGeneric(true);
    referenceBean.setInterface(interfaceName);
    referenceBean.setVersion(version);
    referenceBean.setGroup(group);
    bindReferenceBean(referenceBean,dubboTranslatedAttributes);
    return referenceBean;
  }
);
}
