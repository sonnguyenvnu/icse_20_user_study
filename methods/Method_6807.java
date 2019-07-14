public void applyMediaExistanceFlags(int flags){
  if (flags == -1) {
    checkMediaExistance();
  }
 else {
    attachPathExists=(flags & 1) != 0;
    mediaExists=(flags & 2) != 0;
  }
}
