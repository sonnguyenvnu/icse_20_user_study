public void say(){
  if (null != list && 0 != list.size()) {
    System.out.println("list...");
    for (    BeanInterface bean : list) {
      System.out.println(bean.getClass().getName());
    }
  }
 else {
    System.out.println("List<BeanInterface> list is null !!!!!!!!!!");
  }
  System.out.println();
  if (null != map && 0 != map.size()) {
    System.out.println("map...");
    for (    Map.Entry<String,BeanInterface> entry : map.entrySet()) {
      System.out.println(entry.getKey() + "      " + entry.getValue().getClass().getName());
    }
  }
 else {
    System.out.println("Map<String, BeanInterface> map is null !!!!!!!!!!");
  }
  System.out.println();
  if (null != beanInterface) {
    System.out.println(beanInterface.getClass().getName());
  }
 else {
    System.out.println("beanInterface is null...");
  }
}
