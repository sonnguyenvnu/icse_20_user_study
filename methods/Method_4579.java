void floatElement(int id,double value){
switch (id) {
case ID_DURATION:
    durationTimecode=(long)value;
  break;
case ID_SAMPLING_FREQUENCY:
currentTrack.sampleRate=(int)value;
break;
case ID_PRIMARY_R_CHROMATICITY_X:
currentTrack.primaryRChromaticityX=(float)value;
break;
case ID_PRIMARY_R_CHROMATICITY_Y:
currentTrack.primaryRChromaticityY=(float)value;
break;
case ID_PRIMARY_G_CHROMATICITY_X:
currentTrack.primaryGChromaticityX=(float)value;
break;
case ID_PRIMARY_G_CHROMATICITY_Y:
currentTrack.primaryGChromaticityY=(float)value;
break;
case ID_PRIMARY_B_CHROMATICITY_X:
currentTrack.primaryBChromaticityX=(float)value;
break;
case ID_PRIMARY_B_CHROMATICITY_Y:
currentTrack.primaryBChromaticityY=(float)value;
break;
case ID_WHITE_POINT_CHROMATICITY_X:
currentTrack.whitePointChromaticityX=(float)value;
break;
case ID_WHITE_POINT_CHROMATICITY_Y:
currentTrack.whitePointChromaticityY=(float)value;
break;
case ID_LUMNINANCE_MAX:
currentTrack.maxMasteringLuminance=(float)value;
break;
case ID_LUMNINANCE_MIN:
currentTrack.minMasteringLuminance=(float)value;
break;
case ID_PROJECTION_POSE_YAW:
currentTrack.projectionPoseYaw=(float)value;
break;
case ID_PROJECTION_POSE_PITCH:
currentTrack.projectionPosePitch=(float)value;
break;
case ID_PROJECTION_POSE_ROLL:
currentTrack.projectionPoseRoll=(float)value;
break;
default :
break;
}
}
