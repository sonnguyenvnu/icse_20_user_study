byte[] buildSignatureData(long nonce){
  final byte[] nonceData=String.valueOf(nonce).getBytes(CHARSET);
  final byte[] data=new byte[nonceData.length + 1 + publicKey.length];
  System.arraycopy(nonceData,0,data,0,nonceData.length);
  data[nonceData.length]='$';
  System.arraycopy(publicKey,0,data,nonceData.length + 1,publicKey.length);
  return data;
}
