/** 
 * The SET instructions set the 16-bit destination to 1 if the specified condition is true, otherwise destination is set to 0. <p> FamilyOpcode   Instruction        SET to 1 if ... else to 0            Flags 0x01           SETA, SETNBE       Above, Not Below or Equal            CF=0 AND ZF=0 0x02           SETAE,SETNB,SETNC  Above or Equal, Not Below, No Carry  CF=0 0x03           SETBE, SETNA       Below or Equal, Not Above            CF=1 OR ZF=1 0x04           SETB, SETC,SETNAE  Below, Carry, Not Above or Equal     CF=1 0x05           SETE, SETZ         Equal, Zero                          ZF=1 0x06           SETNE, SETNZ       Not Equal, Not Zero                  ZF=0 0x07           SETG, SETNLE       Greater, Not Less or Equal           SF=OF AND ZF=0 0x08           SETGE, SETNL       Greater or Equal, Not Less           SF=OF 0x09           SETLE, SETNG       Less or Equal, Not Greater           SF<>OF OR ZF=1 0x0A           SETL, SETNGE       Less, Not Greater or Equal           SF<>OF 0x0B           SETO               Overflow                             OF=1 0x0C           SETNO              No Overflow                          OF=0 0x0D           SETS               Sign (negative)                      SF=1 0x0E           SETNS              No Sign (positive)                   SF=0
 */
private static Status setcc(Target dst,int dstIndex,int familyOpCode,Status status){
switch (familyOpCode) {
case SETA:
    return seta(dst,dstIndex,status);
case SETAE:
  return setae(dst,dstIndex,status);
case SETBE:
return setbe(dst,dstIndex,status);
case SETB:
return setb(dst,dstIndex,status);
case SETE:
return sete(dst,dstIndex,status);
case SETNE:
return setne(dst,dstIndex,status);
case SETG:
return setg(dst,dstIndex,status);
case SETGE:
return setge(dst,dstIndex,status);
case SETLE:
return setle(dst,dstIndex,status);
case SETL:
return setl(dst,dstIndex,status);
case SETO:
return seto(dst,dstIndex,status);
case SETNO:
return setno(dst,dstIndex,status);
case SETS:
return sets(dst,dstIndex,status);
case SETNS:
return setns(dst,dstIndex,status);
default :
return status;
}
}
