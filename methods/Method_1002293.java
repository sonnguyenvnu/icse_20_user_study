public final boolean commit(){
  if (pointer == EOF)   return false;
  writeRelease(pointer);
  return true;
}
