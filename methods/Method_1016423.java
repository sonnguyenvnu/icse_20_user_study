public static final List<InetAddress> genlist(InetAddress base,final int subnet){
  final ArrayList<InetAddress> c=new ArrayList<InetAddress>(1);
  genlist(c,base,subnet);
  return c;
}
