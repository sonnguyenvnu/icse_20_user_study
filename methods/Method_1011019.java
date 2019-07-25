public static void members(SNode classifier,final TextGenContext ctx){
  final TextGenSupport tgs=new TextGenSupport(ctx);
  boolean isWrappedElementBefore=true;
  SNode lastMember=Sequence.fromIterable(Classifier__BehaviorDescriptor.members_id1hodSy8nQmC.invoke(classifier)).last();
  for (  SNode member : Classifier__BehaviorDescriptor.members_id1hodSy8nQmC.invoke(classifier)) {
    boolean needsLineBefore=(boolean)ClassifierMember__BehaviorDescriptor.needsEmptyLineBefore_idzB21h1tQit.invoke(member);
    boolean needsLineAfter=(boolean)ClassifierMember__BehaviorDescriptor.needsEmptyLineAfter_idzB21h1tQNm.invoke(member);
    BaseLanguageTextGen.newLine(needsLineBefore && !(isWrappedElementBefore),ctx);
    tgs.appendNode(member);
    BaseLanguageTextGen.newLine(needsLineAfter && !((lastMember == member)),ctx);
    isWrappedElementBefore=needsLineAfter;
  }
}
