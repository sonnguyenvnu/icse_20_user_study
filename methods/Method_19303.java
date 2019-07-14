private static void assertNotNullRenderInfo(RenderInfo renderInfo){
  if (renderInfo == null) {
    throw new RuntimeException("Received null RenderInfo to insert/update!");
  }
}
