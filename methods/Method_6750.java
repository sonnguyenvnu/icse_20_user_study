public boolean isFromUser(){
  return messageOwner.from_id > 0 && !messageOwner.post;
}
