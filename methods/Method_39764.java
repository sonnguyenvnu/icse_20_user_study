/** 
 * Creates proxy methods over target method, For each matched proxy, new proxy method is created by taking advice bytecode and replaces usages of  {@link jodd.proxetta.ProxyTarget}. <p> Invocation chain example:  {@code name -> name$p0 -> name$p1 -> name$p4 -> super}.
 */
public void createProxyMethod(final TargetMethodData td){
  final ProxyAspectData aspectData=td.getProxyData();
  int access=td.msign.getAccessFlags();
  access&=~ACC_NATIVE;
  access&=~ACC_ABSTRACT;
  access=ProxettaAsmUtil.makePrivateFinalAccess(access);
  final MethodVisitor mv=wd.dest.visitMethod(access,td.methodName(),td.msign.getDescription(),null,null);
  mv.visitCode();
  aspectData.getAdviceClassReader().accept(new EmptyClassVisitor(){
    @Override public MethodVisitor visitMethod(    final int access,    final String name,    final String desc,    final String signature,    final String[] exceptions){
      if (!name.equals(ProxettaNames.executeMethodName)) {
        return null;
      }
      return new HistoryMethodAdapter(mv){
        @Override public void visitFieldInsn(        final int opcode,        String owner,        String name,        final String desc){
          if (owner.equals(aspectData.adviceReference)) {
            owner=wd.thisReference;
            name=adviceFieldName(name,aspectData.aspectIndex);
          }
          super.visitFieldInsn(opcode,owner,name,desc);
        }
        @Override public void visitVarInsn(        final int opcode,        int var){
          var+=(var == 0 ? 0 : td.msign.getAllArgumentsSize());
          if (proxyInfoRequested) {
            proxyInfoRequested=false;
            if (opcode == ASTORE) {
              ProxyTargetReplacement.info(mv,td.msign,var);
            }
          }
          super.visitVarInsn(opcode,var);
        }
        @Override public void visitIincInsn(        int var,        final int increment){
          var+=(var == 0 ? 0 : td.msign.getAllArgumentsSize());
          super.visitIincInsn(var,increment);
        }
        @Override public void visitInsn(        final int opcode){
          if (opcode == ARETURN) {
            visitReturn(mv,td.msign,true);
            return;
          }
          if (traceNext) {
            if ((opcode == POP) || (opcode == POP2)) {
              return;
            }
          }
          super.visitInsn(opcode);
        }
        @SuppressWarnings({"ParameterNameDiffersFromOverriddenParameter"}) @Override public void visitMethodInsn(        final int opcode,        String string,        String mname,        final String mdesc,        final boolean isInterface){
          if ((opcode == INVOKEVIRTUAL) || (opcode == INVOKEINTERFACE) || (opcode == INVOKESPECIAL)) {
            if (string.equals(aspectData.adviceReference)) {
              string=wd.thisReference;
              mname=adviceMethodName(mname,aspectData.aspectIndex);
            }
          }
 else           if (opcode == INVOKESTATIC) {
            if (string.equals(aspectData.adviceReference)) {
              string=wd.thisReference;
              mname=adviceMethodName(mname,aspectData.aspectIndex);
            }
 else             if (string.endsWith('/' + TARGET_CLASS_NAME)) {
              if (isInvokeMethod(mname,mdesc)) {
                if (td.isLastMethodInChain()) {
                  if (!wd.isWrapper()) {
                    loadSpecialMethodArguments(mv,td.msign);
                    mv.visitMethodInsn(INVOKESPECIAL,wd.superReference,td.msign.getMethodName(),td.msign.getDescription(),isInterface);
                  }
 else {
                    mv.visitVarInsn(ALOAD,0);
                    mv.visitFieldInsn(GETFIELD,wd.thisReference,wd.wrapperRef,wd.wrapperType);
                    loadVirtualMethodArguments(mv,td.msign);
                    if (wd.wrapInterface) {
                      mv.visitMethodInsn(INVOKEINTERFACE,wd.wrapperType.substring(1,wd.wrapperType.length() - 1),td.msign.getMethodName(),td.msign.getDescription(),true);
                    }
 else {
                      mv.visitMethodInsn(INVOKEVIRTUAL,wd.wrapperType.substring(1,wd.wrapperType.length() - 1),td.msign.getMethodName(),td.msign.getDescription(),isInterface);
                    }
                  }
                  prepareReturnValue(mv,td.msign,aspectData.maxLocalVarOffset);
                  traceNext=true;
                }
 else {
                  loadSpecialMethodArguments(mv,td.msign);
                  mv.visitMethodInsn(INVOKESPECIAL,wd.thisReference,td.nextMethodName(),td.msign.getDescription(),isInterface);
                  visitReturn(mv,td.msign,false);
                }
                return;
              }
              if (isArgumentsCountMethod(mname,mdesc)) {
                ProxyTargetReplacement.argumentsCount(mv,td.msign);
                return;
              }
              if (isArgumentTypeMethod(mname,mdesc)) {
                int argIndex=this.getArgumentIndex();
                ProxyTargetReplacement.argumentType(mv,td.msign,argIndex);
                return;
              }
              if (isArgumentMethod(mname,mdesc)) {
                int argIndex=this.getArgumentIndex();
                ProxyTargetReplacement.argument(mv,td.msign,argIndex);
                return;
              }
              if (isSetArgumentMethod(mname,mdesc)) {
                int argIndex=this.getArgumentIndex();
                checkArgumentIndex(td.msign,argIndex);
                mv.visitInsn(POP);
                storeMethodArgumentFromObject(mv,td.msign,argIndex);
                return;
              }
              if (isCreateArgumentsArrayMethod(mname,mdesc)) {
                ProxyTargetReplacement.createArgumentsArray(mv,td.msign);
                return;
              }
              if (isCreateArgumentsClassArrayMethod(mname,mdesc)) {
                ProxyTargetReplacement.createArgumentsClassArray(mv,td.msign);
                return;
              }
              if (isTargetMethod(mname,mdesc)) {
                mv.visitVarInsn(ALOAD,0);
                return;
              }
              if (isTargetClassMethod(mname,mdesc)) {
                ProxyTargetReplacement.targetClass(mv,td.msign);
                return;
              }
              if (isTargetMethodNameMethod(mname,mdesc)) {
                ProxyTargetReplacement.targetMethodName(mv,td.msign);
                return;
              }
              if (isTargetMethodSignatureMethod(mname,mdesc)) {
                ProxyTargetReplacement.targetMethodSignature(mv,td.msign);
                return;
              }
              if (isTargetMethodDescriptionMethod(mname,mdesc)) {
                ProxyTargetReplacement.targetMethodDescription(mv,td.msign);
                return;
              }
              if (isInfoMethod(mname,mdesc)) {
                proxyInfoRequested=true;
                return;
              }
              if (isReturnTypeMethod(mname,mdesc)) {
                ProxyTargetReplacement.returnType(mv,td.msign);
                return;
              }
              if (isReturnValueMethod(mname,mdesc)) {
                castToReturnType(mv,td.msign);
                return;
              }
              if (isTargetMethodAnnotationMethod(mname,mdesc)) {
                String[] args=getLastTwoStringArguments();
                mv.visitInsn(POP);
                mv.visitInsn(POP);
                ProxyTargetReplacement.targetMethodAnnotation(mv,td.msign,args);
                return;
              }
              if (isTargetClassAnnotationMethod(mname,mdesc)) {
                String[] args=getLastTwoStringArguments();
                mv.visitInsn(POP);
                mv.visitInsn(POP);
                ProxyTargetReplacement.targetClassAnnotation(mv,td.msign.getClassInfo(),args);
                return;
              }
            }
          }
          super.visitMethodInsn(opcode,string,mname,mdesc,isInterface);
        }
      }
;
    }
  }
,0);
}
