/** 
 * Loads the dynamic library file named by the null-terminated string  {@code filename} and returns an opaque "handle" for the dynamic library. If{@code filename} is {@code NULL}, then the returned handle is for the main program.
 * @param filename the name of the dynamic library to open, or {@code NULL}
 * @param mode     a bitfield. One or more of:<br><table><tr><td>{@link #RTLD_LAZY}</td><td> {@link #RTLD_NOW}</td><td> {@link #RTLD_BINDING_MASK}</td><td> {@link #RTLD_NOLOAD}</td><td> {@link #RTLD_DEEPBIND}</td><td> {@link #RTLD_GLOBAL}</td></tr><tr><td> {@link #RTLD_LOCAL}</td><td> {@link #RTLD_NODELETE}</td></tr></table>
 */
@NativeType("void *") public static long dlopen(@Nullable @NativeType("char const *") ByteBuffer filename,int mode){
  if (CHECKS) {
    checkNT1Safe(filename);
  }
  return ndlopen(memAddressSafe(filename),mode);
}
