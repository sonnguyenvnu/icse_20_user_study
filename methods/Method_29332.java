private void caculate(){
  float totalIn=0;
  float totalOut=0;
  for (  NetworkInterfaceCard nic : ncList) {
    String[] array=nic.getName().split("-");
    if ("read".equals(array[1])) {
      ninDetail.append(array[0]);
      ninDetail.append(",");
      ninDetail.append(nic.getValue());
      ninDetail.append(";");
      totalIn+=nic.getValue();
    }
 else     if ("write".equals(array[1])) {
      noutDetail.append(array[0]);
      noutDetail.append(",");
      noutDetail.append(nic.getValue());
      noutDetail.append(";");
      totalOut+=nic.getValue();
    }
  }
  nin=new BigDecimal(totalIn).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
  nout=new BigDecimal(totalOut).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
}
