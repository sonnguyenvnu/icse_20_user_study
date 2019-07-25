public static IntermediateModelsCache load(ModelCacheContainer cacheContainer){
  try {
    ModelInputStream is=new ModelInputStream(cacheContainer.openStream(STEPS));
    List<Integer> steps;
    try {
      int count=is.readInt();
      if (count > 10000) {
        throw new IOException("illegal data");
      }
      steps=new ArrayList<>(count);
      for (int i=0; i < count; i++) {
        steps.add(is.readInt());
      }
    }
  finally {
      is.close();
    }
    InputStreamReader reader=new InputStreamReader(cacheContainer.openStream(SIGNATURE),FileUtil.DEFAULT_CHARSET);
    try {
      StringBuilder signature=new StringBuilder();
      char[] buff=new char[512];
      int size;
      while ((size=reader.read(buff)) > 0) {
        signature.append(buff,0,size);
      }
      IntermediateModelsCache result=new IntermediateModelsCache(cacheContainer,signature.toString());
      result.setSteps(steps);
      return result;
    }
  finally {
      reader.close();
    }
  }
 catch (  IOException e) {
  }
  return null;
}
