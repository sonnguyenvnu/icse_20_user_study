void buildObjectType(){
  String[] obj_methods={"__delattr__","__format__","__getattribute__","__hash__","__init__","__new__","__reduce__","__reduce_ex__","__repr__","__setattr__","__sizeof__","__str__","__subclasshook__"};
  for (  String m : obj_methods) {
    objectType.table.insert(m,newLibUrl("stdtypes"),newFunc(),METHOD);
  }
  objectType.table.insert("__doc__",newLibUrl("stdtypes"),Type.STR,CLASS);
  objectType.table.insert("__class__",newLibUrl("stdtypes"),Type.UNKNOWN,CLASS);
}
