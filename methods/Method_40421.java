void buildFileType(){
  String url="stdtypes.html#bltin-file-objects";
  Scope table=BaseFile.getTable();
  String[] methods_unknown={"__enter__","__exit__","__iter__","flush","readinto","truncate"};
  for (  String m : methods_unknown) {
    table.update(m,newLibUrl(url),newFunc(),METHOD);
  }
  String[] methods_str={"next","read","readline"};
  for (  String m : methods_str) {
    table.update(m,newLibUrl(url),newFunc(BaseStr),METHOD);
  }
  String[] num={"fileno","isatty","tell"};
  for (  String m : num) {
    table.update(m,newLibUrl(url),newFunc(BaseNum),METHOD);
  }
  String[] methods_none={"close","seek","write","writelines"};
  for (  String m : methods_none) {
    table.update(m,newLibUrl(url),newFunc(None),METHOD);
  }
  table.update("readlines",newLibUrl(url),newFunc(newList(BaseStr)),METHOD);
  table.update("xreadlines",newLibUrl(url),newFunc(BaseFile),METHOD);
  table.update("closed",newLibUrl(url),BaseNum,ATTRIBUTE);
  table.update("encoding",newLibUrl(url),BaseStr,ATTRIBUTE);
  table.update("errors",newLibUrl(url),unknown(),ATTRIBUTE);
  table.update("mode",newLibUrl(url),BaseNum,ATTRIBUTE);
  table.update("name",newLibUrl(url),BaseStr,ATTRIBUTE);
  table.update("softspace",newLibUrl(url),BaseNum,ATTRIBUTE);
  table.update("newlines",newLibUrl(url),newUnion(BaseStr,newTuple(BaseStr)),ATTRIBUTE);
}
