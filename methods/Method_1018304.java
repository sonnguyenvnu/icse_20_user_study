/** 
 * ?????? http://www.mybatis.org/generator/reference/pluggingIn.html
 * @param introspectedTable
 */
@Override public void initialized(IntrospectedTable introspectedTable){
  super.initialized(introspectedTable);
  this.incColumns=new ArrayList<>();
  this.incEnum=null;
  this.incEnumBuilder=null;
  String incrementsColumns=introspectedTable.getTableConfigurationProperty(IncrementsPlugin.PRO_INCREMENTS_COLUMNS);
  if (StringUtility.stringHasValue(incrementsColumns)) {
    String[] incrementsColumnsStrs=incrementsColumns.split(",");
    for (    String incrementsColumnsStr : incrementsColumnsStrs) {
      IntrospectedColumn column=IntrospectedTableTools.safeGetColumn(introspectedTable,incrementsColumnsStr);
      if (column == null) {
        warnings.add("itfsw:??" + IncrementsPlugin.class.getTypeName() + "??????column?" + incrementsColumnsStr.trim() + "????");
      }
 else {
        incColumns.add(column);
      }
    }
  }
}
