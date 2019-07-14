final GifDrawable build(final GifDrawable oldDrawable,final ScheduledThreadPoolExecutor executor,final boolean isRenderingAlwaysEnabled,final GifOptions options) throws IOException {
  return new GifDrawable(createHandleWith(options),oldDrawable,executor,isRenderingAlwaysEnabled);
}
