public static void premain(String agentArgs,Instrumentation inst){
  inst.addTransformer(new Transformer(),true);
  System.setProperty(Transformer.EA_ASYNC_RUNNING,"true");
}
