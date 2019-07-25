/** 
 * The entry point invoked when this agent is started after the JVM starts.
 */
public static void agentmain(String agentArgs,Instrumentation inst) throws Throwable {
  if (!inst.isRedefineClassesSupported())   throw new RuntimeException("this JVM does not support redefinition of classes");
  instrumentation=inst;
}
