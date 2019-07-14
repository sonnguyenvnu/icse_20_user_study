public static IContentProvider newInstance(Context context,IContentProvider iContentProvider){
  return (IContentProvider)Proxy.newProxyInstance(iContentProvider.getClass().getClassLoader(),new Class[]{IContentProvider.class},new IContentProviderProxy(context,iContentProvider));
}
