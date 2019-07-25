public __ptr__ malloc(){
  if (tobeAllocated != null) {
    return Memory.malloc(tobeAllocated);
  }
  return (__ptr__)new CObject(-1,tobeAllocated);
}
