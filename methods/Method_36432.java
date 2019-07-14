public Object newInstance(Context ctx,Map<String,Object> map,String keyPrefix) throws Exception {
  Object ob=klass.newInstance();
  ctx.push(ob);
  for (  XAnnotatedMember member : members) {
    member.process(ctx,map,keyPrefix);
  }
  return ctx.pop();
}
