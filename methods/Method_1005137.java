/** 
 * ???????
 */
public List<TreeGrid> treegrid(List<?> all,TreeGridModel treeGridModel){
  List<TreeGrid> treegrid=new ArrayList<TreeGrid>();
  for (  Object obj : all) {
    ReflectHelper reflectHelper=new ReflectHelper(obj);
    TreeGrid tg=new TreeGrid();
    String id=oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getIdField()));
    String src=oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getSrc()));
    String text=oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getTextField()));
    if (StringUtils.isNotEmpty(treeGridModel.getOrder())) {
      String order=oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getOrder()));
      tg.setOrder(order);
    }
    tg.setId(id);
    if (treeGridModel.getIcon() != null) {
      String iconpath=TagUtil.fieldNametoValues(treeGridModel.getIcon(),obj).toString();
      if (iconpath != null) {
        tg.setCode(iconpath);
      }
 else {
        tg.setCode("");
      }
    }
    tg.setSrc(src);
    tg.setText(text);
    if (treeGridModel.getParentId() != null) {
      Object pid=TagUtil.fieldNametoValues(treeGridModel.getParentId(),obj);
      if (pid != null) {
        tg.setParentId(pid.toString());
      }
 else {
        tg.setParentId("");
      }
    }
    if (treeGridModel.getParentText() != null) {
      Object ptext=TagUtil.fieldNametoValues(treeGridModel.getTextField(),obj);
      if (ptext != null) {
        tg.setParentText(ptext.toString());
      }
 else {
        tg.setParentText("");
      }
    }
    List childList=(List)reflectHelper.getMethodValue(treeGridModel.getChildList());
    if (childList != null && childList.size() > 0) {
      tg.setState("closed");
    }
    if (treeGridModel.getRoleid() != null) {
      String[] opStrings={};
      List<TSRoleFunction> roleFunctions=findByProperty(TSRoleFunction.class,"TSFunction.id",id);
      if (roleFunctions.size() > 0) {
        for (        TSRoleFunction tRoleFunction : roleFunctions) {
          TSRoleFunction roleFunction=tRoleFunction;
          if (roleFunction.getTSRole().getId().toString().equals(treeGridModel.getRoleid())) {
            String bbString=roleFunction.getOperation();
            if (bbString != null) {
              opStrings=bbString.split(",");
              break;
            }
          }
        }
      }
      List<TSOperation> operateions=findByProperty(TSOperation.class,"TSFunction.id",id);
      StringBuffer attributes=new StringBuffer();
      if (operateions.size() > 0) {
        for (        TSOperation tOperation : operateions) {
          if (opStrings.length < 1) {
            attributes.append("<input type=checkbox name=operatons value=" + tOperation.getId() + "_" + id + ">" + tOperation.getOperationname());
          }
 else {
            StringBuffer sb=new StringBuffer();
            sb.append("<input type=checkbox name=operatons");
            for (int i=0; i < opStrings.length; i++) {
              if (opStrings[i].equals(tOperation.getId().toString())) {
                sb.append(" checked=checked");
              }
            }
            sb.append(" value=" + tOperation.getId() + "_" + id + ">" + tOperation.getOperationname());
            attributes.append(sb.toString());
          }
        }
      }
      tg.setOperations(attributes.toString());
    }
    if (treeGridModel.getFieldMap() != null) {
      tg.setFieldMap(new HashMap<String,Object>());
      for (      Map.Entry<String,Object> entry : treeGridModel.getFieldMap().entrySet()) {
        Object fieldValue=reflectHelper.getMethodValue(entry.getValue().toString());
        tg.getFieldMap().put(entry.getKey(),fieldValue);
      }
    }
    if (treeGridModel.getFunctionType() != null) {
      String functionType=oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getFunctionType()));
      tg.setFunctionType(functionType);
    }
    if (treeGridModel.getIconStyle() != null) {
      String iconStyle=oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getIconStyle()));
      tg.setIconStyle(iconStyle);
    }
    treegrid.add(tg);
  }
  return treegrid;
}
