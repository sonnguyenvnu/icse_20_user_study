public static List<MenuNode> build(List<MenuNode> nodes){
  GunsProperties gunsProperties=SpringContextHolder.getBean(GunsProperties.class);
  if (!gunsProperties.getSwaggerOpen()) {
    List<MenuNode> menuNodesCopy=new ArrayList<>();
    for (    MenuNode menuNode : nodes) {
      if (Const.API_MENU_NAME.equals(menuNode.getName())) {
        continue;
      }
 else {
        menuNodesCopy.add(menuNode);
      }
    }
    nodes=menuNodesCopy;
  }
  return nodes;
}
