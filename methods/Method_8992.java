public int[] getResourceDeclareStyleableIntArray(String packageName,String name){
  try {
    Field f=Class.forName(packageName + ".R$styleable").getField(name);
    if (f != null) {
      return (int[])f.get(null);
    }
  }
 catch (  Throwable t) {
  }
  return null;
}
