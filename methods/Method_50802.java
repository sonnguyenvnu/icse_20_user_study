private static String[] validRendererCodes(){
  return RendererFactory.REPORT_FORMAT_TO_RENDERER.keySet().toArray(new String[RendererFactory.REPORT_FORMAT_TO_RENDERER.size()]);
}
