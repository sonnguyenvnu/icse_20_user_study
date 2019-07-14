/** 
 * Programmatic interface to the error-prone Java compiler.
 * @param args the same args which would be passed to javac on the command line
 * @param out a {@link PrintWriter} to which to send diagnostic output
 * @return result from the compiler invocation
 */
public static Result compile(String[] args,PrintWriter out){
  return ErrorProneCompiler.builder().redirectOutputTo(out).build().run(args);
}
