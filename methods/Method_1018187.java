@Override public <C extends Serializable>Alert create(URI type,String subtype,Level level,String description,C content){
  final Principal user=SecurityContextHolder.getContext().getAuthentication() != null ? SecurityContextHolder.getContext().getAuthentication() : null;
  if (subtype == null) {
    subtype="Other";
  }
  if (content != null && content instanceof EntityIdentificationAlertContent) {
    subtype="Entity";
  }
  final String finalSubType=subtype;
  Alert created=this.metadataAccess.commit(() -> {
    JpaAlert alert=new JpaAlert(type,finalSubType,level,user,description,content);
    this.repository.save(alert);
    return asValue(alert);
  }
,MetadataAccess.SERVICE);
  updateLastUpdatedTime();
  notifyReceivers(1);
  return created;
}
