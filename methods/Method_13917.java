@Override public PyObject __findattr_ex__(String name){
  Object v=_obj.getField(name,_bindings);
  if (v != null) {
    if (v instanceof PyObject) {
      return (PyObject)v;
    }
 else     if (v instanceof HasFields) {
      return new JythonHasFieldsWrapper((HasFields)v,_bindings);
    }
 else     if (Py.getAdapter().canAdapt(v)) {
      return Py.java2py(v);
    }
 else {
      return new JythonObjectWrapper(v);
    }
  }
 else {
    return null;
  }
}
