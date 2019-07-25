public byte[] serialize(TransactionXid xid,Object obj){
  TransactionArchive archive=(TransactionArchive)obj;
  String propagatedBy=String.valueOf(archive.getPropagatedBy());
  RemoteNode remoteNode=CommonUtils.getRemoteNode(propagatedBy);
  byte[] hostByteArray=new byte[4];
  byte[] nameByteArray=new byte[0];
  byte[] portByteArray=new byte[2];
  if (remoteNode != null) {
    String hostStr=remoteNode.getServerHost();
    String nameStr=remoteNode.getServiceKey();
    String portStr=String.valueOf(remoteNode.getServerPort());
    String[] hostArray=hostStr.split("\\s*\\.\\s*");
    for (int i=0; hostArray.length == 4 && i < hostArray.length; i++) {
      try {
        int value=Integer.valueOf(hostArray[i]);
        hostByteArray[i]=(byte)(value - 128);
      }
 catch (      RuntimeException rex) {
        logger.warn(rex.getMessage(),rex);
      }
    }
    nameByteArray=StringUtils.isBlank(nameStr) ? new byte[0] : nameStr.getBytes();
    try {
      short port=(short)(Integer.valueOf(portStr) - 32768);
      byte[] byteArray=ByteUtils.shortToByteArray(port);
      System.arraycopy(byteArray,0,portByteArray,0,2);
    }
 catch (    RuntimeException rex) {
      logger.warn(rex.getMessage(),rex);
    }
  }
  List<CompensableArchive> nativeArchiveList=archive.getCompensableResourceList();
  List<XAResourceArchive> remoteArchiveList=archive.getRemoteResources();
  int nativeArchiveNumber=nativeArchiveList.size();
  int remoteArchiveNumber=remoteArchiveList.size();
  byte[] varByteArray=null;
  if (archive.getVariables() == null) {
    varByteArray=ByteUtils.shortToByteArray((short)0);
  }
 else {
    try {
      byte[] textByteArray=SerializeUtils.serializeObject((Serializable)archive.getVariables());
      byte[] sizeByteArray=ByteUtils.shortToByteArray((short)textByteArray.length);
      varByteArray=new byte[sizeByteArray.length + textByteArray.length];
      System.arraycopy(sizeByteArray,0,varByteArray,0,sizeByteArray.length);
      System.arraycopy(textByteArray,0,varByteArray,sizeByteArray.length,textByteArray.length);
    }
 catch (    Exception ex) {
      logger.error("Error occurred while serializing variable: {}",archive.getVariables(),ex);
      varByteArray=ByteUtils.shortToByteArray((short)0);
    }
  }
  long recoveredMillis=archive.getRecoveredAt();
  int recoveredTimes=archive.getRecoveredTimes();
  int length=6 + 4 + 1 + nameByteArray.length + 2 + varByteArray.length + 2 + 8 + 1;
  byte[][] nativeByteArray=new byte[nativeArchiveNumber][];
  for (int i=0; i < nativeArchiveNumber; i++) {
    CompensableArchive compensableArchive=nativeArchiveList.get(i);
    byte[] compensableByteArray=this.compensableArchiveDeserializer.serialize(xid,compensableArchive);
    byte[] lengthByteArray=ByteUtils.shortToByteArray((short)compensableByteArray.length);
    byte[] elementByteArray=new byte[compensableByteArray.length + 2];
    System.arraycopy(lengthByteArray,0,elementByteArray,0,lengthByteArray.length);
    System.arraycopy(compensableByteArray,0,elementByteArray,2,compensableByteArray.length);
    nativeByteArray[i]=elementByteArray;
    length=length + elementByteArray.length;
  }
  byte[][] remoteByteArray=new byte[remoteArchiveNumber][];
  for (int i=0; i < remoteArchiveNumber; i++) {
    XAResourceArchive resourceArchive=remoteArchiveList.get(i);
    byte[] resourceByteArray=this.resourceArchiveDeserializer.serialize(xid,resourceArchive);
    byte[] lengthByteArray=ByteUtils.shortToByteArray((short)resourceByteArray.length);
    byte[] elementByteArray=new byte[resourceByteArray.length + 2];
    System.arraycopy(lengthByteArray,0,elementByteArray,0,lengthByteArray.length);
    System.arraycopy(resourceByteArray,0,elementByteArray,2,resourceByteArray.length);
    remoteByteArray[i]=elementByteArray;
    length=length + elementByteArray.length;
  }
  int position=0;
  byte[] byteArray=new byte[length];
  byteArray[position++]=(byte)archive.getStatus();
  byteArray[position++]=(byte)archive.getVote();
  byteArray[position++]=archive.isCoordinator() ? (byte)0x1 : (byte)0x0;
  byteArray[position++]=archive.isPropagated() ? (byte)0x1 : (byte)0x0;
  byteArray[position++]=archive.isCompensable() ? (byte)0x1 : (byte)0x0;
  byteArray[position++]=(byte)archive.getCompensableStatus();
  System.arraycopy(hostByteArray,0,byteArray,position,4);
  position=position + 4;
  byteArray[position++]=(byte)(nameByteArray.length - 128);
  System.arraycopy(nameByteArray,0,byteArray,position,nameByteArray.length);
  position=position + nameByteArray.length;
  System.arraycopy(portByteArray,0,byteArray,position,2);
  position=position + 2;
  System.arraycopy(varByteArray,0,byteArray,position,varByteArray.length);
  position=position + varByteArray.length;
  byteArray[position++]=(byte)(recoveredTimes - 128);
  byte[] millisByteArray=ByteUtils.longToByteArray(recoveredMillis);
  System.arraycopy(millisByteArray,0,byteArray,position,millisByteArray.length);
  position=position + millisByteArray.length;
  byteArray[position++]=(byte)nativeArchiveNumber;
  byteArray[position++]=(byte)remoteArchiveNumber;
  for (int i=0; i < nativeArchiveNumber; i++) {
    byte[] elementByteArray=nativeByteArray[i];
    System.arraycopy(elementByteArray,0,byteArray,position,elementByteArray.length);
    position=position + elementByteArray.length;
  }
  for (int i=0; i < remoteArchiveNumber; i++) {
    byte[] elementByteArray=remoteByteArray[i];
    System.arraycopy(elementByteArray,0,byteArray,position,elementByteArray.length);
    position=position + elementByteArray.length;
  }
  return byteArray;
}
