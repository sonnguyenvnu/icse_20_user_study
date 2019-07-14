public void markAsNew(){
  flags|=NEW;
  flags&=~CALL;
}
