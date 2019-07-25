public V8ArrayWrapper push(Object object){
  if (object instanceof ReflectiveObjectWrapper) {
    ReflectiveObjectWrapper objectWrapper=(ReflectiveObjectWrapper)object;
    object=objectWrapper.wrappedObj();
  }
  if (reflective().clazz(NodeJSWrapper.V8_VALUE_CLASS).isAssignableFrom(object.getClass())) {
    invoke("push",reflective().typed(NodeJSWrapper.V8_VALUE_CLASS,object));
  }
 else {
    invoke("push",object);
  }
  return this;
}
