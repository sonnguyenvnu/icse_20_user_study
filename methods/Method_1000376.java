private static <T extends FieldInfo>T create(Class<T> classOfT,final Method method){
  final T info=Mirror.me(classOfT).born();
  Mirror.evalGetterSetter(method,ERR_MSG,new Callback3<String,Method,Method>(){
    public void invoke(    String name,    Method getter,    Method setter){
      if (null == getter) {
        throw Lang.makeThrow("Method '%s'(%s) has '@Column', but NO setter!",method.getName(),method.getDeclaringClass().getName());
      }
      if (null == setter) {
        throw Lang.makeThrow("Method '%s'(%s) has '@Column', but NO setter!",method.getName(),method.getDeclaringClass().getName());
      }
      info.name=name;
      info.fieldType=getter.getGenericReturnType();
      info.ejecting=new EjectByGetter(getter);
      info.injecting=new InjectBySetter(setter);
    }
  }
);
  return info;
}
