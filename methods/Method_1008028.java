/** 
 * Runs the two-pass compiler to generate a Painless script.
 * @param loader The ClassLoader used to define the script.
 * @param name The name of the script.
 * @param source The source code for the script.
 * @param settings The CompilerSettings to be used during the compilation.
 * @return An executable script that implements both a specified interface and is a subclass of {@link PainlessScript}
 */
Constructor<?> compile(Loader loader,MainMethodReserved reserved,String name,String source,CompilerSettings settings){
  if (source.length() > MAXIMUM_SOURCE_LENGTH) {
    throw new IllegalArgumentException("Scripts may be no longer than " + MAXIMUM_SOURCE_LENGTH + " characters.  The passed in script is " + source.length() + " characters.  Consider using a" + " plugin if a script longer than this length is a requirement.");
  }
  ScriptClassInfo scriptClassInfo=new ScriptClassInfo(definition,base);
  SSource root=Walker.buildPainlessTree(scriptClassInfo,reserved,name,source,settings,definition,null);
  root.analyze(definition);
  root.write();
  try {
    Class<? extends PainlessScript> clazz=loader.defineScript(CLASS_NAME,root.getBytes());
    clazz.getField("$NAME").set(null,name);
    clazz.getField("$SOURCE").set(null,source);
    clazz.getField("$STATEMENTS").set(null,root.getStatements());
    clazz.getField("$DEFINITION").set(null,definition);
    return clazz.getConstructors()[0];
  }
 catch (  Exception exception) {
    throw new IllegalStateException("An internal error occurred attempting to define the script [" + name + "].",exception);
  }
}
