@Override public void save(int saveFlags){
  boolean saveAlpha=(saveFlags & SAVE_FLAG_ALPHA) == SAVE_FLAG_ALPHA;
  if (saveAlpha) {
    float currentAlpha=getAlpha();
    mCurrentAlphaIndex++;
    if (mAlphas.length <= mCurrentAlphaIndex) {
      mAlphas=Arrays.copyOf(mAlphas,mAlphas.length * 2);
    }
    mAlphas[mCurrentAlphaIndex]=currentAlpha;
  }
  boolean saveMatrix=(saveFlags & SAVE_FLAG_MATRIX) == SAVE_FLAG_MATRIX;
  if (saveMatrix) {
    int currentIndex=mCurrentMatrixIndex;
    mCurrentMatrixIndex+=MATRIX_SIZE;
    if (mMatrices.length <= mCurrentMatrixIndex) {
      mMatrices=Arrays.copyOf(mMatrices,mMatrices.length * 2);
    }
    System.arraycopy(mMatrices,currentIndex,mMatrices,mCurrentMatrixIndex,MATRIX_SIZE);
  }
  mSaveFlags.add(saveFlags);
}
