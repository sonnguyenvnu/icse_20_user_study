@OnClassLoadEvent(classNameRegexp=".*",events=LoadEvent.REDEFINE) public static void patch(final CtClass ctClass,final ClassLoader classLoader,final Class<?> originalClass) throws IOException, CannotCompileException, NotFoundException {
  if (isSyntheticClass(originalClass)) {
    return;
  }
  final String className=ctClass.getName();
  try {
    CtMethod origMethod=ctClass.getDeclaredMethod(HOTSWAP_AGENT_CLINIT_METHOD);
    ctClass.removeMethod(origMethod);
  }
 catch (  org.hotswap.agent.javassist.NotFoundException ex) {
  }
  CtConstructor clinit=ctClass.getClassInitializer();
  if (clinit != null) {
    LOGGER.debug("Adding " + HOTSWAP_AGENT_CLINIT_METHOD + " to class: {}",className);
    CtConstructor haClinit=new CtConstructor(clinit,ctClass,null);
    haClinit.getMethodInfo().setName(HOTSWAP_AGENT_CLINIT_METHOD);
    haClinit.setModifiers(Modifier.PUBLIC | Modifier.STATIC);
    ctClass.addConstructor(haClinit);
    final boolean reinitializeStatics[]=new boolean[]{false};
    haClinit.instrument(new ExprEditor(){
      public void edit(      FieldAccess f) throws CannotCompileException {
        try {
          if (f.isStatic() && f.isWriter()) {
            Field originalField=null;
            try {
              originalField=originalClass.getDeclaredField(f.getFieldName());
            }
 catch (            NoSuchFieldException e) {
              LOGGER.debug("New field will be initialized {}",f.getFieldName());
              reinitializeStatics[0]=true;
            }
            if (originalField != null) {
              if (originalClass.isEnum() && "ENUM$VALUES".equals(f.getFieldName())) {
                if (reinitializeStatics[0]) {
                  LOGGER.debug("New field will be initialized {}",f.getFieldName());
                }
 else {
                  reinitializeStatics[0]=checkOldEnumValues(ctClass,originalClass);
                }
              }
 else {
                LOGGER.debug("Skipping old field {}",f.getFieldName());
                f.replace("{}");
              }
            }
          }
        }
 catch (        Exception e) {
          LOGGER.error("Patching " + HOTSWAP_AGENT_CLINIT_METHOD + " method failed.",e);
        }
      }
    }
);
    if (reinitializeStatics[0]) {
      PluginManager.getInstance().getScheduler().scheduleCommand(new Command(){
        @Override public void executeCommand(){
          try {
            Class<?> clazz=classLoader.loadClass(className);
            Method m=clazz.getDeclaredMethod(HOTSWAP_AGENT_CLINIT_METHOD,new Class[]{});
            if (m != null) {
              m.setAccessible(true);
              m.invoke(null,new Object[]{});
            }
          }
 catch (          Exception e) {
            LOGGER.error("Error initializing redefined class {}",e,className);
          }
 finally {
            reloadFlag=false;
          }
        }
      }
,150);
    }
 else {
      reloadFlag=false;
    }
  }
}
