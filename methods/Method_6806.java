public int getMediaExistanceFlags(){
  int flags=0;
  if (attachPathExists) {
    flags|=1;
  }
  if (mediaExists) {
    flags|=2;
  }
  return flags;
}
