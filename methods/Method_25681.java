private static ApiDiff loadApiDiff(){
  try {
    ApiDiffProto.Diff.Builder diffBuilder=ApiDiffProto.Diff.newBuilder();
    byte[] diffData=Resources.toByteArray(Resources.getResource(Java7ApiChecker.class,"7to8diff.binarypb"));
    diffBuilder.mergeFrom(diffData).addClassDiff(ApiDiffProto.ClassDiff.newBuilder().setMemberDiff(ApiDiffProto.MemberDiff.newBuilder().setClassName("com/google/common/base/Predicate").addMember(ApiDiffProto.ClassMember.newBuilder().setIdentifier("test").setMemberDescriptor("(Ljava/lang/Object;)Z")))).addClassDiff(ApiDiffProto.ClassDiff.newBuilder().setMemberDiff(ApiDiffProto.MemberDiff.newBuilder().setClassName("com/google/common/base/BinaryPredicate").addMember(ApiDiffProto.ClassMember.newBuilder().setIdentifier("test").setMemberDescriptor("(Ljava/lang/Object;Ljava/lang/Object;)Z"))));
    return ApiDiff.fromProto(diffBuilder.build());
  }
 catch (  IOException e) {
    throw new UncheckedIOException(e);
  }
}
