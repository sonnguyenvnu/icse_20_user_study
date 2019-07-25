public static PropertyMetadata construct(Boolean req,String desc,Integer index,String defaultValue){
  if ((desc != null) || (index != null) || (defaultValue != null)) {
    return new PropertyMetadata(req,desc,index,defaultValue,null,null,null);
  }
  if (req == null) {
    return STD_REQUIRED_OR_OPTIONAL;
  }
  return req ? STD_REQUIRED : STD_OPTIONAL;
}
