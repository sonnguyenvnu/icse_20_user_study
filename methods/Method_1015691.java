protected void resize(int new_capacity){
  if (new_capacity <= messages.length)   return;
  Message[] tmp=new Message[new_capacity];
  System.arraycopy(messages,0,tmp,0,messages.length);
  messages=tmp;
}
