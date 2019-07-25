public static void make(final Project project,final Iterable<? extends SModel> models,final Iterable<? extends SModule> modules,final boolean wholeProject,final boolean dirtyOnly,final boolean depClosure){
  final Wrappers._T<List<SModel>> modelsToGenerate=new Wrappers._T<List<SModel>>();
  project.getRepository().getModelAccess().runReadAction(new Runnable(){
    public void run(){
      if (wholeProject) {
        modelsToGenerate.value=ListSequence.fromListWithValues(new ArrayList<SModel>(),new ProjectScope(project).getModels());
      }
 else {
        modelsToGenerate.value=ListSequence.fromListWithValues(new ArrayList<SModel>(),ListSequence.fromList(ListSequence.fromListWithValues(new ArrayList<SModel>(),models)).concat(Sequence.fromIterable(modules).translate(new ITranslator2<SModule,SModel>(){
          public Iterable<SModel> translate(          SModule it){
            return it.getModels();
          }
        }
)));
      }
      if (depClosure) {
        Iterable<SModel> dependencies=modelsToGenerate.value;
        int oldSize;
        do {
          dependencies=Sequence.fromIterable(dependencies).translate(new ITranslator2<SModel,SModel>(){
            public Iterable<SModel> translate(            SModel it){
              return Sequence.fromIterable(((Iterable<SModelReference>)SModelOperations.getImportedModelUIDs(it))).select(new ISelector<SModelReference,SModel>(){
                public SModel select(                SModelReference it){
                  return it.resolve(project.getRepository());
                }
              }
);
            }
          }
).where(new IWhereFilter<SModel>(){
            public boolean accept(            SModel it){
              return GenerationFacade.canGenerate(it);
            }
          }
).distinct().subtract(ListSequence.fromList(modelsToGenerate.value)).toListSequence();
          oldSize=ListSequence.fromList(modelsToGenerate.value).count();
          ListSequence.fromList(modelsToGenerate.value).addSequence(Sequence.fromIterable(dependencies));
        }
 while (ListSequence.fromList(modelsToGenerate.value).count() > oldSize);
      }
      if (dirtyOnly) {
        final ModelGenerationStatusManager mgsm=project.getComponent(ModelGenerationStatusManager.class);
        modelsToGenerate.value=ListSequence.fromList(modelsToGenerate.value).where(new IWhereFilter<SModel>(){
          public boolean accept(          SModel it){
            return mgsm.generationRequired(it);
          }
        }
).toListSequence();
      }
 else {
        modelsToGenerate.value=ListSequence.fromList(modelsToGenerate.value).toListSequence();
      }
    }
  }
);
  SwingUtilities.invokeLater(new Runnable(){
    public void run(){
      new MakeActionImpl(new MakeActionParameters(project).models(modelsToGenerate.value).cleanMake(false)).executeAction();
    }
  }
);
}
