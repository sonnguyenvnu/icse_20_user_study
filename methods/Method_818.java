@SuppressWarnings({"rawtypes","unchecked"}) public static Collection createCollection(Type type){
  Class<?> rawClass=getRawClass(type);
  Collection list;
  if (rawClass == AbstractCollection.class || rawClass == Collection.class) {
    list=new ArrayList();
  }
 else   if (rawClass.isAssignableFrom(HashSet.class)) {
    list=new HashSet();
  }
 else   if (rawClass.isAssignableFrom(LinkedHashSet.class)) {
    list=new LinkedHashSet();
  }
 else   if (rawClass.isAssignableFrom(TreeSet.class)) {
    list=new TreeSet();
  }
 else   if (rawClass.isAssignableFrom(ArrayList.class)) {
    list=new ArrayList();
  }
 else   if (rawClass.isAssignableFrom(EnumSet.class)) {
    Type itemType;
    if (type instanceof ParameterizedType) {
      itemType=((ParameterizedType)type).getActualTypeArguments()[0];
    }
 else {
      itemType=Object.class;
    }
    list=EnumSet.noneOf((Class<Enum>)itemType);
  }
 else   if (rawClass.isAssignableFrom(Queue.class)) {
    list=new LinkedList();
  }
 else {
    try {
      list=(Collection)rawClass.newInstance();
    }
 catch (    Exception e) {
      throw new JSONException("create instance error, class " + rawClass.getName());
    }
  }
  return list;
}
