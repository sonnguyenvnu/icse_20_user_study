void buildFileType(){
  String url="stdtypes.html#bltin-file-objects";
  State table=BaseFile.table;
  String[] methods_unknown={"__enter__","__exit__","__iter__","flush","readinto","truncate"};
  for (  String m : methods_unknown) {
    table.insert(m,newLibUrl(url),newFunc(),METHOD);
  }
  String[] methods_str={"next","read","readline"};
  for (  String m : methods_str) {
    table.insert(m,newLibUrl(url),newFunc(Type.STR),METHOD);
  }
  String[] num={"fileno","isatty","tell"};
  for (  String m : num) {
    table.insert(m,newLibUrl(url),newFunc(Type.INT),METHOD);
  }
  String[] methods_none={"close","seek","write","writelines"};
  for (  String m : methods_none) {
    table.insert(m,newLibUrl(url),newFunc(Type.NONE),METHOD);
  }
  table.insert("readlines",newLibUrl(url),newFunc(newList(Type.STR)),METHOD);
  table.insert("xreadlines",newLibUrl(url),newFunc(Type.STR),METHOD);
  table.insert("closed",newLibUrl(url),Type.INT,ATTRIBUTE);
  table.insert("encoding",newLibUrl(url),Type.STR,ATTRIBUTE);
  table.insert("errors",newLibUrl(url),Type.UNKNOWN,ATTRIBUTE);
  table.insert("mode",newLibUrl(url),Type.INT,ATTRIBUTE);
  table.insert("name",newLibUrl(url),Type.STR,ATTRIBUTE);
  table.insert("softspace",newLibUrl(url),Type.INT,ATTRIBUTE);
  table.insert("newlines",newLibUrl(url),newUnion(Type.STR,newTuple(Type.STR)),ATTRIBUTE);
}
