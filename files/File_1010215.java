/*
 * Copyright 2003-2015 JetBrains s.r.o.
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
package jetbrains.mps.persistence.java.library;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.java.stub.JavaPackageModelId;
import jetbrains.mps.smodel.LanguageID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

/**
 * evgeny, 12/13/12
 */
public class JavaClassesPersistence implements CoreComponent {
  private final PersistenceFacade myFacade;

  public JavaClassesPersistence(@NotNull PersistenceFacade persistenceFacade) {
    myFacade = persistenceFacade;
  }
  @Override
  public void init() {
    myFacade.setModelRootFactory(JavaClassStubConstants.STUB_TYPE, new JavaClassStubModelRootFactory());
    myFacade.setModelIdFactory(LanguageID.JAVA, new JavaPackageModelId.Factory());
  }

  @Override
  public void dispose() {
    myFacade.setModelIdFactory(LanguageID.JAVA, null);
    myFacade.setModelRootFactory(JavaClassStubConstants.STUB_TYPE, null);
  }
}
