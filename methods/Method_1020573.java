@Override public void render(CaseEntity caseEntity){
  filterNameTxt.setText(caseEntity.getFilterName());
  glView.setTextureFilter(caseEntity.getTextureFilter());
  glView.setBitmap(caseEntity.getFirstBitmap());
  glView.requestRender();
  view.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      glView.requestRender();
      gpuImageView.requestRender();
    }
  }
);
  gpuImageView.getGPUImage().deleteImage();
  gpuImageView.setImage(caseEntity.getFirstBitmap());
  gpuImageView.setFilter(caseEntity.getGpuImageFilter());
  gpuImageView.requestRender();
}
