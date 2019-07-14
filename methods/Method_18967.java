private static TypeName getMountType(Elements elements,TypeElement element){
  TypeElement viewType=elements.getTypeElement(ClassNames.VIEW_NAME);
  TypeElement drawableType=elements.getTypeElement(ClassNames.DRAWABLE_NAME);
  for (  Element enclosedElement : element.getEnclosedElements()) {
    if (enclosedElement.getKind() != ElementKind.METHOD) {
      continue;
    }
    OnCreateMountContent annotation=enclosedElement.getAnnotation(OnCreateMountContent.class);
    if (annotation != null) {
      if (annotation.mountingType() == MountingType.VIEW) {
        return ClassNames.COMPONENT_LIFECYCLE_MOUNT_TYPE_VIEW;
      }
      if (annotation.mountingType() == MountingType.DRAWABLE) {
        return ClassNames.COMPONENT_LIFECYCLE_MOUNT_TYPE_DRAWABLE;
      }
      TypeMirror returnType=((ExecutableElement)enclosedElement).getReturnType();
      while (returnType.getKind() != TypeKind.NONE && returnType.getKind() != TypeKind.VOID) {
        final TypeElement returnElement=(TypeElement)((DeclaredType)returnType).asElement();
        if (returnElement.equals(viewType)) {
          return ClassNames.COMPONENT_LIFECYCLE_MOUNT_TYPE_VIEW;
        }
 else         if (returnElement.equals(drawableType)) {
          return ClassNames.COMPONENT_LIFECYCLE_MOUNT_TYPE_DRAWABLE;
        }
        try {
          returnType=returnElement.getSuperclass();
        }
 catch (        RuntimeException e) {
          throw new ComponentsProcessingException("Failed to get mount type for " + element + ".  Try specifying `@OnCreateMountContent(mountingType = MountingType.VIEW)` (or DRAWABLE).");
        }
      }
    }
  }
  return ClassNames.COMPONENT_LIFECYCLE_MOUNT_TYPE_NONE;
}
