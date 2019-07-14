protected EventMessage buildEvent(String schemeIdUri,String value,long id,long durationMs,byte[] messageData,long presentationTimeUs){
  return new EventMessage(schemeIdUri,value,durationMs,id,messageData,presentationTimeUs);
}
