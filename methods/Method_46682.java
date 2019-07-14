@Override public ListAppMods listAppMods(Integer page,Integer limit){
  if (Objects.isNull(limit) || limit < 1) {
    limit=10;
  }
  if (Objects.isNull(page) || page < 1) {
    page=1;
  }
  List<ListAppMods.AppMod> appMods=new ArrayList<>(limit);
  int firIdx=(page - 1) * limit;
  List<AppInfo> apps=rpcClient.apps();
  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  for (int i=0; i < apps.size(); i++) {
    if (firIdx > apps.size() - 1) {
      break;
    }
    if (i < firIdx) {
      continue;
    }
    AppInfo appInfo=apps.get(i);
    ListAppMods.AppMod appMod=new ListAppMods.AppMod();
    PropertyMapper.get().from(appInfo::getAppName).to(appMod::setModName);
    PropertyMapper.get().from(appInfo::getLabelName).to(appMod::setModId);
    PropertyMapper.get().from(appInfo::getCreateTime).to(t -> appMod.setRegisterTime(dateFormat.format(t)));
    appMods.add(appMod);
  }
  ListAppMods listAppMods=new ListAppMods();
  listAppMods.setTotal(apps.size());
  listAppMods.setAppMods(appMods);
  return listAppMods;
}
