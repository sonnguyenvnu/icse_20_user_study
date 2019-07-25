/** 
 * Runs the two-pass compiler to generate a Painless script.  (Used by the debugger.)
 * @param source The source code for the script.
 * @param settings The CompilerSettings to be used during the compilation.
 * @return The bytes for compilation.
 */
byte[] compile(String name,String source,CompilerSettings settings,Printer debugStream){
  if (source.length() > MAXIMUM_SOURCE_LENGTH) {
    throw new IllegalArgumentException("Scripts may be no longer than " + MAXIMUM_SOURCE_LENGTH + " characters.  The passed in script is " + source.length() + " characters.  Consider using a" + " plugin if a script longer than this length is a requirement.");
  }
  ScriptClassInfo scriptClassInfo=new ScriptClassInfo(definition,base);
  SSource root=Walker.buildPainlessTree(scriptClassInfo,new MainMethodReserved(),name,source,settings,definition,debugStream);
  root.analyze(definition);
  root.write();
  return root.getBytes();
}
