public static SEnumerationLiteralId deserialize(String s){
  int split=s.lastIndexOf('/');
  SDataTypeId enumId=SDataTypeId.deserialize(s.substring(0,split));
  long ref=Long.parseLong(s.substring(split + 1));
  return new SEnumerationLiteralId(enumId,ref);
}
