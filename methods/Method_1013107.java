@Override public Tuple2<String,String> deserialize(byte[] message){
  byte[] str1=new byte[message.length];
  byte[] str2=new byte[message.length];
  int index=0;
  for (int i=0; i < message.length; i++) {
    if (message[i] == ' ') {
      index=i;
      break;
    }
    str1[i]=message[i];
  }
  for (int i=index + 1; i < message.length; i++) {
    str2[i - index - 1]=message[i];
  }
  return new Tuple2<String,String>(new String(str1,0,index),new String(str2,0,message.length - index - 1));
}
