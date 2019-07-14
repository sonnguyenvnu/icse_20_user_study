public static String[] getRenderers(){
  String[] result=RENDERERS.keySet().toArray(new String[RENDERERS.size()]);
  Arrays.sort(result);
  return result;
}
