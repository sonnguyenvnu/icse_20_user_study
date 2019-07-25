private Query<PsiReference> query(PsiMethod method){
  Query<PsiReference> exactUsages=MethodReferencesSearch.search(method);
  final Query<PsiReference>[] query=new Query[]{exactUsages};
  OverridingMethodsSearch.search(method).forEach(new Processor<PsiMethod>(){
    @Override public boolean process(    PsiMethod psiMethod){
      Query<PsiReference> q=MethodReferencesSearch.search(psiMethod);
      if (query[0] == null) {
        query[0]=q;
      }
 else {
        query[0]=new MergeQuery<PsiReference>(query[0],q);
      }
      return false;
    }
  }
);
  return query[0];
}
