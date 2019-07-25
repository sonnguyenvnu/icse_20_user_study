public RequestOptions flags(Message.Flag... flags){
  if (flags != null)   for (  Message.Flag flag : flags)   if (flag != null)   this.flags|=flag.value();
  return this;
}
