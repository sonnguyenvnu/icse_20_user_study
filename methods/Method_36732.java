@Override public PojoGroupBasicAdapter newAdapter(@NonNull final Context context,@NonNull final VirtualLayoutManager layoutManager,@NonNull final ServiceManager serviceManager){
  BaseCellBinderResolver componentBinderResolver=serviceManager.getService(BaseCellBinderResolver.class);
  BaseCardBinderResolver cardBinderResolver=serviceManager.getService(BaseCardBinderResolver.class);
  MVHelper mvHelper=serviceManager.getService(MVHelper.class);
  ViewManager viewManager=serviceManager.getService(ViewManager.class);
  final PojoGroupBasicAdapter adapter=new PojoGroupBasicAdapter(context,layoutManager,componentBinderResolver,cardBinderResolver,mvHelper,viewManager);
  return adapter;
}
