public static void start(@NonNull Context context,@NonNull String login,@NonNull String repo,long commentId,ReactionTypes reactionType,boolean isCommit,boolean isDelete,boolean isEnterprise){
  Intent intent=new Intent(context,ReactionService.class);
  intent.putExtras(Bundler.start().put(BundleConstant.EXTRA,isCommit).put(BundleConstant.EXTRA_TWO,login).put(BundleConstant.EXTRA_THREE,repo).put(BundleConstant.EXTRA_FOUR,isDelete).put(BundleConstant.ID,commentId).put(BundleConstant.EXTRA_TYPE,reactionType).put(BundleConstant.IS_ENTERPRISE,isEnterprise).end());
  context.startService(intent);
}
