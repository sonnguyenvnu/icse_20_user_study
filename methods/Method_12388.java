Instance clearUnsavedEvents(){
  return new Instance(this.id,this.version,this.registration,this.registered,this.statusInfo,this.statusTimestamp,this.info,this.endpoints,this.buildVersion,this.tags,emptyList());
}
