public static <X1,X2,ZZ,E1 extends Throwable,E2 extends Throwable>ZZ chain(X1 obj,Pfun<X1,X2,E1> function1,Pfun<X2,ZZ,E2> function2) throws Pausable, E1, E2 {
  X2 obj2=function1.apply(obj);
  return function2.apply(obj2);
}
