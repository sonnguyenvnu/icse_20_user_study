protected void init(){
  if (!wavMode) {
    blocksize=(2 * ((nbchannels + 1) / 2)) * sampleFrequency * bitsperSample / 1600;
    payload=new byte[4];
switch (nbchannels) {
case 1:
      payload[2]=17;
    break;
case 2:
  payload[2]=49;
break;
case 3:
payload[2]=65;
break;
case 4:
payload[2]=113;
break;
case 5:
payload[2]=-127;
break;
case 6:
payload[2]=-111;
break;
case 7:
payload[2]=-95;
break;
case 8:
payload[2]=-79;
break;
default :
break;
}
payload[0]=(byte)((blocksize >> 8) & 0xff);
payload[1]=(byte)((blocksize + 256) % 256);
if (sampleFrequency == 96000) {
payload[2]=(byte)(payload[2] + 3);
}
if (sampleFrequency == 192000) {
payload[2]=(byte)(payload[2] + 4);
}
payload[3]=(byte)(16 * (bitsperSample - 12));
}
 else {
int BytePerBloc=nbchannels * bitsperSample / 8;
int BytePerSec=sampleFrequency * BytePerBloc;
payload=new byte[44];
payload[0]=82;
payload[1]=73;
payload[2]=70;
payload[3]=70;
payload[8]=87;
payload[9]=65;
payload[10]=86;
payload[11]=69;
payload[12]=102;
payload[13]=109;
payload[14]=116;
payload[15]=32;
payload[16]=16;
payload[20]=1;
payload[22]=(byte)nbchannels;
payload[25]=(byte)(sampleFrequency & 0xff);
payload[24]=(byte)((sampleFrequency >> 8) & 0xff);
payload[30]=(byte)(BytePerSec & 0xff);
payload[29]=(byte)((BytePerSec >> 8) & 0xff);
payload[32]=(byte)(BytePerBloc & 0xff);
payload[34]=(byte)(bitsperSample & 0xff);
payload[36]=100;
payload[37]=97;
payload[38]=116;
payload[39]=97;
payload[40]=-1;
payload[41]=-1;
payload[42]=-1;
payload[43]=-1;
}
}
