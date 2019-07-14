/** 
 * Returns argument index from the history. <b>Must</b> POP value from the stack after the execution.
 */
protected int getArgumentIndex(){
  if (!isPrevious) {
    throw new ProxettaException("Unexpected previous instruction type used for setting argument index");
  }
  int argIndex;
switch (opcode) {
case ICONST_0:
    argIndex=0;
  break;
case ICONST_1:
argIndex=1;
break;
case ICONST_2:
argIndex=2;
break;
case ICONST_3:
argIndex=3;
break;
case ICONST_4:
argIndex=4;
break;
case ICONST_5:
argIndex=5;
break;
case BIPUSH:
case SIPUSH:
argIndex=operand;
break;
default :
throw new ProxettaException("Unexpected previous instruction used for setting argument index");
}
return argIndex;
}
