public Message last(){
  for (int i=index - 1; i >= 0; i--)   if (messages[i] != null)   return messages[i];
  return null;
}
