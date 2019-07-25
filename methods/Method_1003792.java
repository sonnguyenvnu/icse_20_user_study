private CompiledTextTemplate compile(TextTemplateSource templateSource){
  ByteBuf content;
  try {
    content=templateSource.getContent();
  }
 catch (  IOException e) {
    throw new UncheckedIOException(e);
  }
  try {
    return templateCompiler.compile(content,templateSource.getName());
  }
 catch (  IOException e) {
    throw new UncheckedIOException(e);
  }
 finally {
    content.release();
  }
}
