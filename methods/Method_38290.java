/** 
 * Resolves and registers table references.
 */
@Override public void init(final TemplateData templateData){
  super.init(templateData);
  if (entity != null) {
    ded=lookupType(entity);
  }
 else {
    Object object=templateData.getObjectReference(entityName);
    if (object != null) {
      ded=lookupType(resolveClass(object));
    }
 else {
      ded=lookupName(entityName);
    }
  }
  String tableReference=this.tableReference;
  if (tableReference == null) {
    tableReference=tableAlias;
  }
  if (tableReference == null) {
    tableReference=entityName;
  }
  if (tableReference == null) {
    tableReference=ded.getEntityName();
  }
  templateData.registerTableReference(tableReference,ded,tableAlias);
}
