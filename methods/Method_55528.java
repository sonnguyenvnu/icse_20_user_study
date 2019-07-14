private static long loadLibrary(String name){
  long handle;
  try (MemoryStack stack=stackPush()){
    handle=dlopen(stack.ASCII(name),RTLD_LAZY | RTLD_LOCAL);
  }
   if (handle == NULL) {
    throw new UnsatisfiedLinkError("Failed to dynamically load library: " + name + "(error = " + dlerror() + ")");
  }
  return handle;
}
