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
import org.jetbrains.mps.openapi.module.SRepository;

public class MappingConfig_RefAllLocal extends MappingConfig_AbstractRef {
  public static final int PERSISTENCE_ID = 0x55550001;

  @NotNull
  @Override
  public MappingConfig_AbstractRef copy() {
    return new MappingConfig_RefAllLocal();
  }

  @Override
  public boolean isIncomplete() {
    return false;
  }

  @Override
  public String asString(SRepository repository) {
    return "*";
  }

  @Override
  public String asString() {
    return "*";
  }

  @Override
  public int hashCode() {
    return MappingConfig_RefAllLocal.class.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof MappingConfig_RefAllLocal;
  }
}
