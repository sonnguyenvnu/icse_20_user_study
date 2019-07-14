public long write(String text,List<Uri> imageUris,String linkTitle,String linkUrl,Context context){
  SendBroadcastWriter writer=new SendBroadcastWriter(text,imageUris,linkTitle,linkUrl,this);
  add(writer,context);
  return writer.getId();
}
