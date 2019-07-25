protected void write(List<PingData> list,OutputStream out) throws Exception {
  try {
    for (    PingData data : list) {
      String logical_name=data.getLogicalName();
      Address addr=data.getAddress();
      PhysicalAddress phys_addr=data.getPhysicalAddr();
      if (logical_name == null || addr == null || phys_addr == null)       continue;
      out.write(logical_name.getBytes());
      out.write(WHITESPACE);
      out.write(addressAsString(addr).getBytes());
      out.write(WHITESPACE);
      out.write(phys_addr.toString().getBytes());
      out.write(WHITESPACE);
      out.write(data.isCoord() ? String.format("T%n").getBytes() : String.format("F%n").getBytes());
    }
  }
  finally {
    Util.close(out);
  }
}
