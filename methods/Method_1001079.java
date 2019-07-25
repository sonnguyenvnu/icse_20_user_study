public static CString idprint(__ptr__ state,int objtype,int id){
  ENTERING("8143j507ej7uqqjzw5i32xej5","idprint");
  try {
    if (id % 2 == 0)     return (CString)Memory.fromIdentityHashCode(id);
 else     return null;
  }
  finally {
    LEAVING("8143j507ej7uqqjzw5i32xej5","idprint");
  }
}
