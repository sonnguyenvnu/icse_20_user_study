/** 
 * Retrieves a module handle for the specified module. The module must have been loaded by the calling process.
 * @param moduleName the name of the loaded module (either a .dll or .exe file). If the file name extension is omitted, the default library extension .dll is appended.The file name string can include a trailing point character (.) to indicate that the module name has no extension. The string does not have to specify a path. When specifying a path, be sure to use backslashes (\), not forward slashes (/). The name is compared (case independently) to the names of modules currently mapped into the address space of the calling process. <p>If this parameter is  {@code NULL},  {@code GetModuleHandle} returns a handle to the file used to create the calling process (.exe file).</p>
 */
@NativeType("HMODULE") public static long GetModuleHandle(@Nullable @NativeType("LPCTSTR") CharSequence moduleName){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nUTF16Safe(moduleName,true);
    long moduleNameEncoded=moduleName == null ? NULL : stack.getPointerAddress();
    return nGetModuleHandle(moduleNameEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
