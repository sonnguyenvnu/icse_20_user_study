@Override public Alert remove(Alert.ID id){
  JpaAlert.AlertId idImpl=(JpaAlert.AlertId)resolve(id);
  JpaAlert jpaAlert=this.metadataAccess.commit(() -> {
    JpaAlert alert=repository.findOne(idImpl);
    this.repository.delete(id);
    return alert;
  }
,MetadataAccess.SERVICE);
  updateLastUpdatedTime();
  return jpaAlert;
}
