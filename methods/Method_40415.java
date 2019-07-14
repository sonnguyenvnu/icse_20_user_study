void buildObjectType(){
  String[] obj_methods={"__delattr__","__format__","__getattribute__","__hash__","__init__","__new__","__reduce__","__reduce_ex__","__repr__","__setattr__","__sizeof__","__str__","__subclasshook__"};
  for (  String m : obj_methods) {
    Object.getTable().update(m,newLibUrl("stdtypes"),newFunc(),METHOD);
  }
  Object.getTable().update("__doc__",newLibUrl("stdtypes"),BaseStr,CLASS);
  Object.getTable().update("__class__",newLibUrl("stdtypes"),unknown(),CLASS);
}
