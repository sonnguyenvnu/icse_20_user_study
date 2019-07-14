/** 
 * Returns all component types of the given type. For example the following types all have the component-type MyClass: <ul> <li>MyClass[]</li> <li>List&lt;MyClass&gt;</li> <li>Foo&lt;? extends MyClass&gt;</li> <li>Bar&lt;? super MyClass&gt;</li> <li>&lt;T extends MyClass&gt; T[]</li> </ul>
 */
public static Class[] getComponentTypes(final Type type,final Class implClass){
  if (type instanceof Class) {
    Class clazz=(Class)type;
    if (clazz.isArray()) {
      return new Class[]{clazz.getComponentType()};
    }
  }
 else   if (type instanceof ParameterizedType) {
    ParameterizedType pt=(ParameterizedType)type;
    Type[] generics=pt.getActualTypeArguments();
    if (generics.length == 0) {
      return null;
    }
    Class[] types=new Class[generics.length];
    for (int i=0; i < generics.length; i++) {
      types[i]=getRawType(generics[i],implClass);
    }
    return types;
  }
 else   if (type instanceof GenericArrayType) {
    GenericArrayType gat=(GenericArrayType)type;
    Class rawType=getRawType(gat.getGenericComponentType(),implClass);
    if (rawType == null) {
      return null;
    }
    return new Class[]{rawType};
  }
  return null;
}
