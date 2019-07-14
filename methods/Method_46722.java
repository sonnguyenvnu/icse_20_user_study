public static List<TxManagerHost> parserList(List<String> managerHost){
  List<TxManagerHost> list=new ArrayList<>();
  for (  String host : managerHost) {
    String[] array=host.split(":");
    TxManagerHost manager=new TxManagerHost(array[0],Integer.parseInt(array[1]));
    list.add(manager);
  }
  return list;
}
