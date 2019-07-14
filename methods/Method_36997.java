public void onDownloadTemplate(){
  List<ComponentInfo> componentInfoList1=null;
  List<ComponentInfo> componentInfoList2=null;
  List<ComponentInfo> componentInfoList3=null;
  List<ComponentInfo> componentInfoList4=null;
  String renderService1=null;
  String renderService2=null;
  String renderService3=null;
  String renderService4=null;
  Set<String> renderSet=renderServiceMap.keySet();
  int i=0;
  for (Iterator<String> iterator=renderSet.iterator(); iterator.hasNext(); ) {
    String renderService=iterator.next();
    if (i == 0) {
      renderService1=renderService;
    }
 else     if (i == 1) {
      renderService2=renderService;
    }
 else     if (i == 2) {
      renderService3=renderService;
    }
 else     if (i == 3) {
      renderService4=renderService;
    }
    i++;
  }
  for (  ComponentInfo componentInfo : componentInfoMap.values()) {
    if (!TextUtils.isEmpty(renderService1) && renderService1.equals(componentInfo.getType())) {
      if (componentInfoList1 == null) {
        componentInfoList1=new ArrayList<>();
      }
      componentInfoList1.add(componentInfo);
    }
 else     if (!TextUtils.isEmpty(renderService2) && renderService2.equals(componentInfo.getType())) {
      if (componentInfoList2 == null) {
        componentInfoList2=new ArrayList<>();
      }
      componentInfoList2.add(componentInfo);
    }
 else     if (!TextUtils.isEmpty(renderService3) && renderService3.equals(componentInfo.getType())) {
      if (componentInfoList3 == null) {
        componentInfoList3=new ArrayList<>();
      }
      componentInfoList3.add(componentInfo);
    }
 else     if (!TextUtils.isEmpty(renderService4) && renderService4.equals(componentInfo.getType())) {
      if (componentInfoList4 == null) {
        componentInfoList4=new ArrayList<>();
      }
      componentInfoList4.add(componentInfo);
    }
 else {
      Log.e("tangram","we consider the count of render service in one page should not more than 4!");
    }
  }
  if (!TextUtils.isEmpty(renderService1)) {
    renderServiceMap.get(renderService1).onDownloadComponentInfo(componentInfoList1);
  }
  if (!TextUtils.isEmpty(renderService2)) {
    renderServiceMap.get(renderService2).onDownloadComponentInfo(componentInfoList2);
  }
  if (!TextUtils.isEmpty(renderService3)) {
    renderServiceMap.get(renderService3).onDownloadComponentInfo(componentInfoList3);
  }
  if (!TextUtils.isEmpty(renderService4)) {
    renderServiceMap.get(renderService4).onDownloadComponentInfo(componentInfoList4);
  }
}
