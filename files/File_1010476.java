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

import jetbrains.mps.extapi.model.GeneratableSModel;
import jetbrains.mps.extapi.model.SModelBase;
import jetbrains.mps.extapi.model.SModelData;
import jetbrains.mps.extapi.persistence.FolderDataSource;
import jetbrains.mps.extapi.persistence.datasource.PreinstalledDataSourceTypes;
import jetbrains.mps.project.MPSExtentions;
import jetbrains.mps.smodel.DefaultSModelDescriptor;
import jetbrains.mps.smodel.SModelHeader;
import jetbrains.mps.smodel.SModelId;
import jetbrains.mps.smodel.loading.ModelLoadResult;
import jetbrains.mps.smodel.loading.ModelLoadingState;
import jetbrains.mps.smodel.persistence.def.FilePerRootFormatUtil;
import jetbrains.mps.smodel.persistence.def.ModelPersistence;
import jetbrains.mps.smodel.persistence.def.ModelReadException;
import jetbrains.mps.util.FileUtil;
import jetbrains.mps.vfs.IFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
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
import org.jetbrains.mps.openapi.persistence.UnsupportedDataSourceException;
import org.jetbrains.mps.openapi.persistence.datasource.DataSourceType;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * evgeny, 6/3/13
 */
public class FilePerRootModelFactory implements ModelFactory, IndexAwareModelFactory {
  private static final Logger LOG = LogManager.getLogger(FilePerRootModelFactory.class);

  @NotNull
  private static PersistenceFacade FACADE() {
    return PersistenceFacade.getInstance();
  }

  @Internal
  public FilePerRootModelFactory() {
    // do not delete, it is a java service
  }

  /**
   * see BinaryModelPersistence#createFromHeader() for details, same motivation here
   */
  public static SModel createFromHeader(@NotNull SModelHeader header, @NotNull FilePerRootDataSource dataSource) {
    final ModelFactory modelFactory = PersistenceFacade.getInstance().getModelFactory(MPSExtentions.MODEL_HEADER);
    assert modelFactory instanceof FilePerRootModelFactory;
    return new DefaultSModelDescriptor(new PersistenceFacility((FilePerRootModelFactory) modelFactory, dataSource), header.createCopy());
  }

  @Override
  public boolean canCreate(@NotNull DataSource dataSource, @NotNull Map<String, String> options) {
    return dataSource instanceof MultiStreamDataSource;
  }

  @Override
  public boolean supports(@NotNull DataSource dataSource) {
    return dataSource instanceof MultiStreamDataSource;
  }

  @NotNull
  @Override
  public SModel create(@NotNull DataSource dataSource, @NotNull SModelName modelName, @NotNull ModelLoadingOption... options) throws
                                                                                                                              UnsupportedDataSourceException {
    if (!supports(dataSource)) {
      throw new UnsupportedDataSourceException(dataSource);
    }

    SModelReference ref = FACADE().createModelReference(null, SModelId.generate(), modelName.getValue());
    final SModelHeader header = SModelHeader.create(ModelPersistence.LAST_VERSION);
    header.setModelReference(ref);
    return new DefaultSModelDescriptor(new PersistenceFacility(this, (MultiStreamDataSource) dataSource), header);
  }

  @NotNull
  @Override
  public SModel load(@NotNull DataSource dataSource, @NotNull ModelLoadingOption... options) throws UnsupportedDataSourceException,
                                                                                                    ModelLoadException {
    if (!supports(dataSource)) {
      throw new UnsupportedDataSourceException(dataSource);
    }

    MultiStreamDataSource source = (MultiStreamDataSource) dataSource;
    PersistenceFacility pf = new PersistenceFacility(this, source);
    SModelHeader header;
    try {
      header = pf.readHeader();
    } catch (ModelReadException ignored) {
      LOG.error("Can't read model: ", ignored);
      throw new ModelLoadException("Can't read model: ", Collections.emptyList(), ignored);
    }

    if (header.getModelReference() == null) {
      throw new ModelLoadException("Could not find model reference in the model header while loading from the " + source);
    }

    LOG.debug("Getting model " + header.getModelReference() + " from " + source.getLocation());
    return new DefaultSModelDescriptor(pf, header);
  }

  @Override
  public boolean needsUpgrade(@NotNull DataSource dataSource) throws IOException {
    if (!supports(dataSource)) {
      throw new UnsupportedDataSourceException(dataSource);
    }

    InputStream in = null;
    try {
      in = ((MultiStreamDataSource) dataSource).openInputStream(FilePerRootDataSource.HEADER_FILE);
      InputSource source = new InputSource(new InputStreamReader(in, FileUtil.DEFAULT_CHARSET));

      // FIXME replace with SingleStreamSource
      SModelHeader header = ModelPersistence.loadDescriptor(source);
      return header.getPersistenceVersion() < ModelPersistence.LAST_VERSION;
    } catch (ModelReadException ex) {
      throw new IOException(ex);
    } finally {
      FileUtil.closeFileSafe(in);
    }
  }

