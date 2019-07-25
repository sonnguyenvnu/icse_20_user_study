@Override public IInterfacedFinder instantiate(int token) throws IllegalArgumentException {
switch (token) {
case 0:
    return new AllMethodUsages_Finder();
case 1:
  return new BaseMethod_Finder();
case 2:
return new BaseMethodUsages_Finder();
case 3:
return new ClassAncestors_Finder();
case 4:
return new ClassUsages_Finder();
case 5:
return new ConstructorUsages_Finder();
case 6:
return new DerivedClasses_Finder();
case 7:
return new DerivedInterfaces_Finder();
case 8:
return new DerivedMethods_Finder();
case 9:
return new ExactMethodUsages_Finder();
case 10:
return new FieldUsages_Finder();
case 11:
return new ImplementedInterfaces_Finder();
case 12:
return new ImplementingClasses_Finder();
case 13:
return new InterfaceAncestors_Finder();
case 14:
return new InterfaceMethodImplementations_Finder();
case 15:
return new OverridingFields_Finder();
case 16:
return new OverridingMethods_Finder();
case 17:
return new ParameterUsages_Finder();
case 18:
return new StraightDerivedClasses_Finder();
default :
throw new IllegalArgumentException(String.format("Illegal identifier of a finder implementation: %d",token));
}
}
