/*
 * Copyright 2003-2016 JetBrains s.r.o.
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

import jetbrains.mps.generator.runtime.TemplateMappingConfigRef;
import jetbrains.mps.project.structure.modules.Copyable;
import jetbrains.mps.util.io.ModelInputStream;
import jetbrains.mps.util.io.ModelOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SRepository;

import java.io.IOException;
import java.util.List;

public class MappingConfig_AbstractRef implements TemplateMappingConfigRef, Copyable<MappingConfig_AbstractRef> {
  public static final int PERSISTENCE_ID = 0x55550000;

  public boolean isIncomplete() {
    return true;
  }

  public boolean updateReferences(SRepository repository) {
    return false;
  }

  public boolean removeModelReference(SModelReference ref, boolean[] mappingsChanged) {
    return false;
  }

  /**
   * Design-time {@code toString()}
   */
  public String asString(SRepository repository) {
    return getClass().getName();
  }

  /**
   * Deployment-time {@code toString()}
   */
  public String asString() {
    return getClass().getName();
  }

  public static void save(MappingConfig_AbstractRef ref, ModelOutputStream stream) throws IOException {
    if (ref == null) {
      stream.writeInt(0x70);

    } else if (ref instanceof MappingConfig_RefAllGlobal) {
      stream.writeInt(MappingConfig_RefAllGlobal.PERSISTENCE_ID);

    } else if (ref instanceof MappingConfig_RefAllLocal) {
      stream.writeInt(MappingConfig_RefAllLocal.PERSISTENCE_ID);

    } else if (ref instanceof MappingConfig_SimpleRef) {
      MappingConfig_SimpleRef simpleRef = (MappingConfig_SimpleRef) ref;
      stream.writeInt(MappingConfig_SimpleRef.PERSISTENCE_ID);
      stream.writeString(simpleRef.getModelUID());
      stream.writeString(simpleRef.getNodeID());

    } else if (ref instanceof MappingConfig_ExternalRef) {
      stream.writeInt(MappingConfig_ExternalRef.PERSISTENCE_ID);
      MappingConfig_ExternalRef extRef = (MappingConfig_ExternalRef) ref;
      stream.writeModuleReference(extRef.getGenerator());
      save(extRef.getMappingConfig(), stream);

    } else if (ref instanceof MappingConfig_RefSet) {
      stream.writeInt(MappingConfig_RefSet.PERSISTENCE_ID);
      List<MappingConfig_AbstractRef> list = ((MappingConfig_RefSet) ref).getMappingConfigs();
      stream.writeInt(list.size());
      for (MappingConfig_AbstractRef inner : list) {
        save(inner, stream);
      }
    } else {
      stream.writeInt(PERSISTENCE_ID);
    }
  }

  public static MappingConfig_AbstractRef load(ModelInputStream stream) throws IOException {
    int cl = stream.readInt();

    if (cl == PERSISTENCE_ID) {
      return new MappingConfig_AbstractRef();

    } else if (cl == MappingConfig_RefAllGlobal.PERSISTENCE_ID) {
      return new MappingConfig_RefAllGlobal();

    } else if (cl == MappingConfig_RefAllLocal.PERSISTENCE_ID) {
      return new MappingConfig_RefAllLocal();

    } else if (cl == MappingConfig_SimpleRef.PERSISTENCE_ID) {
      MappingConfig_SimpleRef simpleRef = new MappingConfig_SimpleRef();
      simpleRef.setModelUID(stream.readString());
      simpleRef.setNodeID(stream.readString());
      return simpleRef;

    } else if (cl == MappingConfig_ExternalRef.PERSISTENCE_ID) {
      MappingConfig_ExternalRef result = new MappingConfig_ExternalRef();
      result.setGenerator(stream.readModuleReference());
      result.setMappingConfig(load(stream));
      return result;

    } else if (cl == MappingConfig_RefSet.PERSISTENCE_ID) {
      MappingConfig_RefSet result = new MappingConfig_RefSet();
      for (int size = stream.readInt(); size > 0; size--) {
        result.getMappingConfigs().add(load(stream));
      }
      return result;

    } else if (cl == 0x70) {
      return null;
    }

    throw new IOException("bad stream");
  }

  @NotNull
  @Override
  public MappingConfig_AbstractRef copy() {
    return new MappingConfig_AbstractRef();
  }
}
