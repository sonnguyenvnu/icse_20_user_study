protected Object unwrap(PyObject po){
  if (po instanceof PyNone) {
    return null;
  }
 else   if (po.isNumberType()) {
    return po.asDouble();
  }
 else   if (po.isSequenceType()) {
    Iterator<PyObject> i=po.asIterable().iterator();
    List<Object> list=new ArrayList<Object>();
    while (i.hasNext()) {
      list.add(unwrap((Object)i.next()));
    }
    return list.toArray();
  }
 else {
    return po;
  }
}
