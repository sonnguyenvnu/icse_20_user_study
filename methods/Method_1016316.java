public static String sizename(int size){
  if (size < 1024)   return size + " bytes";
  size=size / 1024;
  if (size < 1024)   return size + " kbyte";
  size=size / 1024;
  if (size < 1024)   return size + " mbyte";
  size=size / 1024;
  return size + " gbyte";
}
