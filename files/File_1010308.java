/*
 * Copyright 2003-2018 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.project.structure.modules.mappingpriorities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

import java.util.Objects;

public class MappingConfig_SimpleRef extends MappingConfig_AbstractRef {
  static final int PERSISTENCE_ID = 0x55550002;

  private String myModelUID;
  private String myNodeID;
  /*
   * hint for MC name (if myNodeId points to specific MC), to avoid dependency from sources
   * when we need to report an issue with deployed priority rule.
   */
  private String myConfigName;

  public String getModelUID() {
    return myModelUID;
  }

  public void setModelUID(String modelUID) {
    myModelUID = modelUID;
  }

  public String getNodeID() {
    return myNodeID;
  }

  public void setNodeID(String nodeID) {
    myNodeID = nodeID;
  }

  public boolean includesAll() {
    return "*".equals(myNodeID);
  }

  public void setMapConfigName(String mcName) {
    myConfigName = mcName;
  }

  @Override
  @NotNull
  public MappingConfig_SimpleRef copy() {
    MappingConfig_SimpleRef result = new MappingConfig_SimpleRef();
    result.myModelUID = myModelUID;
    result.myNodeID = myNodeID;
    result.myConfigName = myConfigName;
    return result;
  }

  @Override
  public boolean isIncomplete() {
    return myModelUID == null || myNodeID == null;
  }

  @Override
  public boolean removeModelReference(SModelReference ref, boolean[] mappingsChanged) {
    if (myModelUID != null && myModelUID.equals(ref.toString())) {
      mappingsChanged[0] = true;
      return true;
    }
    return false;
  }

  @Override
  public boolean updateReferences(SRepository repository) {
    if (myModelUID.equals("*")) {
      return false;
    }
    final SModelReference modelReference = PersistenceFacade.getInstance().createModelReference(myModelUID);
    final SModel model = modelReference.resolve(repository);
    if (model != null && !modelReference.equals(model.getReference())) {
      myModelUID = PersistenceFacade.getInstance().asString(model.getReference());
      return true;
    }
    return false;
  }

  @Override
  public String asString(SRepository repository) {
    final SModelReference modelReference = PersistenceFacade.getInstance().createModelReference(myModelUID);
    String modelName = modelReference.getName().getLongName();
    StringBuilder sb = new StringBuilder();
    sb.append(modelName);
    sb.append('.');
    if (myNodeID.equals("*")) {
      return sb.append('*').toString();
    } else {
      SModel refModel = modelReference.resolve(repository);
      if (refModel != null) {
        SNodeId nodeId = PersistenceFacade.getInstance().createNodeId(myNodeID);
        assert nodeId != null : "wrong node id string";
        SNode mappingConfig = refModel.getNode(nodeId);
        if (mappingConfig != null) {
          return sb.append(mappingConfig.getName()).toString();
        }
      }
    }
    return sb.append(myNodeID).append("!unresolved!").toString();
  }

  @Override
  public String asString() {
    final SModelReference modelReference = PersistenceFacade.getInstance().createModelReference(myModelUID);
    String modelName = modelReference.getName().getLongName();
    StringBuilder sb = new StringBuilder();
    sb.append(modelName);
    sb.append('.');
    if (myNodeID.equals("*")) {
      sb.append('*');
    } else {
      sb.append(myConfigName == null ? myNodeID : myConfigName);
    }
    return sb.toString();
  }

  @Override
  public int hashCode() {
    // ignore myConfigName, it's not relevant for matching
    return Objects.hash(myModelUID, myNodeID);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof MappingConfig_SimpleRef) {
      MappingConfig_SimpleRef r = ((MappingConfig_SimpleRef) obj);
      return Objects.equals(myModelUID, r.myModelUID) && Objects.equals(myNodeID, r.myNodeID);
    }
    return false;
  }
}
