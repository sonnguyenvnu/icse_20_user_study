@Override public Type make(API api,Container.Entry entry,String fragment){
  URI key=entry.getUri();
  if (cache.containsKey(key)) {
    return cache.get(key);
  }
 else {
    JavaType type;
    try (InputStream is=entry.getInputStream()){
      ClassReader classReader=new ClassReader(is);
      if ((fragment != null) && (fragment.length() > 0)) {
        int index=fragment.indexOf('-');
        if (index != -1) {
          fragment=fragment.substring(0,index);
        }
        if (!classReader.getClassName().equals(fragment)) {
          String entryTypePath=classReader.getClassName() + ".class";
          String fragmentTypePath=fragment + ".class";
          while (true) {
            if (entry.getPath().endsWith(entryTypePath)) {
              String pathToFound=entry.getPath().substring(0,entry.getPath().length() - entryTypePath.length()) + fragmentTypePath;
              Container.Entry entryFound=null;
              for (              Container.Entry e : entry.getParent().getChildren()) {
                if (e.getPath().equals(pathToFound)) {
                  entryFound=e;
                  break;
                }
              }
              if (entryFound == null)               return null;
              entry=entryFound;
              try (InputStream is2=entry.getInputStream()){
                classReader=new ClassReader(is2);
              }
 catch (              IOException e) {
                assert ExceptionUtil.printStackTrace(e);
                return null;
              }
              break;
            }
            int firstPackageSeparatorIndex=entryTypePath.indexOf('/');
            if (firstPackageSeparatorIndex == -1) {
              return null;
            }
            entryTypePath=entryTypePath.substring(firstPackageSeparatorIndex + 1);
            fragmentTypePath=fragmentTypePath.substring(fragmentTypePath.indexOf('/') + 1);
          }
        }
      }
      type=new JavaType(entry,classReader,-1);
    }
 catch (    IOException e) {
      assert ExceptionUtil.printStackTrace(e);
      type=null;
    }
    cache.put(key,type);
    return type;
  }
}
