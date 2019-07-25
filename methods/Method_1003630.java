@Override public void render(Context ctx,Path targetFile) throws Exception {
  readAttributes(targetFile,cacheMetadata,attributes -> {
    if (attributes == null || !attributes.isRegularFile()) {
      ctx.clientError(404);
    }
 else {
      sendFile(ctx,targetFile,attributes);
    }
  }
);
}
