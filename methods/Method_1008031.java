/** 
 * invokeDynamic bootstrap method <p> In addition to ordinary parameters, we also take some parameters defined at the call site: <ul> <li> {@code initialDepth}: initial call site depth. this is used to exercise megamorphic fallback. <li> {@code flavor}: type of dynamic call it is (and which part of whitelist to look at). <li> {@code args}: flavor-specific args. </ul> And we take the  {@link Definition} used to compile the script for whitelist checking.<p> see https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.invokedynamic
 */
public static CallSite bootstrap(Definition definition,Lookup lookup,String name,MethodType type,int initialDepth,int flavor,Object... args){
switch (flavor) {
case METHOD_CALL:
    if (args.length == 0) {
      throw new BootstrapMethodError("Invalid number of parameters for method call");
    }
  if (args[0] instanceof String == false) {
    throw new BootstrapMethodError("Illegal parameter for method call: " + args[0]);
  }
String recipe=(String)args[0];
int numLambdas=recipe.length();
if (numLambdas > type.parameterCount()) {
throw new BootstrapMethodError("Illegal recipe for method call: too many bits");
}
if (args.length != numLambdas + 1) {
throw new BootstrapMethodError("Illegal number of parameters: expected " + numLambdas + " references");
}
return new PIC(definition,lookup,name,type,initialDepth,flavor,args);
case LOAD:
case STORE:
case ARRAY_LOAD:
case ARRAY_STORE:
case ITERATOR:
case INDEX_NORMALIZE:
if (args.length > 0) {
throw new BootstrapMethodError("Illegal static bootstrap parameters for flavor: " + flavor);
}
return new PIC(definition,lookup,name,type,initialDepth,flavor,args);
case REFERENCE:
if (args.length != 1) {
throw new BootstrapMethodError("Invalid number of parameters for reference call");
}
if (args[0] instanceof String == false) {
throw new BootstrapMethodError("Illegal parameter for reference call: " + args[0]);
}
return new PIC(definition,lookup,name,type,initialDepth,flavor,args);
case UNARY_OPERATOR:
case SHIFT_OPERATOR:
case BINARY_OPERATOR:
if (args.length != 1) {
throw new BootstrapMethodError("Invalid number of parameters for operator call");
}
if (args[0] instanceof Integer == false) {
throw new BootstrapMethodError("Illegal parameter for reference call: " + args[0]);
}
int flags=(int)args[0];
if ((flags & OPERATOR_ALLOWS_NULL) != 0 && flavor != BINARY_OPERATOR) {
throw new BootstrapMethodError("This parameter is only supported for BINARY_OPERATORs");
}
if ((flags & OPERATOR_COMPOUND_ASSIGNMENT) != 0 && flavor != BINARY_OPERATOR && flavor != SHIFT_OPERATOR) {
throw new BootstrapMethodError("This parameter is only supported for BINARY/SHIFT_OPERATORs");
}
return new MIC(name,type,initialDepth,flavor,flags);
default :
throw new BootstrapMethodError("Illegal static bootstrap parameter for flavor: " + flavor);
}
}
