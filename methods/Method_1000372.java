private Pojo __macro(MappingField ef,List<FieldMacroInfo> infoList){
  FieldMacroInfo theInfo=null;
  for (  FieldMacroInfo info : infoList) {
    if (DB.OTHER == info.getDb()) {
      theInfo=info;
    }
 else     if (info.getDb().name().equalsIgnoreCase(expert.getDatabaseType())) {
      theInfo=info;
      break;
    }
  }
  if (null != theInfo) {
    if (theInfo.isEl())     return new ElFieldMacro(ef,theInfo.getValue());
 else     return new SqlFieldMacro(ef,theInfo.getValue());
  }
  return null;
}