  @Override
  public void upgrade(@NotNull DataSource dataSource) throws IOException {
    if (!supports(dataSource)) {
      throw new UnsupportedDataSourceException(dataSource);
    }

    MultiStreamDataSource source = (MultiStreamDataSource) dataSource;
    try {
      ModelLoadResult model = FilePerRootFormatUtil.readModel(null, source, ModelLoadingState.FULLY_LOADED);
      FilePerRootFormatUtil.saveModel(model.getModel(), source, ModelPersistence.LAST_VERSION);
    } catch (ModelReadException ex) {
      throw new IOException(ex.getMessage(), ex);
    }
  }

  @Override
  public void save(@NotNull SModel model, @NotNull DataSource dataSource) throws IOException {
    if (!supports(dataSource)) {
      throw new UnsupportedDataSourceException(dataSource);
    }

    FilePerRootFormatUtil.saveModel(((SModelBase) model).getSModel(), (MultiStreamDataSource) dataSource,
        ModelPersistence.LAST_VERSION);
  }

  @NotNull
  @Override
  public ModelFactoryType getType() {
    return PreinstalledModelFactoryTypes.PER_ROOT_XML;
  }

  @NotNull
  @Override
  public List<DataSourceType> getPreferredDataSourceTypes() {
    return Arrays.asList(PreinstalledDataSourceTypes.MODEL,
                         PreinstalledDataSourceTypes.FOLDER,
                         PreinstalledDataSourceTypes.MODEL_ROOT);
  }

  public static Map<String, String> getModelHashes(@NotNull MultiStreamDataSource source) {
    BigInteger fileHash = BigInteger.ZERO;
    Map<String, String> result = new HashMap<>();
    for (String streamName : source.getAvailableStreams()) {
      Map<String, String> generationHashes = null;
      if (source instanceof FolderDataSource) {
        IFile file = ((FolderDataSource) source).getFile(streamName);
        generationHashes = file == null ? null : ModelDigestHelper.getInstance().getGenerationHashes(file);
      }
      if (generationHashes == null) {
        generationHashes = DefaultModelPersistence.getDigestMap(source, streamName);
        if (generationHashes == null) {
          // no hash for stream
          return null;
        }
      }

      String streamHash = generationHashes.get(GeneratableSModel.FILE);
      if (streamName.equals(FilePerRootDataSource.HEADER_FILE)) {
        result.put(GeneratableSModel.HEADER, streamHash);
      } else {
        // copy root keys
        for (Entry<String, String> entry : generationHashes.entrySet()) {
          String key = entry.getKey();
          if (GeneratableSModel.FILE.equals(key) || GeneratableSModel.HEADER.equals(key)) continue;
          result.put(entry.getKey(), entry.getValue());
        }
      }
      fileHash = fileHash.xor(new BigInteger(streamHash, Character.MAX_RADIX));
    }
    result.put(GeneratableSModel.FILE, fileHash.toString(Character.MAX_RADIX));
    return result;
  }

  @Override
  public void index(@NotNull InputStream input, @NotNull Callback callback) throws IOException {
    ModelPersistence.index(input, callback);
  }

  @Override
  public SModelData parseSingleStream(@NotNull String name, @NotNull InputStream input) throws IOException {
    return ModelPersistence.getModelData(input);
  }


  private static class PersistenceFacility extends LazyLoadFacility {
    public PersistenceFacility(@NotNull FilePerRootModelFactory modelFactory, @NotNull MultiStreamDataSource dataSource) {
      super(modelFactory, dataSource, true);
    }

    @NotNull
    private MultiStreamDataSource getSource0() {
      return (MultiStreamDataSource) super.getSource();
    }

    @Override
    public String getModelHash() {
      Map<String, String> genHashes = FilePerRootModelFactory.getModelHashes(getSource0());
      if (genHashes == null) {
        // I/O problem, hash is not available
        return null;
      }

      return genHashes.get(GeneratableSModel.FILE);
    }

    @NotNull
    @Override
    public SModelHeader readHeader() throws ModelReadException {
      return FilePerRootFormatUtil.loadDescriptor(getSource0());
    }

    @NotNull
    @Override
    public ModelLoadResult readModel(@NotNull SModelHeader header, @NotNull ModelLoadingState state) throws ModelReadException {
      return FilePerRootFormatUtil.readModel(header, getSource0(), state);
    }

    @Override
    public boolean doesSaveUpgradePersistence(@NotNull SModelHeader header) {
      return FilePerRootFormatUtil.actualPersistenceVersion(header.getPersistenceVersion()) != header.getPersistenceVersion();
    }

    @Override
    public void saveModel(@NotNull SModelHeader header, SModelData modelData) throws IOException {
      FilePerRootFormatUtil.saveModel((jetbrains.mps.smodel.SModel) modelData, getSource0(), header.getPersistenceVersion());
    }
  }
}
