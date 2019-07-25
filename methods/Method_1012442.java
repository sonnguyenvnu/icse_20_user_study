@Override public List<MenuVO> routes(String roleId){
  List<Menu> allMenus=baseMapper.allMenu();
  List<Menu> roleMenus=baseMapper.roleMenu(Func.toIntList(roleId));
  List<Menu> routes=new LinkedList<>(roleMenus);
  roleMenus.forEach(roleMenu -> recursion(allMenus,routes,roleMenu));
  routes.sort(Comparator.comparing(Menu::getSort));
  MenuWrapper menuWrapper=new MenuWrapper();
  List<Menu> collect=routes.stream().filter(x -> Func.equals(x.getCategory(),1)).collect(Collectors.toList());
  return menuWrapper.listNodeVO(collect);
}
