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
package jetbrains.mps.persistence;

import jetbrains.mps.extapi.model.SModelBase;
import jetbrains.mps.extapi.model.SModelData;
import jetbrains.mps.extapi.persistence.datasource.PreinstalledDataSourceTypes;
import jetbrains.mps.persistence.MetaModelInfoProvider.MetaInfoLoadingOption;
import jetbrains.mps.persistence.MetaModelInfoProvider.RegularMetaModelInfo;
import jetbrains.mps.persistence.MetaModelInfoProvider.StuffedMetaModelInfo;
import jetbrains.mps.project.MPSExtentions;
import jetbrains.mps.smodel.DefaultSModel;
import jetbrains.mps.smodel.DefaultSModelDescriptor;
import jetbrains.mps.smodel.SModelHeader;
import jetbrains.mps.smodel.SModelId;
import jetbrains.mps.smodel.loading.ModelLoadResult;
import jetbrains.mps.smodel.loading.ModelLoadingState;
import jetbrains.mps.smodel.persistence.def.ModelPersistence;
import jetbrains.mps.smodel.persistence.def.ModelReadException;
import jetbrains.mps.util.FileUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Internal;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelName;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.persistence.DataSource;
import org.jetbrains.mps.openapi.persistence.ModelFactory;
import org.jetbrains.mps.openapi.persistence.ModelFactoryType;
import org.jetbrains.mps.openapi.persistence.ModelLoadException;
import org.jetbrains.mps.openapi.persistence.ModelLoadingOption;
import org.jetbrains.mps.openapi.persistence.MultiStreamDataSource;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;
import org.jetbrains.mps.openapi.persistence.StreamDataSource;
import org.jetbrains.mps.openapi.persistence.UnsupportedDataSourceException;
import org.jetbrains.mps.openapi.persistence.datasource.DataSourceType;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Factory for models stored in .mps files.
 */
public class DefaultModelPersistence implements ModelFactory, IndexAwareModelFactory {
  private static final Logger LOG = LogManager.getLogger(DefaultModelPersistence.class);

  public enum ContentLoadingExtentOptions implements ModelLoadingOption {
    /**
     * An option for model loading, indicates loaded model doesn't care about implementation node.
     * For the time being, implementation node is the one with appropriate ConceptKind (designated according to concept's implemented interfaces).
     */
    STRIP_IMPLEMENTATION,
    /**
     * Boolean option for model loading, indicates loaded model cares about its interface aspects only.
     *
     */
    INTERFACE_ONLY
  }

  @Internal
  public DefaultModelPersistence() {
    // do not delete, it is a java service
  }

  @Override
  public boolean supports(@NotNull DataSource dataSource) {
    return dataSource instanceof StreamDataSource;
  }

  @NotNull
  @Override
  public SModel create(@NotNull DataSource dataSource,
                       @NotNull SModelName modelName,
                       @NotNull ModelLoadingOption... options) throws UnsupportedDataSourceException {
    if (!(supports(dataSource))) {
      throw new UnsupportedDataSourceException(dataSource);
    }

    final SModelHeader header = SModelHeader.create(ModelPersistence.LAST_VERSION);
    final SModelReference modelReference = PersistenceFacade.getInstance().createModelReference(null, SModelId.generate(), modelName.getValue());
    header.setModelReference(modelReference);
    final DefaultSModelDescriptor rv = new DefaultSModelDescriptor(new PersistenceFacility(this, (StreamDataSource) dataSource), header);
    // Hack to ensure newly created model is indeed empty. Otherwise, with StreamDataSource pointing to existing model stream, an attempt to
    // do anything with the model triggers loading and the model get all the data. Two approaches deemed reasonable to tackle the issue:
    // (a) enforce clear empty model (why would anyone call #create() then)
    // (b) fail with error (too brutal?)
    // Another alternative considered is to tolerate any DataSource in DefaultSModelDescriptor (or its persistence counterpart), so that
    // one can create an empty model with NullDataSource, and later save with a proper DataSource (which yields more job to client and makes him
    // question why SModel.save() is there). This task is reasonable regardless of final approach taken, but would take more effort, hence the hack.
    if (dataSource.getTimestamp() != -1) { // chances are there's something in the stream already
      rv.replace(new DefaultSModel(modelReference, header)); // model state is FULLY_LOADED, DataSource won't get read
    }
    return rv;
  }

