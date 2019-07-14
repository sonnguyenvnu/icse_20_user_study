/** 
 * incomplete, do not use 
 */
public void parseInto(Object enclosingObject,String fieldName){
  Class<?> target=null;
  Object outgoing=null;
  Field targetField=null;
  try {
    Class<?> sketchClass=enclosingObject.getClass();
    targetField=sketchClass.getDeclaredField(fieldName);
    Class<?> targetArray=targetField.getType();
    if (!targetArray.isArray()) {
    }
 else {
      target=targetArray.getComponentType();
      outgoing=Array.newInstance(target,getRowCount());
    }
  }
 catch (  NoSuchFieldException e) {
    e.printStackTrace();
  }
catch (  SecurityException e) {
    e.printStackTrace();
  }
  Class<?> enclosingClass=target.getEnclosingClass();
  Constructor<?> con=null;
  try {
    if (enclosingClass == null) {
      con=target.getDeclaredConstructor();
    }
 else {
      con=target.getDeclaredConstructor(new Class[]{enclosingClass});
    }
    if (!con.isAccessible()) {
      con.setAccessible(true);
    }
  }
 catch (  SecurityException e) {
    e.printStackTrace();
  }
catch (  NoSuchMethodException e) {
    e.printStackTrace();
  }
  Field[] fields=target.getDeclaredFields();
  ArrayList<Field> inuse=new ArrayList<>();
  for (  Field field : fields) {
    String name=field.getName();
    if (getColumnIndex(name,false) != -1) {
      if (!field.isAccessible()) {
        field.setAccessible(true);
      }
      inuse.add(field);
    }
 else {
    }
  }
  int index=0;
  try {
    for (    TableRow row : rows()) {
      Object item=null;
      if (enclosingClass == null) {
        item=con.newInstance();
      }
 else {
        item=con.newInstance(new Object[]{enclosingObject});
      }
      for (      Field field : inuse) {
        String name=field.getName();
        if (field.getType() == String.class) {
          field.set(item,row.getString(name));
        }
 else         if (field.getType() == Integer.TYPE) {
          field.setInt(item,row.getInt(name));
        }
 else         if (field.getType() == Long.TYPE) {
          field.setLong(item,row.getLong(name));
        }
 else         if (field.getType() == Float.TYPE) {
          field.setFloat(item,row.getFloat(name));
        }
 else         if (field.getType() == Double.TYPE) {
          field.setDouble(item,row.getDouble(name));
        }
 else         if (field.getType() == Boolean.TYPE) {
          String content=row.getString(name);
          if (content != null) {
            if (content.toLowerCase().equals("true") || content.equals("1")) {
              field.setBoolean(item,true);
            }
          }
        }
 else         if (field.getType() == Character.TYPE) {
          String content=row.getString(name);
          if (content != null && content.length() > 0) {
            field.setChar(item,content.charAt(0));
          }
        }
      }
      Array.set(outgoing,index++,item);
    }
    if (!targetField.isAccessible()) {
      targetField.setAccessible(true);
    }
    targetField.set(enclosingObject,outgoing);
  }
 catch (  InstantiationException e) {
    e.printStackTrace();
  }
catch (  IllegalAccessException e) {
    e.printStackTrace();
  }
catch (  IllegalArgumentException e) {
    e.printStackTrace();
  }
catch (  InvocationTargetException e) {
    e.printStackTrace();
  }
}
