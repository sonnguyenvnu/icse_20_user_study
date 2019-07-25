public void recursion(List<Menu> allMenus,List<Menu> routes,Menu roleMenu){
  Optional<Menu> menu=allMenus.stream().filter(x -> Func.equals(x.getId(),roleMenu.getParentId())).findFirst();
  if (menu.isPresent() && !routes.contains(menu.get())) {
    routes.add(menu.get());
    recursion(allMenus,routes,menu.get());
  }
}
