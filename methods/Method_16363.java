protected void customColumnSetting(RDBDatabase database,DynamicFormEntity formEntity,RDBTableMetaData table,DynamicFormColumnEntity columnEntity,RDBColumnMetaData column){
  ColumnInitializeContext context=new ColumnInitializeContext(){
    @Override public DynamicFormColumnEntity getColumnEntity(){
      return columnEntity;
    }
    @Override public RDBColumnMetaData getColumn(){
      return column;
    }
    @Override public RDBDatabase getDatabase(){
      return database;
    }
    @Override public DynamicFormEntity getFormEntity(){
      return formEntity;
    }
    @Override public RDBTableMetaData getTable(){
      return table;
    }
  }
;
  if (!CollectionUtils.isEmpty(initializeCustomizers)) {
    initializeCustomizers.forEach(customer -> customer.customTableColumnSetting(context));
  }
}
