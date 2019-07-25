public boolean validate(MetaData metaData){
  for (  IndexRoutingTable indexRoutingTable : this) {
    if (indexRoutingTable.validate(metaData) == false) {
      return false;
    }
  }
  return true;
}
