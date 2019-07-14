protected static int accessToIndex(int access){
  int index=0;
  if ((access & Type.FLAG_STATIC) != 0)   index+=4;
  if ((access & Type.FLAG_FINAL) != 0)   index+=8;
  if ((access & Type.FLAG_ABSTRACT) != 0)   index+=16;
  if ((access & Type.FLAG_PUBLIC) != 0)   return index + 1;
 else   if ((access & Type.FLAG_PROTECTED) != 0)   return index + 2;
 else   if ((access & Type.FLAG_PRIVATE) != 0)   return index + 3;
 else   return index;
}
