@SneakyThrows public static void writeClass(String file,String code){
  File oldClassFile=new File(file);
  if (oldClassFile.exists()) {
    CompilationUnit old=JavaParser.parse(oldClassFile);
    CompilationUnit newClazz=JavaParser.parse(code);
    Map<String,FieldDeclaration> oldFields=old.findAll(FieldDeclaration.class).stream().collect(Collectors.toMap(declaration -> declaration.getVariable(0).getNameAsString(),Function.identity()));
    Map<String,MethodDeclaration> oldMethod=old.findAll(MethodDeclaration.class).stream().collect(Collectors.toMap(NodeWithSimpleName::getNameAsString,Function.identity()));
    newClazz.findAll(FieldDeclaration.class).forEach(declaration -> {
      String name=declaration.getVariable(0).getNameAsString();
      if (oldFields.get(name) == null) {
        VariableDeclarator declarator=declaration.getVariable(0);
        FieldDeclaration newField=old.getType(0).addField(declarator.getType(),declarator.getNameAsString(),declaration.getModifiers().toArray(new Modifier[]{}));
        declaration.getJavadocComment().ifPresent(newField::setJavadocComment);
        for (        Comment comment : declaration.getAllContainedComments()) {
          newField.setComment(comment);
        }
        for (        AnnotationExpr annotationExpr : declaration.getAnnotations()) {
          newField.addAnnotation(annotationExpr.clone());
        }
      }
    }
);
    newClazz.findAll(MethodDeclaration.class).forEach(declaration -> {
      String name=declaration.getNameAsString();
      if (oldMethod.get(name) == null) {
        MethodDeclaration newMethod=old.getType(0).addMethod(name,declaration.getModifiers().toArray(new Modifier[]{}));
        declaration.getJavadocComment().ifPresent(newMethod::setJavadocComment);
        for (        Comment comment : declaration.getAllContainedComments()) {
          newMethod.setComment(comment);
        }
        for (        AnnotationExpr annotationExpr : declaration.getAnnotations()) {
          newMethod.addAnnotation(annotationExpr.clone());
        }
      }
    }
);
    code=old.toString();
  }
  FileUtils.writeString2File(code,file,"utf-8");
}
