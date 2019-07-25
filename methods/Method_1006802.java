protected long create(Object obj,Connection conn){
  Class clz=obj.getClass();
  long id=-1;
  PreparedStatement pstmt=null;
  try {
    String sql=MapperFactory.getSql(clz,Mapper.CREATE);
    Parsed parsed=Parser.get(clz);
    List<BeanElement> eles=parsed.getBeanElementList();
    Long keyOneValue=0L;
    Field keyOneField=parsed.getKeyField(X.KEY_ONE);
    if (Objects.isNull(keyOneField))     throw new PersistenceException("No setting of PrimaryKey by @X.Key");
    Class keyOneType=keyOneField.getType();
    if (keyOneType == String.class) {
      keyOneValue=1L;
    }
 else {
      keyOneValue=keyOneField.getLong(obj);
    }
    if (keyOneType != String.class && (keyOneValue == null || keyOneValue == 0)) {
      pstmt=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    }
 else {
      pstmt=conn.prepareStatement(sql);
    }
    int i=1;
    for (    BeanElement ele : eles) {
      Object value=ele.getMethod.invoke(obj);
      if (value == null) {
        if (ele.clz.isEnum())         throw new PersistenceException("ENUM CAN NOT NULL, property:" + clz.getName() + SqlScript.POINT + ele.getProperty());
        if (ele.clz == Boolean.class || ele.clz == Integer.class || ele.clz == Long.class || ele.clz == Double.class || ele.clz == Float.class || ele.clz == BigDecimal.class || ele.clz == Byte.class || ele.clz == Short.class)         value=0;
        pstmt.setObject(i++,value);
      }
 else {
        if (ele.isJson) {
          String str=JsonX.toJson(value);
          this.dialect.setJSON(i++,str,pstmt);
        }
 else         if (ele.clz.isEnum()) {
          String str=value.toString();
          pstmt.setObject(i++,str);
        }
 else {
          value=this.dialect.filterValue(value);
          pstmt.setObject(i++,value);
        }
      }
    }
    pstmt.execute();
    if (keyOneType != String.class && (keyOneValue == null || keyOneValue == 0)) {
      ResultSet rs=pstmt.getGeneratedKeys();
      if (rs.next()) {
        id=rs.getLong(1);
      }
    }
 else {
      id=keyOneValue;
    }
  }
 catch (  Exception e) {
    System.out.println("Exception occured, while create: " + obj);
    throw new RollbackException("RollbackException occoured: " + e.getMessage() + ", while create " + obj);
  }
 finally {
    close(pstmt);
    DataSourceUtil.releaseConnection(conn);
  }
  return id;
}
