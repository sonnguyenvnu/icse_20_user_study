/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.core.aspects.behaviour;

import jetbrains.mps.core.aspects.behaviour.api.BehaviorRegistry;
import jetbrains.mps.core.aspects.behaviour.api.SAbstractType;
import jetbrains.mps.core.aspects.behaviour.api.SMethod;
import jetbrains.mps.core.aspects.behaviour.api.SParameter;
import jetbrains.mps.smodel.language.ConceptRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SAbstractConcept;

import java.util.Arrays;
import java.util.List;

/**
 * Standard builder for SMethod.
 * Used in the generated behavior descriptors.
 */
public final class SMethodBuilder<T> {
  private String myName;
  private SModifiersImpl myModifiers;
  private final SAbstractType myReturnType;
  private SAbstractConcept myConcept;
  private String myId64; // base = 64
  private BehaviorRegistry myRegistry;

  public SMethodBuilder(SAbstractType returnType) {
    myReturnType = returnType;
  }

  public SMethod<T> build(SParameter... paramTypes) {
    return build(Arrays.asList(paramTypes));
  }

  public SMethod<T> build(List<SParameter> paramTypes) {
    SMethodTrimmedId methodId = SMethodTrimmedId.create("", myModifiers.isVirtual() ? null : myConcept, myId64);
    final BehaviorRegistry registry = myRegistry != null ? myRegistry : ConceptRegistry.getInstance().getBehaviorRegistry();
    return SMethodImpl.create(myName, myModifiers, myReturnType, myConcept, methodId, registry, paramTypes);
  }

  public SMethodBuilder<T> name(@NotNull String name) {
    myName = name;
    return this;
  }

  public SMethodBuilder<T> modifiers(@NotNull SModifiersImpl modifiers) {
    myModifiers = modifiers;
    return this;
  }

  public SMethodBuilder<T> concept(@NotNull SAbstractConcept concept) {
    myConcept = concept;
    return this;
  }

  public SMethodBuilder<T> id(@NotNull String id) {
    myId64 = id;
    return this;
  }

  public SMethodBuilder<T> registry(@NotNull BehaviorRegistry registry) {
    myRegistry = registry;
    return this;
  }

  public int a() {
    return 1;
  }
  public <T1> T1 foo(T1 t) {
    return (T1) ((Integer) a());
  }

  public static SParameter createVarArgPrm(Class<?> aClass, String name) {
    return new SVarArgParameter(new SJavaCompoundTypeImpl(aClass), name);
  }

  public static SParameter createJavaParameter(Class<?> aClass, String name) {
    return new SParameterImpl(new SJavaCompoundTypeImpl(aClass), name);
  }
}
