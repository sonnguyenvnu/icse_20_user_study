public static Iterable<SNode> ancestors(final SNode arg,boolean concreteFirst){
  final SNode rootOfHierarchy=SNodeOperations.cast(SLinkOperations.getTarget(_quotation_createNode_pzuztq_a0a0c0d(),MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,0x101de490babL,"classifier")),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c108ca66L,"jetbrains.mps.baseLanguage.structure.ClassConcept"));
  Iterable<SNode> classes=Sequence.fromClosure(new ISequenceClosure<SNode>(){
    public Iterable<SNode> iterable(){
      return new Iterable<SNode>(){
        public Iterator<SNode> iterator(){
          return new YieldingIterator<SNode>(){
            protected boolean moveToNext(){
              __loop__:               do {
                __switch__: switch (this.__CP__) {
case -1:
                  assert false : "Internal error";
                return false;
case 7:
              if (_3_cl == rootOfHierarchy) {
                this.__CP__=8;
                break;
              }
            this.__CP__=9;
          break;
case 11:
        if (_3_cl == null) {
          this.__CP__=12;
          break;
        }
      this.__CP__=4;
    break;
case 4:
  if (_3_cl != null) {
    this.__CP__=5;
    break;
  }
this.__CP__=1;
break;
case 6:
this.__CP__=7;
this.yield(_3_cl);
return true;
case 0:
this._3_cl=arg;
this.__CP__=4;
break;
case 5:
this.__CP__=6;
break;
case 9:
_3_cl=SNodeOperations.cast(SLinkOperations.getTarget(SLinkOperations.getTarget(_3_cl,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c108ca66L,0x10f6353296dL,"superclass")),MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101de48bf9eL,0x101de490babL,"classifier")),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c108ca66L,"jetbrains.mps.baseLanguage.structure.ClassConcept"));
this.__CP__=11;
break;
case 8:
this.__CP__=1;
break;
case 12:
_3_cl=rootOfHierarchy;
this.__CP__=4;
break;
default :
break __loop__;
}
}
 while (true);
return false;
}
}
;
}
}
;
}
}
);
if (concreteFirst) {
return classes;
}
 else {
return ListSequence.fromList(Sequence.fromIterable(classes).toListSequence()).reversedList();
}
}