  @NotNull
  @Override
  public SModel load(@NotNull DataSource dataSource, @NotNull ModelLoadingOption... options) throws UnsupportedDataSourceException,
                                                                                                    ModelLoadException {
    if (!(dataSource instanceof StreamDataSource)) {
      throw new UnsupportedDataSourceException(dataSource);
    }

    StreamDataSource source = (StreamDataSource) dataSource;
    PersistenceFacility persistenceFacility = new PersistenceFacility(this, source);
    SModelHeader header = readHeader(dataSource, source, persistenceFacility);
    LOG.debug("Getting model " + header.getModelReference() + " from " + dataSource.getLocation());

    if (Arrays.asList(options).contains(MetaInfoLoadingOption.KEEP_READ)) {
      header.setMetaInfoProvider(new StuffedMetaModelInfo(new RegularMetaModelInfo()));
    }

    // If there are any load options, process them and fill the model with desired model data, otherwise return a lightweight descriptor.
    final DefaultSModelDescriptor resultingModel = new DefaultSModelDescriptor(persistenceFacility, header);
    ModelLoadingState loadingLevel = detectLoadingLevel(options);
    readModelUpToLevel(dataSource, persistenceFacility, header, resultingModel, loadingLevel);
    return resultingModel;
  }

  private void readModelUpToLevel(@NotNull DataSource dataSource,
                                  PersistenceFacility persistenceFacility,
                                  SModelHeader header,
                                  DefaultSModelDescriptor rv,
                                  ModelLoadingState loadingLevel) throws ModelLoadException {
    if (loadingLevel != null) {
      try {
        jetbrains.mps.smodel.SModel md = persistenceFacility.readModel(header, loadingLevel).getModel();
        rv.replace(md);
      } catch (ModelReadException e) {
        LOG.error("Can't read model: ", e);
        throw new ModelLoadException("Can't read a model from the '" + dataSource + "'", Collections.emptyList(), e);
      }
    }
  }

  @NotNull
  private SModelHeader readHeader(@NotNull DataSource dataSource, StreamDataSource source, PersistenceFacility pf) throws ModelLoadException {
    SModelHeader header;
    try {
      header = pf.readHeader();
    } catch (ModelReadException e) {
      LOG.error("Can't read model: ", e);
      throw new ModelLoadException("Can't read model header from the '" + dataSource + "'", Collections.emptyList(), e);
    }
    if (header.getModelReference() == null) {
      throw new ModelLoadException("Could not find model reference in the model header while loading from the " + source);
    }
    return header;
  }

  /**
   * An alternative to replace() method call (which is hacky) is to expose UpdateableModel field from LazyEditableSModelBase and use
   * UpdateableModel#getModel(ModelLoadingState) instead to ensure model is loaded to desired state.
   * However, not sure subsequent access to model won't trigger full load anyway, thus replace() which indicates supplied state is 'FULLY LOADED'
   * might be the right (hacky, nonetheless) solution.
   * [atikhomirov]
   */
  @Nullable
  private ModelLoadingState detectLoadingLevel(@NotNull ModelLoadingOption[] options) {
    ModelLoadingState loadingLevel = null;
    if (Arrays.asList(options).contains(ContentLoadingExtentOptions.STRIP_IMPLEMENTATION)) {
      loadingLevel = ModelLoadingState.NO_IMPLEMENTATION;
    } else if (Arrays.asList(options).contains(ContentLoadingExtentOptions.INTERFACE_ONLY)) {
      loadingLevel = ModelLoadingState.INTERFACE_LOADED;
    }
    return loadingLevel;
  }

  @Override
  public boolean needsUpgrade(@NotNull DataSource dataSource) throws IOException {
    if (!(dataSource instanceof StreamDataSource)) {
      throw new UnsupportedDataSourceException(dataSource);
    }
    try {

      SModelHeader header = ModelPersistence.loadDescriptor((StreamDataSource) dataSource);
      return header.getPersistenceVersion() < ModelPersistence.LAST_VERSION;
    } catch (ModelReadException ex) {
      throw new IOException(ex);
    }
  }

  @Override
  public void upgrade(@NotNull DataSource dataSource) throws IOException {
    if (!(dataSource instanceof StreamDataSource)) {
      throw new UnsupportedDataSourceException(dataSource);
    }

    StreamDataSource source = (StreamDataSource) dataSource;
    try {
      DefaultSModel model = ModelPersistence.readModel(source, false);
      ModelPersistence.saveModel(model, source, ModelPersistence.LAST_VERSION);
    } catch (ModelReadException ex) {
      throw new IOException(ex.getMessage(), ex);
    }
  }

