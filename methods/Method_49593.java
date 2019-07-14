@Override public void register(String store,String key,KeyInformation information,BaseTransaction tx) throws BackendException {
  final Class<?> dataType=information.getDataType();
  final Mapping map=Mapping.getMapping(information);
  Preconditions.checkArgument(map == Mapping.DEFAULT || AttributeUtil.isString(dataType) || (map == Mapping.PREFIX_TREE && AttributeUtil.isGeo(dataType)),"Specified illegal mapping [%s] for data type [%s]",map,dataType);
}
