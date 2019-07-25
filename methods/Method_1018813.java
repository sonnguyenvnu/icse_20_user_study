/** 
 * ?? packageName ? classname ?? FragmentProxy
 * @param proxyClass  FragmentProxy ????
 * @param packageName ????
 * @param className   ?? Fragment ??
 * @return FragmentProxy
 */
@NonNull public static AbstractFragmentProxy create(Class<? extends AbstractFragmentProxy> proxyClass,String packageName,String className){
  AbstractFragmentProxy fragment;
  if (proxyClass == null) {
    fragment=new DefaultFragmentProxy();
  }
 else {
    try {
      fragment=proxyClass.newInstance();
    }
 catch (    Throwable e) {
      ErrorUtil.throwErrorIfNeed(e);
      fragment=new DefaultFragmentProxy();
    }
  }
  Bundle bundle=new Bundle();
  bundle.putString(IntentConstant.EXTRA_TARGET_PACKAGE_KEY,packageName);
  bundle.putString(IntentConstant.EXTRA_TARGET_CLASS_KEY,className);
  fragment.setArguments(bundle);
  return fragment;
}
