@Override public void onAddedLoadedPlugin(LoadedPlugin plugin){
  try {
    String clsName="android.databinding.DataBinderMapper_" + plugin.getPackageName().replace('.','_');
    Log.d(TAG,"Try to find the class: " + clsName);
    Class cls=Class.forName(clsName,true,plugin.getClassLoader());
    Object obj=cls.newInstance();
    addMapper((DataBinderMapper)obj);
  }
 catch (  Exception e) {
    Log.w(TAG,e);
  }
}
