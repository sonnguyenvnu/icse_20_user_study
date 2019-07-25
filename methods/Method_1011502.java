@Override public Iterable<Problem> check(SModule m){
{
    SearchScope scope_u6fl1v_a0e=CommandUtil.createScope(m);
    final SearchScope scope_u6fl1v_a0e_0=new EditableFilteringScope(scope_u6fl1v_a0e);
    QueryExecutionContext context=new QueryExecutionContext(){
      public SearchScope getDefaultSearchScope(){
        return scope_u6fl1v_a0e_0;
      }
    }
;
    Iterable<Problem> notSet=CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103553c5ffL,"jetbrains.mps.lang.structure.structure.AbstractConceptDeclaration"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return isEmptyString(SPropertyOperations.getString(it,MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103553c5ffL,0x5d2e6079771f8cc0L,"conceptId")));
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return ((Problem)new NotMigratedNode(it){
          public String getMessage(){
            return "Concept id is not set";
          }
        }
);
      }
    }
);
    Iterable<Problem> notSetProp=CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979bd086bL,"jetbrains.mps.lang.structure.structure.PropertyDeclaration"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return isEmptyString(SPropertyOperations.getString(it,MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979bd086bL,0x35a81382d82a4d9L,"propertyId")));
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return ((Problem)new NotMigratedNode(it){
          public String getMessage(){
            return "Property id is not set";
          }
        }
);
      }
    }
);
    Iterable<Problem> notSetLinks=CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979bd086aL,"jetbrains.mps.lang.structure.structure.LinkDeclaration"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return isEmptyString(SPropertyOperations.getString(it,MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979bd086aL,0x35a81382d82a4e4L,"linkId")));
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return ((Problem)new NotMigratedNode(it){
          public String getMessage(){
            return "Link id is not set";
          }
        }
);
      }
    }
);
    Iterable<Problem> notEmpty=CollectionSequence.fromCollection(CommandUtil.instances(CommandUtil.selectScope(null,context),MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103553c5ffL,"jetbrains.mps.lang.structure.structure.AbstractConceptDeclaration"),false)).where(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return SPropertyOperations.getInteger(it,MetaAdapterFactory.getProperty(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0x1103553c5ffL,0x16096a174f259419L,"intConceptId")) != 0;
      }
    }
).select(new ISelector<SNode,Problem>(){
      public Problem select(      SNode it){
        return ((Problem)new NotMigratedNode(it){
          public String getMessage(){
            return "Old concept id != null";
          }
        }
);
      }
    }
);
    return Sequence.fromIterable(notSet).union(Sequence.fromIterable(notSetProp)).union(Sequence.fromIterable(notSetLinks)).union(Sequence.fromIterable(notEmpty));
  }
}
