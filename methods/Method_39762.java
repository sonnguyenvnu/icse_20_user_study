/** 
 * Matches pointcuts on method. If no pointcut found, returns <code>null</code>.
 */
protected List<ProxyAspectData> matchMethodPointcuts(final MethodSignatureVisitor msign){
  List<ProxyAspectData> aspectList=null;
  for (  ProxyAspectData aspectData : wd.proxyAspects) {
    if (aspectData.apply(msign)) {
      if (aspectList == null) {
        aspectList=new ArrayList<>(wd.proxyAspects.length);
      }
      aspectList.add(aspectData);
    }
  }
  return aspectList;
}
