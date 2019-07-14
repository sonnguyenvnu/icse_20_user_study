public int getRealId(){
  return messageOwner.realId != 0 ? messageOwner.realId : messageOwner.id;
}
