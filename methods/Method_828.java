public static String[] getKoltinConstructorParameters(Class clazz){
  if (kotlin_kclass_constructor == null && !kotlin_class_klass_error) {
    try {
      Class class_kotlin_kclass=Class.forName("kotlin.reflect.jvm.internal.KClassImpl");
      kotlin_kclass_constructor=class_kotlin_kclass.getConstructor(Class.class);
    }
 catch (    Throwable e) {
      kotlin_class_klass_error=true;
    }
  }
  if (kotlin_kclass_constructor == null) {
    return null;
  }
  if (kotlin_kclass_getConstructors == null && !kotlin_class_klass_error) {
    try {
      Class class_kotlin_kclass=Class.forName("kotlin.reflect.jvm.internal.KClassImpl");
      kotlin_kclass_getConstructors=class_kotlin_kclass.getMethod("getConstructors");
    }
 catch (    Throwable e) {
      kotlin_class_klass_error=true;
    }
  }
  if (kotlin_kfunction_getParameters == null && !kotlin_class_klass_error) {
    try {
      Class class_kotlin_kfunction=Class.forName("kotlin.reflect.KFunction");
      kotlin_kfunction_getParameters=class_kotlin_kfunction.getMethod("getParameters");
    }
 catch (    Throwable e) {
      kotlin_class_klass_error=true;
    }
  }
  if (kotlin_kparameter_getName == null && !kotlin_class_klass_error) {
    try {
      Class class_kotlinn_kparameter=Class.forName("kotlin.reflect.KParameter");
      kotlin_kparameter_getName=class_kotlinn_kparameter.getMethod("getName");
    }
 catch (    Throwable e) {
      kotlin_class_klass_error=true;
    }
  }
  if (kotlin_error) {
    return null;
  }
  try {
    Object constructor=null;
    Object kclassImpl=kotlin_kclass_constructor.newInstance(clazz);
    Iterable it=(Iterable)kotlin_kclass_getConstructors.invoke(kclassImpl);
    for (Iterator iterator=it.iterator(); iterator.hasNext(); iterator.hasNext()) {
      Object item=iterator.next();
      List parameters=(List)kotlin_kfunction_getParameters.invoke(item);
      if (constructor != null && parameters.size() == 0) {
        continue;
      }
      constructor=item;
    }
    List parameters=(List)kotlin_kfunction_getParameters.invoke(constructor);
    String[] names=new String[parameters.size()];
    for (int i=0; i < parameters.size(); i++) {
      Object param=parameters.get(i);
      names[i]=(String)kotlin_kparameter_getName.invoke(param);
    }
    return names;
  }
 catch (  Throwable e) {
    e.printStackTrace();
    kotlin_error=true;
  }
  return null;
}
