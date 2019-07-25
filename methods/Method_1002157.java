@Override public void update(Menu resources){
  if (resources.getId().equals(resources.getPid())) {
    throw new BadRequestException("???????");
  }
  Optional<Menu> optionalPermission=menuRepository.findById(resources.getId());
  ValidationUtil.isNull(optionalPermission,"Permission","id",resources.getId());
  if (resources.getIFrame()) {
    if (!(resources.getPath().toLowerCase().startsWith("http://") || resources.getPath().toLowerCase().startsWith("https://"))) {
      throw new BadRequestException("?????http://??https://??");
    }
  }
  Menu menu=optionalPermission.get();
  Menu menu1=menuRepository.findByName(resources.getName());
  if (menu1 != null && !menu1.getId().equals(menu.getId())) {
    throw new EntityExistException(Menu.class,"name",resources.getName());
  }
  menu.setName(resources.getName());
  menu.setComponent(resources.getComponent());
  menu.setPath(resources.getPath());
  menu.setIcon(resources.getIcon());
  menu.setIFrame(resources.getIFrame());
  menu.setPid(resources.getPid());
  menu.setSort(resources.getSort());
  menuRepository.save(menu);
}
