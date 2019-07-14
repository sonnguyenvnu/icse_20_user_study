void buildArrayType(){
  String[] array_methods_none={"append","buffer_info","byteswap","extend","fromfile","fromlist","fromstring","fromunicode","index","insert","pop","read","remove","reverse","tofile","tolist","typecode","write"};
  for (  String m : array_methods_none) {
    BaseArray.table.insert(m,newLibUrl("array"),newFunc(Type.NONE),METHOD);
  }
  String[] array_methods_num={"count","itemsize"};
  for (  String m : array_methods_num) {
    BaseArray.table.insert(m,newLibUrl("array"),newFunc(Type.INT),METHOD);
  }
  String[] array_methods_str={"tostring","tounicode"};
  for (  String m : array_methods_str) {
    BaseArray.table.insert(m,newLibUrl("array"),newFunc(Type.STR),METHOD);
  }
}
