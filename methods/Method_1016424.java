private static final void genlist(ArrayList<InetAddress> c,InetAddress base,final int subnet){
  if (subnet == 31) {
    try {
      c.add(InetAddress.getByAddress(base.getAddress()));
    }
 catch (    final UnknownHostException e) {
    }
  }
 else {
    int ul=subnet >= 24 ? base.getAddress()[2] : (1 << (24 - subnet)) - 1;
    for (int br=subnet >= 24 ? base.getAddress()[2] : 0; br <= ul; br++) {
      for (int j=1; j < 255; j++) {
        final byte[] address=base.getAddress();
        address[2]=(byte)br;
        address[3]=(byte)j;
        try {
          c.add(InetAddress.getByAddress(address));
        }
 catch (        final UnknownHostException e) {
        }
      }
    }
  }
}
