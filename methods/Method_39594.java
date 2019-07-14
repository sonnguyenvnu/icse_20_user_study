/** 
 * Returns the "canonical"  {@link Label} instance corresponding to this label's bytecode offset,if known, otherwise the label itself. The canonical instance is the first label (in the order of their visit by  {@link MethodVisitor#visitLabel}) corresponding to this bytecode offset. It cannot be known for labels which have not been visited yet. <p><i>This method should only be used when the  {@link MethodWriter#COMPUTE_ALL_FRAMES} optionis used.</i>
 * @return the label itself if {@link #frame} is null, otherwise the Label's frame owner. Thiscorresponds to the "canonical" label instance described above thanks to the way the label frame is set in  {@link MethodWriter#visitLabel}.
 */
final Label getCanonicalInstance(){
  return frame == null ? this : frame.owner;
}
