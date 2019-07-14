static MemoryAllocator getInstance(){
  Object allocator=Configuration.MEMORY_ALLOCATOR.get();
  if (allocator instanceof MemoryAllocator) {
    return (MemoryAllocator)allocator;
  }
  if (!"system".equals(allocator)) {
    String className;
    if (allocator == null || "jemalloc".equals(allocator)) {
      className="org.lwjgl.system.jemalloc.JEmallocAllocator";
    }
 else     if ("rpmalloc".equals(allocator)) {
      className="org.lwjgl.system.rpmalloc.RPmallocAllocator";
    }
 else {
      className=allocator.toString();
    }
    try {
      Class<?> allocatorClass=Class.forName(className);
      return (MemoryAllocator)allocatorClass.getConstructor().newInstance();
    }
 catch (    Throwable t) {
      if (Checks.DEBUG && allocator != null) {
        t.printStackTrace(DEBUG_STREAM);
      }
      apiLog(String.format("Warning: Failed to instantiate memory allocator: %s. Using the system default.",className));
    }
  }
  return new StdlibAllocator();
}
