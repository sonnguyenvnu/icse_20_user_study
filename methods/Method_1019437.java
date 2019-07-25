@Override public void read(InputStream inputStream) throws SocksException, IOException {
  version=checkEnd(inputStream.read());
  int cmd=checkEnd(inputStream.read());
switch (cmd) {
case CMD_CONNECT:
    command=SocksCommand.CONNECT;
  break;
case CMD_BIND:
command=SocksCommand.BIND;
break;
case CMD_UDP_ASSOCIATE:
command=SocksCommand.UDP_ASSOCIATE;
break;
default :
socksException=SocksException.serverReplyException(ServerReply.COMMAND_NOT_SUPPORTED);
}
reserved=checkEnd(inputStream.read());
addressType=checkEnd(inputStream.read());
if (!AddressType.isSupport(addressType) && socksException == null) {
socksException=SocksException.serverReplyException(ServerReply.ADDRESS_TYPE_NOT_SUPPORTED);
}
switch (addressType) {
case AddressType.IPV4:
byte[] addressBytes=StreamUtil.read(inputStream,4);
inetAddress=InetAddress.getByAddress(addressBytes);
break;
case AddressType.IPV6:
byte[] addressBytes6=StreamUtil.read(inputStream,16);
inetAddress=InetAddress.getByAddress(addressBytes6);
break;
case AddressType.DOMAIN_NAME:
int domainLength=checkEnd(inputStream.read());
if (domainLength < 1) {
throw new SocksException("Length of domain must great than 0");
}
byte[] domainBytes=StreamUtil.read(inputStream,domainLength);
host=new String(domainBytes,Charset.forName("UTF-8"));
try {
inetAddress=InetAddress.getByName(host);
}
 catch (UnknownHostException e) {
if (socksException == null) {
socksException=SocksException.serverReplyException(ServerReply.HOST_UNREACHABLE);
}
}
break;
default :
break;
}
byte[] portBytes=StreamUtil.read(inputStream,2);
port=SocksUtil.bytesToInt(portBytes);
}
