void buildNumTypes(){
  State bft=Type.FLOAT.table;
  String[] float_methods_num={"__abs__","__add__","__coerce__","__div__","__divmod__","__eq__","__float__","__floordiv__","__format__","__ge__","__getformat__","__gt__","__int__","__le__","__long__","__lt__","__mod__","__mul__","__ne__","__neg__","__new__","__nonzero__","__pos__","__pow__","__radd__","__rdiv__","__rdivmod__","__rfloordiv__","__rmod__","__rmul__","__rpow__","__rsub__","__rtruediv__","__setformat__","__sub__","__truediv__","__trunc__","as_integer_ratio","fromhex","is_integer"};
  for (  String m : float_methods_num) {
    bft.insert(m,numUrl(),newFunc(Type.FLOAT),METHOD);
  }
  State bnt=Type.INT.table;
  String[] num_methods_num={"__abs__","__add__","__and__","__class__","__cmp__","__coerce__","__delattr__","__div__","__divmod__","__doc__","__float__","__floordiv__","__getattribute__","__getnewargs__","__hash__","__hex__","__index__","__init__","__int__","__invert__","__long__","__lshift__","__mod__","__mul__","__neg__","__new__","__nonzero__","__oct__","__or__","__pos__","__pow__","__radd__","__rand__","__rdiv__","__rdivmod__","__reduce__","__reduce_ex__","__repr__","__rfloordiv__","__rlshift__","__rmod__","__rmul__","__ror__","__rpow__","__rrshift__","__rshift__","__rsub__","__rtruediv__","__rxor__","__setattr__","__str__","__sub__","__truediv__","__xor__"};
  for (  String m : num_methods_num) {
    bnt.insert(m,numUrl(),newFunc(Type.INT),METHOD);
  }
  bnt.insert("__getnewargs__",numUrl(),newFunc(newTuple(Type.INT)),METHOD);
  bnt.insert("hex",numUrl(),newFunc(Type.STR),METHOD);
  bnt.insert("conjugate",numUrl(),newFunc(Type.COMPLEX),METHOD);
  State bct=Type.COMPLEX.table;
  String[] complex_methods={"__abs__","__add__","__div__","__divmod__","__float__","__floordiv__","__format__","__getformat__","__int__","__long__","__mod__","__mul__","__neg__","__new__","__pos__","__pow__","__radd__","__rdiv__","__rdivmod__","__rfloordiv__","__rmod__","__rmul__","__rpow__","__rsub__","__rtruediv__","__sub__","__truediv__","conjugate"};
  for (  String c : complex_methods) {
    bct.insert(c,numUrl(),newFunc(Type.COMPLEX),METHOD);
  }
  String[] complex_methods_num={"__eq__","__ge__","__gt__","__le__","__lt__","__ne__","__nonzero__","__coerce__"};
  for (  String cn : complex_methods_num) {
    bct.insert(cn,numUrl(),newFunc(Type.INT),METHOD);
  }
  bct.insert("__getnewargs__",numUrl(),newFunc(newTuple(Type.COMPLEX)),METHOD);
  bct.insert("imag",numUrl(),Type.INT,ATTRIBUTE);
  bct.insert("real",numUrl(),Type.INT,ATTRIBUTE);
}
