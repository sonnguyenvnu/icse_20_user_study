public void update(byte[] puchMsg,int usDataLen){
  int uIndex;
  for (int i=0; i < usDataLen; i++) {
    uIndex=(uchCRCHi ^ puchMsg[i]) & 0xff;
    uchCRCHi=(byte)(uchCRCLo ^ auchCRCHi[uIndex]);
    uchCRCLo=auchCRCLo[uIndex];
  }
  value=((((int)uchCRCHi) << 8 | (((int)uchCRCLo) & 0xff))) & 0xffff;
  return;
}
