@Override public void register(String store,String key,KeyInformation information,BaseTransaction tx) throws BackendException {
  final Class<?> dataType=information.getDataType();
  final Mapping map=Mapping.getMapping(information);
  Preconditions.checkArgument(map == Mapping.DEFAULT || AttributeUtil.isString(dataType) || (map == Mapping.PREFIX_TREE && AttributeUtil.isGeo(dataType)),"Specified illegal mapping [%s] for data type [%s]",map,dataType);
  final String indexStoreName=getIndexStoreName(store);
  if (useExternalMappings) {
    try {
      final IndexMapping mappings=client.getMapping(indexStoreName,store);
      if (mappings == null || (!mappings.isDynamic() && !mappings.getProperties().containsKey(key))) {
        throw new PermanentBackendException("The external mapping for index '" + indexStoreName + "' and type '" + store + "' do not have property '" + key + "'");
      }
 else       if (allowMappingUpdate && mappings.isDynamic()) {
        this.pushMapping(store,key,information);
      }
    }
 catch (    final IOException e) {
      throw new PermanentBackendException(e);
    }
  }
 else {
    try {
      checkForOrCreateIndex(indexStoreName);
    }
 catch (    final IOException e) {
      throw new PermanentBackendException(e);
    }
    this.pushMapping(store,key,information);
  }
}
