private static String name(VirtualMachineDescriptor item){
  String str=item.toString();
  str=str.substring(str.indexOf(":") + 1);
  return str;
}
