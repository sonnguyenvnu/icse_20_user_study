public Object convert(Object obj){
  StringBuilder sb=new StringBuilder();
  Writer writer=new StringWriter(sb);
  try {
    JsonRender jr;
    Class<? extends JsonRender> jrCls=getJsonRenderCls();
    if (jrCls == null)     jr=new JsonRenderImpl();
 else     jr=Mirror.me(jrCls).born();
    jr.setWriter(writer);
    jr.setFormat(format);
    jr.render(obj);
    writer.flush();
    return sb.toString();
  }
 catch (  IOException e) {
    throw Lang.wrapThrow(e,JsonException.class);
  }
}
