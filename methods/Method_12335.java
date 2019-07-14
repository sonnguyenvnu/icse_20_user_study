private static InputSource findSource(final TypedArray textureViewAttributes){
  final TypedValue value=new TypedValue();
  if (!textureViewAttributes.getValue(R.styleable.GifTextureView_gifSource,value)) {
    return null;
  }
  if (value.resourceId != 0) {
    final String resourceTypeName=textureViewAttributes.getResources().getResourceTypeName(value.resourceId);
    if (GifViewUtils.SUPPORTED_RESOURCE_TYPE_NAMES.contains(resourceTypeName)) {
      return new InputSource.ResourcesSource(textureViewAttributes.getResources(),value.resourceId);
    }
 else     if (!"string".equals(resourceTypeName)) {
      throw new IllegalArgumentException("Expected string, drawable, mipmap or raw resource type. '" + resourceTypeName + "' is not supported");
    }
  }
  return new InputSource.AssetSource(textureViewAttributes.getResources().getAssets(),value.string.toString());
}
