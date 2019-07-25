public Message first(){
  for (int i=0; i < index; i++)   if (messages[i] != null)   return messages[i];
  return null;
}