  @Override
  public void save(@NotNull SModel model, @NotNull DataSource dataSource) throws IOException {
    if (!(dataSource instanceof StreamDataSource)) {
      throw new UnsupportedDataSourceException(dataSource);
    }
    int persistenceVersion = -1;
    if (model instanceof PersistenceVersionAware) {
      persistenceVersion = ((PersistenceVersionAware) model).getPersistenceVersion();
    }
    if (persistenceVersion == -1) {
      persistenceVersion = ModelPersistence.LAST_VERSION;
    }

    ModelPersistence.saveModel(((SModelBase) model).getSModel(), (StreamDataSource) dataSource, persistenceVersion);
  }

  @Override
  public void index(@NotNull InputStream input, @NotNull Callback callback) throws IOException {
    ModelPersistence.index(input, callback);
  }

  @Override
  public SModelData parseSingleStream(@NotNull String name, @NotNull InputStream input) throws IOException {
    return ModelPersistence.getModelData(input);
  }

  @NotNull
  @Override
  public ModelFactoryType getType() {
    return PreinstalledModelFactoryTypes.PLAIN_XML;
  }

  @NotNull
  @Override
  public List<DataSourceType> getPreferredDataSourceTypes() {
    return Collections.singletonList(PreinstalledDataSourceTypes.MPS);
  }

  public static Map<String, String> getDigestMap(@NotNull MultiStreamDataSource source, String streamName) {
    InputStream is = null;
    try {
      is = source.openInputStream(streamName);
      return getDigestMap(new InputStreamReader(is, FileUtil.DEFAULT_CHARSET));
    } catch (IOException e) {
      /* ignore */
    } finally {
      FileUtil.closeFileSafe(is);
    }
    return null;
  }

  public static Map<String, String> getDigestMap(@NotNull StreamDataSource source) {
    InputStream is = null;
    try {
      is = source.openInputStream();
      return getDigestMap(new InputStreamReader(is, FileUtil.DEFAULT_CHARSET));
    } catch (IOException e) {
      /* ignore */
    } finally {
      FileUtil.closeFileSafe(is);
    }
    return null;
  }

  public static Map<String, String> getDigestMap(Reader input) {
    try {
      return ModelPersistence.calculateHashes(FileUtil.read(input));
    } catch (ModelReadException e) {
      return null;
    }
  }

  /**
   * hack, @see BinaryModelPersistence#createFromHeader for details
   */
  public static SModel createFromHeader(@NotNull SModelHeader header, @NotNull StreamDataSource dataSource) {
    final ModelFactory modelFactory = PersistenceFacade.getInstance().getModelFactory(MPSExtentions.MODEL);
    assert modelFactory instanceof DefaultModelPersistence;
    return new DefaultSModelDescriptor(new PersistenceFacility((DefaultModelPersistence) modelFactory, dataSource), header.createCopy());
  }

  private static class PersistenceFacility extends LazyLoadFacility {
    /*package*/ PersistenceFacility(DefaultModelPersistence modelFactory, StreamDataSource dataSource) {
      super(modelFactory, dataSource, true);
    }

    @NotNull
    private StreamDataSource getSource0() {
      return (StreamDataSource) super.getSource();
    }

    @NotNull
    @Override
    public SModelHeader readHeader() throws ModelReadException {
      return ModelPersistence.loadDescriptor(getSource0());
    }

    @NotNull
    @Override
    public ModelLoadResult readModel(@NotNull SModelHeader header, @NotNull ModelLoadingState state) throws ModelReadException {
      return ModelPersistence.readModel(header, getSource0(), state);
    }

    @Override
    public boolean doesSaveUpgradePersistence(@NotNull SModelHeader header) {
      //not sure !=-1 is really needed, just left to be ensured about compatibility
      return header.getPersistenceVersion() != ModelPersistence.LAST_VERSION && header.getPersistenceVersion() != -1;
    }

    @Override
    public void saveModel(@NotNull SModelHeader header, SModelData modelData) throws IOException {
      ModelPersistence.saveModel((jetbrains.mps.smodel.SModel) modelData, getSource0(), header.getPersistenceVersion());
    }
  }
}
