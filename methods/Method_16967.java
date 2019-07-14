private NodeSelectorCode selector(){
  block.beginControlFlow("try").addStatement("$T<?> clazz = $T.class.getClassLoader().loadClass(sb.toString())",Class.class,NODE_FACTORY.rawType).add("@SuppressWarnings($S)\n","unchecked").addStatement("$1T factory = ($1T) clazz.getDeclaredConstructor().newInstance()",NODE_FACTORY).addStatement("return factory").nextControlFlow("catch ($T e)",ReflectiveOperationException.class).addStatement("throw new $T(sb.toString(), e)",IllegalStateException.class).endControlFlow();
  return this;
}
