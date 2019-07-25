@Override public JsonEntityField make(final Mirror<?> mirror,final Method method){
  final JsonField jf=method.getAnnotation(JsonField.class);
  if (null == jf || jf.ignore())   return null;
  final JsonEntityField[] result=new JsonEntityField[1];
  Callback<Method> whenError=new Callback<Method>(){
    public void invoke(    Method m){
      throw Lang.makeThrow(JsonException.class,"JsonField '%s' should be getter/setter pair!",m);
    }
  }
;
  Callback3<String,Method,Method> whenOk=new Callback3<String,Method,Method>(){
    public void invoke(    String name,    Method getter,    Method setter){
      if (null == getter || null == setter || Strings.isBlank(name)) {
        throw Lang.makeThrow(JsonException.class,"JsonField '%s' should be getter/setter pair!",method);
      }
      JsonEntityField ef=JsonEntityField.eval(mirror,Strings.sBlank(jf.value(),name),getter,setter);
      result[0]=ef;
    }
  }
;
  Mirror.evalGetterSetter(method,whenOk,whenError);
  return result[0];
}
