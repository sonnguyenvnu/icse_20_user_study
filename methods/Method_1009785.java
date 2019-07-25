/** 
 * draw specific texture with specific texture matrix
 * @param tex_id texture ID
 * @param tex_matrix texture matrix?if this is null, the last one use(we don't check size of this array and needs at least 16 of float)
 */
public void draw(final int tex_id,final float[] tex_matrix){
  GLES20.glUseProgram(hProgram);
  if (tex_matrix != null)   GLES20.glUniformMatrix4fv(muTexMatrixLoc,1,false,tex_matrix,0);
  GLES20.glUniformMatrix4fv(muMVPMatrixLoc,1,false,mMvpMatrix,0);
  GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
  GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,tex_id);
  GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP,0,VERTEX_NUM);
  GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,0);
  GLES20.glUseProgram(0);
}
