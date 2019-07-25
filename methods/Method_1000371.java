public <T>Entity<T> make(Class<T> type){
  NutEntity<T> en=_createNutEntity(type);
  TableInfo ti=_createTableInfo(type);
  if (null != expert.getConf()) {
    for (    String key : expert.getConf().keySet())     en.getMetas().put(key,expert.getConf().get(key));
  }
  if (null != ti.annMeta) {
    Map<String,Object> map=Lang.map(ti.annMeta.value());
    for (    Entry<String,Object> entry : map.entrySet()) {
      en.getMetas().put(entry.getKey(),entry.getValue().toString());
    }
  }
  String tableName=null;
  if (null == ti.annTable) {
    tableName=Daos.getTableNameMaker().make(type);
    if (null == ti.annView)     log.warnf("No @Table found, fallback to use table name='%s' for type '%s'",tableName,type.getName());
  }
 else {
    tableName=ti.annTable.value().isEmpty() ? Daos.getTableNameMaker().make(type) : ti.annTable.value();
    if (!ti.annTable.prefix().isEmpty()) {
      tableName=ti.annTable.prefix() + tableName;
    }
    if (!ti.annTable.suffix().isEmpty()) {
      tableName=tableName + ti.annTable.suffix();
    }
  }
  String viewName=null;
  if (null == ti.annView) {
    viewName=tableName;
  }
 else {
    viewName=ti.annView.value().isEmpty() ? Daos.getViewNameMaker().make(type) : ti.annView.value();
    if (!ti.annView.prefix().isEmpty()) {
      viewName=ti.annView.prefix() + viewName;
    }
    if (!ti.annView.suffix().isEmpty()) {
      viewName=viewName + ti.annView.suffix();
    }
  }
  en.setTableName(tableName);
  en.setViewName(viewName);
  boolean hasTableComment=null != ti.tableComment;
  String tableComment=hasTableComment ? Strings.isBlank(ti.tableComment.value()) ? type.getName() : ti.tableComment.value() : null;
  en.setHasTableComment(hasTableComment);
  en.setTableComment(tableComment);
  boolean shouldUseColumn=false;
  boolean hasColumnComment=false;
  for (  Field field : en.getMirror().getFields()) {
    if (shouldUseColumn && hasColumnComment) {
      break;
    }
    if (!shouldUseColumn && null != field.getAnnotation(Column.class)) {
      shouldUseColumn=true;
    }
    if (!hasColumnComment && null != field.getAnnotation(Comment.class)) {
      hasColumnComment=true;
    }
  }
  en.setHasColumnComment(hasColumnComment);
  List<MappingInfo> infos=new ArrayList<MappingInfo>();
  List<LinkInfo> ones=new ArrayList<LinkInfo>();
  List<LinkInfo> manys=new ArrayList<LinkInfo>();
  List<LinkInfo> manymanys=new ArrayList<LinkInfo>();
  String[] _tmp=ti.annPK == null ? null : ti.annPK.value();
  List<String> pks=_tmp == null ? new ArrayList<String>() : Arrays.asList(_tmp);
  for (  Field field : en.getMirror().getFields()) {
    if (null != field.getAnnotation(One.class)) {
      ones.add(_Infos.createLinkInfo(field));
    }
 else     if (null != field.getAnnotation(Many.class)) {
      manys.add(_Infos.createLinkInfo(field));
    }
 else     if (null != field.getAnnotation(ManyMany.class)) {
      manymanys.add(_Infos.createLinkInfo(field));
    }
 else     if ((Modifier.isTransient(field.getModifiers()) && null == field.getAnnotation(Column.class)) || (shouldUseColumn && (null == field.getAnnotation(Column.class) && null == field.getAnnotation(Id.class) && null == field.getAnnotation(Name.class))) && !pks.contains(field.getName())) {
      continue;
    }
 else {
      infos.add(_Infos.createMappingInfo(ti.annPK,field));
    }
  }
  for (  Method method : en.getType().getMethods()) {
    if (null != method.getAnnotation(One.class)) {
      ones.add(_Infos.createLinkInfo(method));
    }
 else     if (null != method.getAnnotation(Many.class)) {
      manys.add(_Infos.createLinkInfo(method));
    }
 else     if (null != method.getAnnotation(ManyMany.class)) {
      manymanys.add(_Infos.createLinkInfo(method));
    }
 else     if (null == method.getAnnotation(Column.class) && null == method.getAnnotation(Id.class) && null == method.getAnnotation(Name.class)) {
      continue;
    }
 else {
      infos.add(_Infos.createMapingInfo(ti.annPK,method));
    }
  }
  List<MappingInfo> tmp=new ArrayList<MappingInfo>(infos.size());
  MappingInfo miId=null;
  MappingInfo miName=null;
  MappingInfo miVersion=null;
  for (  MappingInfo mi : infos) {
    if (mi.annId != null) {
      if (miId != null) {
        throw new DaoException("Allows only a single @Id ! " + type);
      }
      miId=mi;
    }
 else     if (mi.annName != null) {
      if (miName != null) {
        throw new DaoException("Allows only a single @Name ! " + type);
      }
      miName=mi;
    }
 else {
      if (mi.annColumn != null && mi.annColumn.version()) {
        if (miVersion != null) {
          throw new DaoException("Allows only a single @Version ! " + type);
        }
        miVersion=mi;
      }
      tmp.add(mi);
    }
  }
  if (miName != null)   tmp.add(0,miName);
  if (miId != null)   tmp.add(0,miId);
  infos=tmp;
  if (infos.isEmpty())   throw Lang.makeThrow(IllegalArgumentException.class,"Pojo(%s) without any Mapping Field!!",type);
  for (  MappingInfo info : infos) {
    NutMappingField ef=new NutMappingField(en);
    _evalMappingField(ef,info);
    en.addMappingField(ef);
  }
  holder.set(en);
  try {
    for (    LinkInfo li : ones) {
      en.addLinkField(new OneLinkField(en,holder,li));
    }
    for (    LinkInfo li : manys) {
      en.addLinkField(new ManyLinkField(en,holder,li));
    }
    for (    LinkInfo li : manymanys) {
      en.addLinkField(new ManyManyLinkField(en,holder,li));
    }
    en.checkCompositeFields(null == ti.annPK ? null : ti.annPK.value());
    if (null != datasource && null != expert) {
      _checkupEntityFieldsWithDatabase(en);
    }
    _evalFieldMacro(en,infos);
    if (null != ti.annIndexes)     _evalEntityIndexes(en,ti.annIndexes);
  }
 catch (  RuntimeException e) {
    holder.remove(en);
    throw e;
  }
catch (  Throwable e) {
    holder.remove(en);
    throw Lang.wrapThrow(e);
  }
  if (NutConf.DAO_USE_POJO_INTERCEPTOR && ti.annTable != null) {
    PojoInterceptor pint=Mirror.me(ti.annTable.interceptor()).born();
    pint.setupEntity(en,expert);
    en.setInterceptor(pint);
  }
  en.setComplete(true);
  return en;
}
