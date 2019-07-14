/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.cuda;

import javax.annotation.*;

import java.nio.*;

import org.lwjgl.*;

import org.lwjgl.system.*;

import static org.lwjgl.system.APIUtil.*;
import static org.lwjgl.system.Checks.*;
import static org.lwjgl.system.JNI.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.Pointer.*;

import static org.lwjgl.cuda.CUDA.*;

/**
 * Contains bindings to the <a href="https://docs.nvidia.com/cuda/cuda-driver-api/index.html">CUDA Driver API</a>.
 * 
 * <p>This class includes functionality up to CUDA version 3.2, which is the minimum version compatible with the LWJGL bindings.</p>
 */
public class CU {

    /**
     * Flags for {@link #cuMemHostAlloc MemHostAlloc}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_MEMHOSTALLOC_PORTABLE MEMHOSTALLOC_PORTABLE} - If set, host memory is portable between CUDA contexts.</li>
     * <li>{@link #CU_MEMHOSTALLOC_DEVICEMAP MEMHOSTALLOC_DEVICEMAP} - If set, host memory is mapped into CUDA address space and {@link #cuMemHostGetDevicePointer MemHostGetDevicePointer} may be called on the host pointer.</li>
     * <li>{@link #CU_MEMHOSTALLOC_WRITECOMBINED MEMHOSTALLOC_WRITECOMBINED} - 
     * If set, host memory is allocated as write-combined - fast to write, faster to DMA, slow to read except via SSE4 streaming load instruction
     * ({@code MOVNTDQA}).
     * </li>
     * </ul>
     */
    public static final int
        CU_MEMHOSTALLOC_PORTABLE      = 0x1,
        CU_MEMHOSTALLOC_DEVICEMAP     = 0x2,
        CU_MEMHOSTALLOC_WRITECOMBINED = 0x4;

    /**
     * Flags for {@link CU40#cuMemHostRegister MemHostRegister}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_MEMHOSTREGISTER_PORTABLE MEMHOSTREGISTER_PORTABLE} - If set, host memory is portable between CUDA contexts.</li>
     * <li>{@link #CU_MEMHOSTREGISTER_DEVICEMAP MEMHOSTREGISTER_DEVICEMAP} - If set, host memory is mapped into CUDA address space and {@link #cuMemHostGetDevicePointer MemHostGetDevicePointer} may be called on the host pointer.</li>
     * <li>{@link #CU_MEMHOSTREGISTER_IOMEMORY MEMHOSTREGISTER_IOMEMORY} - 
     * If set, the passed memory pointer is treated as pointing to some memory-mapped I/O space, e.g. belonging to a third-party PCIe device.
     * 
     * <p>On Windows the flag is a no-op. On Linux that memory is marked as non cache-coherent for the GPU and is expected to be physically contiguous.
     * It may return {@link #CUDA_ERROR_NOT_PERMITTED} if run as an unprivileged user, {@link #CUDA_ERROR_NOT_SUPPORTED} on older Linux kernel versions. On all other
     * platforms, it is not supported and {@link #CUDA_ERROR_NOT_SUPPORTED} is returned.</p>
     * </li>
     * </ul>
     */
    public static final int
        CU_MEMHOSTREGISTER_PORTABLE  = 0x1,
        CU_MEMHOSTREGISTER_DEVICEMAP = 0x2,
        CU_MEMHOSTREGISTER_IOMEMORY  = 0x4;

    /**
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CUDA_ARRAY3D_LAYERED CUDA_ARRAY3D_LAYERED} - 
     * If set, the CUDA array is a collection of layers, where each layer is either a 1D or a 2D array and the Depth member of {@link CUDA_ARRAY3D_DESCRIPTOR}
     * specifies the number of layers, not the depth of a 3D array.
     * </li>
     * <li>{@link #CUDA_ARRAY3D_2DARRAY CUDA_ARRAY3D_2DARRAY} - Deprecated, use {@link #CUDA_ARRAY3D_LAYERED}.</li>
     * <li>{@link #CUDA_ARRAY3D_SURFACE_LDST CUDA_ARRAY3D_SURFACE_LDST} - This flag must be set in order to bind a surface reference to the CUDA array.</li>
     * <li>{@link #CUDA_ARRAY3D_CUBEMAP CUDA_ARRAY3D_CUBEMAP} - 
     * If set, the CUDA array is a collection of six 2D arrays, representing faces of a cube. The width of such a CUDA array must be equal to its height,
     * and Depth must be six. If {@link #CUDA_ARRAY3D_LAYERED} flag is also set, then the CUDA array is a collection of cubemaps and Depth must be a multiple of
     * six.
     * </li>
     * <li>{@link #CUDA_ARRAY3D_TEXTURE_GATHER CUDA_ARRAY3D_TEXTURE_GATHER} - This flag must be set in order to perform texture gather operations on a CUDA array.</li>
     * <li>{@link #CUDA_ARRAY3D_DEPTH_TEXTURE CUDA_ARRAY3D_DEPTH_TEXTURE} - This flag if set indicates that the CUDA array is a DEPTH_TEXTURE.</li>
     * <li>{@link #CUDA_ARRAY3D_COLOR_ATTACHMENT CUDA_ARRAY3D_COLOR_ATTACHMENT} - This flag indicates that the CUDA array may be bound as a color target in an external graphics API.</li>
     * </ul>
     */
    public static final int
        CUDA_ARRAY3D_LAYERED          = 0x1,
        CUDA_ARRAY3D_2DARRAY          = 0x1,
        CUDA_ARRAY3D_SURFACE_LDST     = 0x2,
        CUDA_ARRAY3D_CUBEMAP          = 0x4,
        CUDA_ARRAY3D_TEXTURE_GATHER   = 0x8,
        CUDA_ARRAY3D_DEPTH_TEXTURE    = 0x10,
        CUDA_ARRAY3D_COLOR_ATTACHMENT = 0x20;

    /**
     * Flag for {@link #cuTexRefSetArray TexRefSetArray}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_TRSA_OVERRIDE_FORMAT TRSA_OVERRIDE_FORMAT} - Override the {@code texref} format with a format inferred from the array.</li>
     * </ul>
     */
    public static final int CU_TRSA_OVERRIDE_FORMAT = 0x1;

    /**
     * Flag for {@link #cuTexRefSetFlags TexRefSetFlags}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_TRSF_READ_AS_INTEGER TRSF_READ_AS_INTEGER} - Read the texture as integers rather than promoting the values to floats in the range {@code [0,1]}.</li>
     * <li>{@link #CU_TRSF_NORMALIZED_COORDINATES TRSF_NORMALIZED_COORDINATES} - Use normalized texture coordinates in the range {@code [0,1)} instead of {@code [0,dim)}.</li>
     * <li>{@link #CU_TRSF_SRGB TRSF_SRGB} - Perform {@code sRGB->linear} conversion during texture read.</li>
     * </ul>
     */
    public static final int
        CU_TRSF_READ_AS_INTEGER        = 0x1,
        CU_TRSF_NORMALIZED_COORDINATES = 0x2,
        CU_TRSF_SRGB                   = 0x10;

    /** For texture references loaded into the module, use default texunit from texture reference. */
    public static final int CU_PARAM_TR_DEFAULT = -1;

    /**
     * Context creation flags. ({@code CUctx_flags})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_CTX_SCHED_AUTO CTX_SCHED_AUTO} - Automatic scheduling</li>
     * <li>{@link #CU_CTX_SCHED_SPIN CTX_SCHED_SPIN} - Set spin as default scheduling</li>
     * <li>{@link #CU_CTX_SCHED_YIELD CTX_SCHED_YIELD} - Set yield as default scheduling</li>
     * <li>{@link #CU_CTX_SCHED_BLOCKING_SYNC CTX_SCHED_BLOCKING_SYNC} - Set blocking synchronization as default scheduling</li>
     * <li>{@link #CU_CTX_BLOCKING_SYNC CTX_BLOCKING_SYNC} - Set blocking synchronization as default scheduling. This flag was deprecated as of CUDA 4.0 and was replaced with {@link #CU_CTX_SCHED_BLOCKING_SYNC CTX_SCHED_BLOCKING_SYNC}.</li>
     * <li>{@link #CU_CTX_SCHED_MASK CTX_SCHED_MASK}</li>
     * <li>{@link #CU_CTX_MAP_HOST CTX_MAP_HOST} - Support mapped pinned allocations</li>
     * <li>{@link #CU_CTX_LMEM_RESIZE_TO_MAX CTX_LMEM_RESIZE_TO_MAX} - Keep local memory allocation after launch</li>
     * <li>{@link #CU_CTX_FLAGS_MASK CTX_FLAGS_MASK}</li>
     * </ul>
     */
    public static final int
        CU_CTX_SCHED_AUTO          = 0x0,
        CU_CTX_SCHED_SPIN          = 0x1,
        CU_CTX_SCHED_YIELD         = 0x2,
        CU_CTX_SCHED_BLOCKING_SYNC = 0x4,
        CU_CTX_BLOCKING_SYNC       = 0x4,
        CU_CTX_SCHED_MASK          = 0x7,
        CU_CTX_MAP_HOST            = 0x8,
        CU_CTX_LMEM_RESIZE_TO_MAX  = 0x10,
        CU_CTX_FLAGS_MASK          = 0x1F;

    /**
     * Stream creation flags. ({@code CUstream_flags})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_STREAM_DEFAULT STREAM_DEFAULT} - Default stream flag</li>
     * <li>{@link #CU_STREAM_NON_BLOCKING STREAM_NON_BLOCKING} - Stream does not synchronize with stream 0 (the {@code NULL} stream)</li>
     * </ul>
     */
    public static final int
        CU_STREAM_DEFAULT      = 0x0,
        CU_STREAM_NON_BLOCKING = 0x1;

    /**
     * Event creation flags. ({@code CUevent_flags})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_EVENT_DEFAULT EVENT_DEFAULT} - Default event flag</li>
     * <li>{@link #CU_EVENT_BLOCKING_SYNC EVENT_BLOCKING_SYNC} - Event uses blocking synchronization</li>
     * <li>{@link #CU_EVENT_DISABLE_TIMING EVENT_DISABLE_TIMING} - Event will not record timing data</li>
     * <li>{@link #CU_EVENT_INTERPROCESS EVENT_INTERPROCESS} - Event is suitable for interprocess use. {@link #CU_EVENT_DISABLE_TIMING EVENT_DISABLE_TIMING} must be set</li>
     * </ul>
     */
    public static final int
        CU_EVENT_DEFAULT        = 0x0,
        CU_EVENT_BLOCKING_SYNC  = 0x1,
        CU_EVENT_DISABLE_TIMING = 0x2,
        CU_EVENT_INTERPROCESS   = 0x4;

    /**
     * Array formats. ({@code CUarray_format})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_AD_FORMAT_UNSIGNED_INT8 AD_FORMAT_UNSIGNED_INT8} - Unsigned 8-bit integers</li>
     * <li>{@link #CU_AD_FORMAT_UNSIGNED_INT16 AD_FORMAT_UNSIGNED_INT16} - Unsigned 16-bit integers</li>
     * <li>{@link #CU_AD_FORMAT_UNSIGNED_INT32 AD_FORMAT_UNSIGNED_INT32} - Unsigned 32-bit integers</li>
     * <li>{@link #CU_AD_FORMAT_SIGNED_INT8 AD_FORMAT_SIGNED_INT8} - Signed 8-bit integers</li>
     * <li>{@link #CU_AD_FORMAT_SIGNED_INT16 AD_FORMAT_SIGNED_INT16} - Signed 16-bit integers</li>
     * <li>{@link #CU_AD_FORMAT_SIGNED_INT32 AD_FORMAT_SIGNED_INT32} - Signed 32-bit integers</li>
     * <li>{@link #CU_AD_FORMAT_HALF AD_FORMAT_HALF} - 16-bit floating point</li>
     * <li>{@link #CU_AD_FORMAT_FLOAT AD_FORMAT_FLOAT} - 32-bit floating point</li>
     * </ul>
     */
    public static final int
        CU_AD_FORMAT_UNSIGNED_INT8  = 0x1,
        CU_AD_FORMAT_UNSIGNED_INT16 = 0x2,
        CU_AD_FORMAT_UNSIGNED_INT32 = 0x3,
        CU_AD_FORMAT_SIGNED_INT8    = 0x8,
        CU_AD_FORMAT_SIGNED_INT16   = 0x9,
        CU_AD_FORMAT_SIGNED_INT32   = 0xA,
        CU_AD_FORMAT_HALF           = 0x10,
        CU_AD_FORMAT_FLOAT          = 0x20;

    /**
     * Texture reference addressing modes. ({@code CUaddress_mode})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_TR_ADDRESS_MODE_WRAP TR_ADDRESS_MODE_WRAP} - Wrapping address mode</li>
     * <li>{@link #CU_TR_ADDRESS_MODE_CLAMP TR_ADDRESS_MODE_CLAMP} - Clamp to edge address mode</li>
     * <li>{@link #CU_TR_ADDRESS_MODE_MIRROR TR_ADDRESS_MODE_MIRROR} - Mirror address mode</li>
     * <li>{@link #CU_TR_ADDRESS_MODE_BORDER TR_ADDRESS_MODE_BORDER} - Border address mode</li>
     * </ul>
     */
    public static final int
        CU_TR_ADDRESS_MODE_WRAP   = 0x0,
        CU_TR_ADDRESS_MODE_CLAMP  = 0x1,
        CU_TR_ADDRESS_MODE_MIRROR = 0x2,
        CU_TR_ADDRESS_MODE_BORDER = 0x3;

    /**
     * Texture reference filtering modes. ({@code CUfilter_mode})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_TR_FILTER_MODE_POINT TR_FILTER_MODE_POINT} - Point filter mode</li>
     * <li>{@link #CU_TR_FILTER_MODE_LINEAR TR_FILTER_MODE_LINEAR} - Linear filter mode</li>
     * </ul>
     */
    public static final int
        CU_TR_FILTER_MODE_POINT  = 0x0,
        CU_TR_FILTER_MODE_LINEAR = 0x1;

    /**
     * Device properties. ({@code CUdevice_attribute})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_THREADS_PER_BLOCK DEVICE_ATTRIBUTE_MAX_THREADS_PER_BLOCK} - Maximum number of threads per block</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_X DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_X} - Maximum block dimension X</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_Y DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_Y} - Maximum block dimension Y</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_Z DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_Z} - Maximum block dimension Z</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_GRID_DIM_X DEVICE_ATTRIBUTE_MAX_GRID_DIM_X} - Maximum grid dimension X</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_GRID_DIM_Y DEVICE_ATTRIBUTE_MAX_GRID_DIM_Y} - Maximum grid dimension Y</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_GRID_DIM_Z DEVICE_ATTRIBUTE_MAX_GRID_DIM_Z} - Maximum grid dimension Z</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_BLOCK DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_BLOCK} - Maximum shared memory available per block in bytes</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_SHARED_MEMORY_PER_BLOCK DEVICE_ATTRIBUTE_SHARED_MEMORY_PER_BLOCK} - Deprecated, use {@link #CU_DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_BLOCK DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_BLOCK}</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_TOTAL_CONSTANT_MEMORY DEVICE_ATTRIBUTE_TOTAL_CONSTANT_MEMORY} - Memory available on device for {@code __constant__} variables in a CUDA C kernel in bytes</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_WARP_SIZE DEVICE_ATTRIBUTE_WARP_SIZE} - Warp size in threads</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_PITCH DEVICE_ATTRIBUTE_MAX_PITCH} - Maximum pitch in bytes allowed by memory copies</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_REGISTERS_PER_BLOCK DEVICE_ATTRIBUTE_MAX_REGISTERS_PER_BLOCK} - Maximum number of 32-bit registers available per block</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_REGISTERS_PER_BLOCK DEVICE_ATTRIBUTE_REGISTERS_PER_BLOCK} - Deprecated, use {@link #CU_DEVICE_ATTRIBUTE_MAX_REGISTERS_PER_BLOCK DEVICE_ATTRIBUTE_MAX_REGISTERS_PER_BLOCK}</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CLOCK_RATE DEVICE_ATTRIBUTE_CLOCK_RATE} - Typical clock frequency in kilohertz</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_TEXTURE_ALIGNMENT DEVICE_ATTRIBUTE_TEXTURE_ALIGNMENT} - Alignment requirement for textures</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_GPU_OVERLAP DEVICE_ATTRIBUTE_GPU_OVERLAP} - Device can possibly copy memory and execute a kernel concurrently. Deprecated. Use instead {@link #CU_DEVICE_ATTRIBUTE_ASYNC_ENGINE_COUNT DEVICE_ATTRIBUTE_ASYNC_ENGINE_COUNT}.</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MULTIPROCESSOR_COUNT DEVICE_ATTRIBUTE_MULTIPROCESSOR_COUNT} - Number of multiprocessors on device</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_KERNEL_EXEC_TIMEOUT DEVICE_ATTRIBUTE_KERNEL_EXEC_TIMEOUT} - Specifies whether there is a run time limit on kernels</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_INTEGRATED DEVICE_ATTRIBUTE_INTEGRATED} - Device is integrated with host memory</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CAN_MAP_HOST_MEMORY DEVICE_ATTRIBUTE_CAN_MAP_HOST_MEMORY} - Device can map host memory into CUDA address space</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_COMPUTE_MODE DEVICE_ATTRIBUTE_COMPUTE_MODE} - Compute mode (See {@code CUcomputemode} for details)</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_WIDTH} - Maximum 1D texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_WIDTH} - Maximum 2D texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_HEIGHT} - Maximum 2D texture height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_WIDTH} - Maximum 3D texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_HEIGHT} - Maximum 3D texture height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_DEPTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_DEPTH} - Maximum 3D texture depth</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_WIDTH} - Maximum 2D layered texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_HEIGHT} - Maximum 2D layered texture height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_LAYERS} - Maximum layers in a 2D layered texture</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_WIDTH} - Deprecated, use {@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_WIDTH}</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_HEIGHT} - Deprecated, use {@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_HEIGHT}</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_NUMSLICES DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_NUMSLICES} - Deprecated, use {@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_LAYERS}</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_SURFACE_ALIGNMENT DEVICE_ATTRIBUTE_SURFACE_ALIGNMENT} - Alignment requirement for surfaces</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CONCURRENT_KERNELS DEVICE_ATTRIBUTE_CONCURRENT_KERNELS} - Device can possibly execute multiple kernels concurrently</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_ECC_ENABLED DEVICE_ATTRIBUTE_ECC_ENABLED} - Device has ECC support enabled</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_PCI_BUS_ID DEVICE_ATTRIBUTE_PCI_BUS_ID} - PCI bus ID of the device</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_PCI_DEVICE_ID DEVICE_ATTRIBUTE_PCI_DEVICE_ID} - PCI device ID of the device</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_TCC_DRIVER DEVICE_ATTRIBUTE_TCC_DRIVER} - Device is using TCC driver model</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MEMORY_CLOCK_RATE DEVICE_ATTRIBUTE_MEMORY_CLOCK_RATE} - Peak memory clock frequency in kilohertz</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_GLOBAL_MEMORY_BUS_WIDTH DEVICE_ATTRIBUTE_GLOBAL_MEMORY_BUS_WIDTH} - Global memory bus width in bits</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_L2_CACHE_SIZE DEVICE_ATTRIBUTE_L2_CACHE_SIZE} - Size of L2 cache in bytes</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_THREADS_PER_MULTIPROCESSOR DEVICE_ATTRIBUTE_MAX_THREADS_PER_MULTIPROCESSOR} - Maximum resident threads per multiprocessor</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_ASYNC_ENGINE_COUNT DEVICE_ATTRIBUTE_ASYNC_ENGINE_COUNT} - Number of asynchronous engines</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_UNIFIED_ADDRESSING DEVICE_ATTRIBUTE_UNIFIED_ADDRESSING} - Device shares a unified address space with the host</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LAYERED_WIDTH} - Maximum 1D layered texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LAYERED_LAYERS} - Maximum layers in a 1D layered texture</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CAN_TEX2D_GATHER DEVICE_ATTRIBUTE_CAN_TEX2D_GATHER} - Deprecated, do not use.</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_GATHER_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_GATHER_WIDTH} - Maximum 2D texture width if {@link #CUDA_ARRAY3D_TEXTURE_GATHER} is set</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_GATHER_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_GATHER_HEIGHT} - Maximum 2D texture height if {@link #CUDA_ARRAY3D_TEXTURE_GATHER} is set</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_WIDTH_ALTERNATE DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_WIDTH_ALTERNATE} - Alternate maximum 3D texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_HEIGHT_ALTERNATE DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_HEIGHT_ALTERNATE} - Alternate maximum 3D texture height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_DEPTH_ALTERNATE DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_DEPTH_ALTERNATE} - Alternate maximum 3D texture depth</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_PCI_DOMAIN_ID DEVICE_ATTRIBUTE_PCI_DOMAIN_ID} - PCI domain ID of the device</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_TEXTURE_PITCH_ALIGNMENT DEVICE_ATTRIBUTE_TEXTURE_PITCH_ALIGNMENT} - Pitch alignment requirement for textures</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_WIDTH} - Maximum cubemap texture width/height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_LAYERED_WIDTH} - Maximum cubemap layered texture width/height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_LAYERED_LAYERS} - Maximum layers in a cubemap layered texture</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_WIDTH} - Maximum 1D surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_WIDTH} - Maximum 2D surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_HEIGHT} - Maximum 2D surface height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_WIDTH} - Maximum 3D surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_HEIGHT} - Maximum 3D surface height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_DEPTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_DEPTH} - Maximum 3D surface depth</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_LAYERED_WIDTH} - Maximum 1D layered surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_LAYERED_LAYERS} - Maximum layers in a 1D layered surface</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_WIDTH} - Maximum 2D layered surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_HEIGHT} - Maximum 2D layered surface height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_LAYERS} - Maximum layers in a 2D layered surface</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_WIDTH} - Maximum cubemap surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_LAYERED_WIDTH} - Maximum cubemap layered surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_LAYERED_LAYERS} - Maximum layers in a cubemap layered surface</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LINEAR_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LINEAR_WIDTH} - Maximum 1D linear texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_WIDTH} - Maximum 2D linear texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_HEIGHT} - Maximum 2D linear texture height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_PITCH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_PITCH} - Maximum 2D linear texture pitch in bytes</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_MIPMAPPED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_MIPMAPPED_WIDTH} - Maximum mipmapped 2D texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_MIPMAPPED_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_MIPMAPPED_HEIGHT} - Maximum mipmapped 2D texture height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MAJOR DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MAJOR} - Major compute capability version number</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MINOR DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MINOR} - Minor compute capability version number</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_MIPMAPPED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_MIPMAPPED_WIDTH} - Maximum mipmapped 1D texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_STREAM_PRIORITIES_SUPPORTED DEVICE_ATTRIBUTE_STREAM_PRIORITIES_SUPPORTED} - Device supports stream priorities</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_GLOBAL_L1_CACHE_SUPPORTED DEVICE_ATTRIBUTE_GLOBAL_L1_CACHE_SUPPORTED} - Device supports caching globals in L1</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_LOCAL_L1_CACHE_SUPPORTED DEVICE_ATTRIBUTE_LOCAL_L1_CACHE_SUPPORTED} - Device supports caching locals in L1</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_MULTIPROCESSOR DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_MULTIPROCESSOR} - Maximum shared memory available per multiprocessor in bytes</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_REGISTERS_PER_MULTIPROCESSOR DEVICE_ATTRIBUTE_MAX_REGISTERS_PER_MULTIPROCESSOR} - Maximum number of 32-bit registers available per multiprocessor</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MANAGED_MEMORY DEVICE_ATTRIBUTE_MANAGED_MEMORY} - Device can allocate managed memory on this system</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MULTI_GPU_BOARD DEVICE_ATTRIBUTE_MULTI_GPU_BOARD} - Device is on a multi-GPU board</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MULTI_GPU_BOARD_GROUP_ID DEVICE_ATTRIBUTE_MULTI_GPU_BOARD_GROUP_ID} - Unique id for a group of devices on the same multi-GPU board</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_HOST_NATIVE_ATOMIC_SUPPORTED DEVICE_ATTRIBUTE_HOST_NATIVE_ATOMIC_SUPPORTED} - 
     * Link between the device and the host supports native atomic operations (this is a placeholder attribute, and is not supported on any current
     * hardware)
     * </li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_SINGLE_TO_DOUBLE_PRECISION_PERF_RATIO DEVICE_ATTRIBUTE_SINGLE_TO_DOUBLE_PRECISION_PERF_RATIO} - Ratio of single precision performance (in floating-point operations per second) to double precision performance</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_PAGEABLE_MEMORY_ACCESS DEVICE_ATTRIBUTE_PAGEABLE_MEMORY_ACCESS} - Device supports coherently accessing pageable memory without calling cudaHostRegister on it</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CONCURRENT_MANAGED_ACCESS DEVICE_ATTRIBUTE_CONCURRENT_MANAGED_ACCESS} - Device can coherently access managed memory concurrently with the CPU</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_COMPUTE_PREEMPTION_SUPPORTED DEVICE_ATTRIBUTE_COMPUTE_PREEMPTION_SUPPORTED} - Device supports compute preemption.</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CAN_USE_HOST_POINTER_FOR_REGISTERED_MEM DEVICE_ATTRIBUTE_CAN_USE_HOST_POINTER_FOR_REGISTERED_MEM} - Device can access host registered memory at the same virtual address as the CPU</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CAN_USE_STREAM_MEM_OPS DEVICE_ATTRIBUTE_CAN_USE_STREAM_MEM_OPS} - {@link CU80#cuStreamBatchMemOp StreamBatchMemOp} and related APIs are supported.</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CAN_USE_64_BIT_STREAM_MEM_OPS DEVICE_ATTRIBUTE_CAN_USE_64_BIT_STREAM_MEM_OPS} - 64-bit operations are supported in {@link CU80#cuStreamBatchMemOp StreamBatchMemOp} and related APIs.</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CAN_USE_STREAM_WAIT_VALUE_NOR DEVICE_ATTRIBUTE_CAN_USE_STREAM_WAIT_VALUE_NOR} - {@link CU80#CU_STREAM_WAIT_VALUE_NOR STREAM_WAIT_VALUE_NOR} is supported.</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_COOPERATIVE_LAUNCH DEVICE_ATTRIBUTE_COOPERATIVE_LAUNCH} - Device supports launching cooperative kernels via {@link CU90#cuLaunchCooperativeKernel LaunchCooperativeKernel}</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_COOPERATIVE_MULTI_DEVICE_LAUNCH DEVICE_ATTRIBUTE_COOPERATIVE_MULTI_DEVICE_LAUNCH} - Device can participate in cooperative kernels launched via {@link CU90#cuLaunchCooperativeKernelMultiDevice LaunchCooperativeKernelMultiDevice}</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_BLOCK_OPTIN DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_BLOCK_OPTIN} - Maximum optin shared memory per block</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CAN_FLUSH_REMOTE_WRITES DEVICE_ATTRIBUTE_CAN_FLUSH_REMOTE_WRITES} - Both the {@link CU80#CU_STREAM_WAIT_VALUE_FLUSH STREAM_WAIT_VALUE_FLUSH} flag and the {@link CU80#CU_STREAM_MEM_OP_FLUSH_REMOTE_WRITES STREAM_MEM_OP_FLUSH_REMOTE_WRITES} MemOp are supported on the device.</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_HOST_REGISTER_SUPPORTED DEVICE_ATTRIBUTE_HOST_REGISTER_SUPPORTED} - Device supports host memory registration via {@code cudaHostRegister()}.</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_PAGEABLE_MEMORY_ACCESS_USES_HOST_PAGE_TABLES DEVICE_ATTRIBUTE_PAGEABLE_MEMORY_ACCESS_USES_HOST_PAGE_TABLES} - Device accesses pageable memory via the host's page tables.</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_DIRECT_MANAGED_MEM_ACCESS_FROM_HOST DEVICE_ATTRIBUTE_DIRECT_MANAGED_MEM_ACCESS_FROM_HOST} - The host can directly access managed memory on the device without migration.</li>
     * </ul>
     */
    public static final int
        CU_DEVICE_ATTRIBUTE_MAX_THREADS_PER_BLOCK                        = 0x1,
        CU_DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_X                              = 0x2,
        CU_DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_Y                              = 0x3,
        CU_DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_Z                              = 0x4,
        CU_DEVICE_ATTRIBUTE_MAX_GRID_DIM_X                               = 0x5,
        CU_DEVICE_ATTRIBUTE_MAX_GRID_DIM_Y                               = 0x6,
        CU_DEVICE_ATTRIBUTE_MAX_GRID_DIM_Z                               = 0x7,
        CU_DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_BLOCK                  = 0x8,
        CU_DEVICE_ATTRIBUTE_SHARED_MEMORY_PER_BLOCK                      = 0x8,
        CU_DEVICE_ATTRIBUTE_TOTAL_CONSTANT_MEMORY                        = 0x9,
        CU_DEVICE_ATTRIBUTE_WARP_SIZE                                    = 0xA,
        CU_DEVICE_ATTRIBUTE_MAX_PITCH                                    = 0xB,
        CU_DEVICE_ATTRIBUTE_MAX_REGISTERS_PER_BLOCK                      = 0xC,
        CU_DEVICE_ATTRIBUTE_REGISTERS_PER_BLOCK                          = 0xC,
        CU_DEVICE_ATTRIBUTE_CLOCK_RATE                                   = 0xD,
        CU_DEVICE_ATTRIBUTE_TEXTURE_ALIGNMENT                            = 0xE,
        CU_DEVICE_ATTRIBUTE_GPU_OVERLAP                                  = 0xF,
        CU_DEVICE_ATTRIBUTE_MULTIPROCESSOR_COUNT                         = 0x10,
        CU_DEVICE_ATTRIBUTE_KERNEL_EXEC_TIMEOUT                          = 0x11,
        CU_DEVICE_ATTRIBUTE_INTEGRATED                                   = 0x12,
        CU_DEVICE_ATTRIBUTE_CAN_MAP_HOST_MEMORY                          = 0x13,
        CU_DEVICE_ATTRIBUTE_COMPUTE_MODE                                 = 0x14,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_WIDTH                      = 0x15,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_WIDTH                      = 0x16,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_HEIGHT                     = 0x17,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_WIDTH                      = 0x18,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_HEIGHT                     = 0x19,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_DEPTH                      = 0x1A,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_WIDTH              = 0x1B,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_HEIGHT             = 0x1C,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_LAYERS             = 0x1D,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_WIDTH                = 0x1B,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_HEIGHT               = 0x1C,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_NUMSLICES            = 0x1D,
        CU_DEVICE_ATTRIBUTE_SURFACE_ALIGNMENT                            = 0x1E,
        CU_DEVICE_ATTRIBUTE_CONCURRENT_KERNELS                           = 0x1F,
        CU_DEVICE_ATTRIBUTE_ECC_ENABLED                                  = 0x20,
        CU_DEVICE_ATTRIBUTE_PCI_BUS_ID                                   = 0x21,
        CU_DEVICE_ATTRIBUTE_PCI_DEVICE_ID                                = 0x22,
        CU_DEVICE_ATTRIBUTE_TCC_DRIVER                                   = 0x23,
        CU_DEVICE_ATTRIBUTE_MEMORY_CLOCK_RATE                            = 0x24,
        CU_DEVICE_ATTRIBUTE_GLOBAL_MEMORY_BUS_WIDTH                      = 0x25,
        CU_DEVICE_ATTRIBUTE_L2_CACHE_SIZE                                = 0x26,
        CU_DEVICE_ATTRIBUTE_MAX_THREADS_PER_MULTIPROCESSOR               = 0x27,
        CU_DEVICE_ATTRIBUTE_ASYNC_ENGINE_COUNT                           = 0x28,
        CU_DEVICE_ATTRIBUTE_UNIFIED_ADDRESSING                           = 0x29,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LAYERED_WIDTH              = 0x2A,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LAYERED_LAYERS             = 0x2B,
        CU_DEVICE_ATTRIBUTE_CAN_TEX2D_GATHER                             = 0x2C,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_GATHER_WIDTH               = 0x2D,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_GATHER_HEIGHT              = 0x2E,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_WIDTH_ALTERNATE            = 0x2F,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_HEIGHT_ALTERNATE           = 0x30,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_DEPTH_ALTERNATE            = 0x31,
        CU_DEVICE_ATTRIBUTE_PCI_DOMAIN_ID                                = 0x32,
        CU_DEVICE_ATTRIBUTE_TEXTURE_PITCH_ALIGNMENT                      = 0x33,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_WIDTH                 = 0x34,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_LAYERED_WIDTH         = 0x35,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_LAYERED_LAYERS        = 0x36,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_WIDTH                      = 0x37,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_WIDTH                      = 0x38,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_HEIGHT                     = 0x39,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_WIDTH                      = 0x3A,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_HEIGHT                     = 0x3B,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_DEPTH                      = 0x3C,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_LAYERED_WIDTH              = 0x3D,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_LAYERED_LAYERS             = 0x3E,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_WIDTH              = 0x3F,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_HEIGHT             = 0x40,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_LAYERS             = 0x41,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_WIDTH                 = 0x42,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_LAYERED_WIDTH         = 0x43,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_LAYERED_LAYERS        = 0x44,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LINEAR_WIDTH               = 0x45,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_WIDTH               = 0x46,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_HEIGHT              = 0x47,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_PITCH               = 0x48,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_MIPMAPPED_WIDTH            = 0x49,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_MIPMAPPED_HEIGHT           = 0x4A,
        CU_DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MAJOR                     = 0x4B,
        CU_DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MINOR                     = 0x4C,
        CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_MIPMAPPED_WIDTH            = 0x4D,
        CU_DEVICE_ATTRIBUTE_STREAM_PRIORITIES_SUPPORTED                  = 0x4E,
        CU_DEVICE_ATTRIBUTE_GLOBAL_L1_CACHE_SUPPORTED                    = 0x4F,
        CU_DEVICE_ATTRIBUTE_LOCAL_L1_CACHE_SUPPORTED                     = 0x50,
        CU_DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_MULTIPROCESSOR         = 0x51,
        CU_DEVICE_ATTRIBUTE_MAX_REGISTERS_PER_MULTIPROCESSOR             = 0x52,
        CU_DEVICE_ATTRIBUTE_MANAGED_MEMORY                               = 0x53,
        CU_DEVICE_ATTRIBUTE_MULTI_GPU_BOARD                              = 0x54,
        CU_DEVICE_ATTRIBUTE_MULTI_GPU_BOARD_GROUP_ID                     = 0x55,
        CU_DEVICE_ATTRIBUTE_HOST_NATIVE_ATOMIC_SUPPORTED                 = 0x56,
        CU_DEVICE_ATTRIBUTE_SINGLE_TO_DOUBLE_PRECISION_PERF_RATIO        = 0x57,
        CU_DEVICE_ATTRIBUTE_PAGEABLE_MEMORY_ACCESS                       = 0x58,
        CU_DEVICE_ATTRIBUTE_CONCURRENT_MANAGED_ACCESS                    = 0x59,
        CU_DEVICE_ATTRIBUTE_COMPUTE_PREEMPTION_SUPPORTED                 = 0x5A,
        CU_DEVICE_ATTRIBUTE_CAN_USE_HOST_POINTER_FOR_REGISTERED_MEM      = 0x5B,
        CU_DEVICE_ATTRIBUTE_CAN_USE_STREAM_MEM_OPS                       = 0x5C,
        CU_DEVICE_ATTRIBUTE_CAN_USE_64_BIT_STREAM_MEM_OPS                = 0x5D,
        CU_DEVICE_ATTRIBUTE_CAN_USE_STREAM_WAIT_VALUE_NOR                = 0x5E,
        CU_DEVICE_ATTRIBUTE_COOPERATIVE_LAUNCH                           = 0x5F,
        CU_DEVICE_ATTRIBUTE_COOPERATIVE_MULTI_DEVICE_LAUNCH              = 0x60,
        CU_DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_BLOCK_OPTIN            = 0x61,
        CU_DEVICE_ATTRIBUTE_CAN_FLUSH_REMOTE_WRITES                      = 0x62,
        CU_DEVICE_ATTRIBUTE_HOST_REGISTER_SUPPORTED                      = 0x63,
        CU_DEVICE_ATTRIBUTE_PAGEABLE_MEMORY_ACCESS_USES_HOST_PAGE_TABLES = 0x64,
        CU_DEVICE_ATTRIBUTE_DIRECT_MANAGED_MEM_ACCESS_FROM_HOST          = 0x65;

    /**
     * Function properties. ({@code CUfunction_attribute})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_FUNC_ATTRIBUTE_MAX_THREADS_PER_BLOCK FUNC_ATTRIBUTE_MAX_THREADS_PER_BLOCK} - 
     * The maximum number of threads per block, beyond which a launch of the function would fail. This number depends on both the function and the device
     * on which the function is currently loaded.
     * </li>
     * <li>{@link #CU_FUNC_ATTRIBUTE_SHARED_SIZE_BYTES FUNC_ATTRIBUTE_SHARED_SIZE_BYTES} - 
     * The size in bytes of statically-allocated shared memory required by this function. This does not include dynamically-allocated shared memory
     * requested by the user at runtime.
     * </li>
     * <li>{@link #CU_FUNC_ATTRIBUTE_CONST_SIZE_BYTES FUNC_ATTRIBUTE_CONST_SIZE_BYTES} - The size in bytes of user-allocated constant memory required by this function.</li>
     * <li>{@link #CU_FUNC_ATTRIBUTE_LOCAL_SIZE_BYTES FUNC_ATTRIBUTE_LOCAL_SIZE_BYTES} - The size in bytes of local memory used by each thread of this function.</li>
     * <li>{@link #CU_FUNC_ATTRIBUTE_NUM_REGS FUNC_ATTRIBUTE_NUM_REGS} - The number of registers used by each thread of this function.</li>
     * <li>{@link #CU_FUNC_ATTRIBUTE_PTX_VERSION FUNC_ATTRIBUTE_PTX_VERSION} - 
     * The PTX virtual architecture version for which the function was compiled.
     * 
     * <p>This value is the major PTX {@code version * 10 + the minor PTX version}, so a PTX version 1.3 function would return the value 13. Note that this
     * may return the undefined value of 0 for cubins compiled prior to CUDA 3.0.</p>
     * </li>
     * <li>{@link #CU_FUNC_ATTRIBUTE_BINARY_VERSION FUNC_ATTRIBUTE_BINARY_VERSION} - 
     * The binary architecture version for which the function was compiled.
     * 
     * <p>This value is the {@code major binary version * 10 + the minor binary version}, so a binary version 1.3 function would return the value 13. Note
     * that this will return a value of 10 for legacy cubins that do not have a properly-encoded binary architecture version.</p>
     * </li>
     * <li>{@link #CU_FUNC_ATTRIBUTE_CACHE_MODE_CA FUNC_ATTRIBUTE_CACHE_MODE_CA} - The attribute to indicate whether the function has been compiled with user specified option {@code "-Xptxas --dlcm=ca"} set.</li>
     * <li>{@link #CU_FUNC_ATTRIBUTE_MAX_DYNAMIC_SHARED_SIZE_BYTES FUNC_ATTRIBUTE_MAX_DYNAMIC_SHARED_SIZE_BYTES} - 
     * The maximum size in bytes of dynamically-allocated shared memory that can be used by this function.
     * 
     * <p>If the user-specified dynamic shared memory size is larger than this value, the launch will fail.</p>
     * </li>
     * <li>{@link #CU_FUNC_ATTRIBUTE_PREFERRED_SHARED_MEMORY_CARVEOUT FUNC_ATTRIBUTE_PREFERRED_SHARED_MEMORY_CARVEOUT} - 
     * On devices where the L1 cache and shared memory use the same hardware resources, this sets the shared memory carveout preference, in percent of the total shared memory. Refer to {@link #CU_DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_MULTIPROCESSOR DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_MULTIPROCESSOR}.
     * 
     * <p>This is only a hint, and the driver can choose a different ratio if required to execute the function.</p>
     * </li>
     * </ul>
     */
    public static final int
        CU_FUNC_ATTRIBUTE_MAX_THREADS_PER_BLOCK            = 0x0,
        CU_FUNC_ATTRIBUTE_SHARED_SIZE_BYTES                = 0x1,
        CU_FUNC_ATTRIBUTE_CONST_SIZE_BYTES                 = 0x2,
        CU_FUNC_ATTRIBUTE_LOCAL_SIZE_BYTES                 = 0x3,
        CU_FUNC_ATTRIBUTE_NUM_REGS                         = 0x4,
        CU_FUNC_ATTRIBUTE_PTX_VERSION                      = 0x5,
        CU_FUNC_ATTRIBUTE_BINARY_VERSION                   = 0x6,
        CU_FUNC_ATTRIBUTE_CACHE_MODE_CA                    = 0x7,
        CU_FUNC_ATTRIBUTE_MAX_DYNAMIC_SHARED_SIZE_BYTES    = 0x8,
        CU_FUNC_ATTRIBUTE_PREFERRED_SHARED_MEMORY_CARVEOUT = 0x9;

    /**
     * Function cache configurations. ({@code CUfunc_cache})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_FUNC_CACHE_PREFER_NONE FUNC_CACHE_PREFER_NONE} - no preference for shared memory or L1 (default)</li>
     * <li>{@link #CU_FUNC_CACHE_PREFER_SHARED FUNC_CACHE_PREFER_SHARED} - prefer larger shared memory and smaller L1 cache</li>
     * <li>{@link #CU_FUNC_CACHE_PREFER_L1 FUNC_CACHE_PREFER_L1} - prefer larger L1 cache and smaller shared memory</li>
     * <li>{@link #CU_FUNC_CACHE_PREFER_EQUAL FUNC_CACHE_PREFER_EQUAL} - prefer equal sized L1 cache and shared memory</li>
     * </ul>
     */
    public static final int
        CU_FUNC_CACHE_PREFER_NONE   = 0x0,
        CU_FUNC_CACHE_PREFER_SHARED = 0x1,
        CU_FUNC_CACHE_PREFER_L1     = 0x2,
        CU_FUNC_CACHE_PREFER_EQUAL  = 0x3;

    /**
     * Memory types. ({@code CUmemorytype})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_MEMORYTYPE_HOST MEMORYTYPE_HOST} - Host memory</li>
     * <li>{@link #CU_MEMORYTYPE_DEVICE MEMORYTYPE_DEVICE} - Device memory</li>
     * <li>{@link #CU_MEMORYTYPE_ARRAY MEMORYTYPE_ARRAY} - Array memory</li>
     * <li>{@link #CU_MEMORYTYPE_UNIFIED MEMORYTYPE_UNIFIED} - Unified device or host memory</li>
     * </ul>
     */
    public static final int
        CU_MEMORYTYPE_HOST    = 0x1,
        CU_MEMORYTYPE_DEVICE  = 0x2,
        CU_MEMORYTYPE_ARRAY   = 0x3,
        CU_MEMORYTYPE_UNIFIED = 0x4;

    /**
     * Compute Modes. ({@code CUcomputemode})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_COMPUTEMODE_DEFAULT COMPUTEMODE_DEFAULT} - Default compute mode (Multiple contexts allowed per device)</li>
     * <li>{@link #CU_COMPUTEMODE_PROHIBITED COMPUTEMODE_PROHIBITED} - Compute-prohibited mode (No contexts can be created on this device at this time)</li>
     * <li>{@link #CU_COMPUTEMODE_EXCLUSIVE_PROCESS COMPUTEMODE_EXCLUSIVE_PROCESS} - Compute-exclusive-process mode (Only one context used by a single process can be present on this device at a time)</li>
     * </ul>
     */
    public static final int
        CU_COMPUTEMODE_DEFAULT           = 0x0,
        CU_COMPUTEMODE_PROHIBITED        = 0x2,
        CU_COMPUTEMODE_EXCLUSIVE_PROCESS = 0x3;

    /**
     * Online compiler and linker options. ({@code CUjit_option})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_JIT_MAX_REGISTERS JIT_MAX_REGISTERS} - 
     * Max number of registers that a thread may use.
     * 
     * <p>Option type: {@code unsigned int}. Applies to: compiler only</p>
     * </li>
     * <li>{@link #CU_JIT_THREADS_PER_BLOCK JIT_THREADS_PER_BLOCK} - 
     * IN: Specifies minimum number of threads per block to target compilation for
     * 
     * <p>OUT: Returns the number of threads the compiler actually targeted.</p>
     * 
     * <p>This restricts the resource utilization fo the compiler (e.g. max registers) such that a block with the given number of threads should be able to
     * launch based on register limitations. Note, this option does not currently take into account any other resource limitations, such as shared memory
     * utilization.</p>
     * 
     * <p>Cannot be combined with {@link #CU_JIT_TARGET JIT_TARGET}. Option type: {@code unsigned int}. Applies to: compiler only</p>
     * </li>
     * <li>{@link #CU_JIT_WALL_TIME JIT_WALL_TIME} - 
     * Overwrites the option value with the total wall clock time, in milliseconds, spent in the compiler and linker.
     * 
     * <p>Option type: {@code float}. Applies to: compiler and linker</p>
     * </li>
     * <li>{@link #CU_JIT_INFO_LOG_BUFFER JIT_INFO_LOG_BUFFER} - 
     * Pointer to a buffer in which to print any log messages that are informational in nature (the buffer size is specified via option
     * {@link #CU_JIT_INFO_LOG_BUFFER_SIZE_BYTES JIT_INFO_LOG_BUFFER_SIZE_BYTES}).
     * 
     * <p>Option type: {@code char *}. Applies to: compiler and linker</p>
     * </li>
     * <li>{@link #CU_JIT_INFO_LOG_BUFFER_SIZE_BYTES JIT_INFO_LOG_BUFFER_SIZE_BYTES} - 
     * IN: Log buffer size in bytes. Log messages will be capped at this size (including null terminator).
     * 
     * <p>OUT: Amount of log buffer filled with messages.</p>
     * 
     * <p>Option type: {@code unsigned int}. Applies to: compiler and linker</p>
     * </li>
     * <li>{@link #CU_JIT_ERROR_LOG_BUFFER JIT_ERROR_LOG_BUFFER} - 
     * Pointer to a buffer in which to print any log messages that reflect errors (the buffer size is specified via option
     * {@link #CU_JIT_ERROR_LOG_BUFFER_SIZE_BYTES JIT_ERROR_LOG_BUFFER_SIZE_BYTES}).
     * 
     * <p>Option type: {@code char *}. Applies to: compiler and linker</p>
     * </li>
     * <li>{@link #CU_JIT_ERROR_LOG_BUFFER_SIZE_BYTES JIT_ERROR_LOG_BUFFER_SIZE_BYTES} - 
     * IN: Log buffer size in bytes. Log messages will be capped at this size (including null terminator).
     * 
     * <p>OUT: Amount of log buffer filled with messages.</p>
     * 
     * <p>Option type: {@code unsigned int}. Applies to: compiler and linker</p>
     * </li>
     * <li>{@link #CU_JIT_OPTIMIZATION_LEVEL JIT_OPTIMIZATION_LEVEL} - 
     * Level of optimizations to apply to generated code (0 - 4), with 4 being the default and highest level of optimizations.
     * 
     * <p>Option type: {@code unsigned int}. Applies to: compiler only</p>
     * </li>
     * <li>{@link #CU_JIT_TARGET_FROM_CUCONTEXT JIT_TARGET_FROM_CUCONTEXT} - 
     * No option value required. Determines the target based on the current attached context (default).
     * 
     * <p>Option type: No option value needed. Applies to: compiler and linker</p>
     * </li>
     * <li>{@link #CU_JIT_TARGET JIT_TARGET} - 
     * Target is chosen based on supplied {@code CUjit_target}. Cannot be combined with {@link #CU_JIT_THREADS_PER_BLOCK JIT_THREADS_PER_BLOCK}.
     * 
     * <p>Option type: {@code unsigned int} for enumerated type {@code CUjit_target}. Applies to: compiler and linker</p>
     * </li>
     * <li>{@link #CU_JIT_FALLBACK_STRATEGY JIT_FALLBACK_STRATEGY} - 
     * Specifies choice of fallback strategy if matching cubin is not found.
     * 
     * <p>Choice is based on supplied {@code CUjit_fallback}. This option cannot be used with {@code cuLink*} APIs as the linker requires exact matches.</p>
     * 
     * <p>Option type: {@code unsigned int} for enumerated type {@code CUjit_fallback}. Applies to: compiler only</p>
     * </li>
     * <li>{@link #CU_JIT_GENERATE_DEBUG_INFO JIT_GENERATE_DEBUG_INFO} - 
     * Specifies whether to create debug information in output (-g) (0: false, default).
     * 
     * <p>Option type: {@code int}. Applies to: compiler and linker</p>
     * </li>
     * <li>{@link #CU_JIT_LOG_VERBOSE JIT_LOG_VERBOSE} - 
     * Generate verbose log messages (0: false, default).
     * 
     * <p>Option type: {@code int}. Applies to: compiler and linker</p>
     * </li>
     * <li>{@link #CU_JIT_GENERATE_LINE_INFO JIT_GENERATE_LINE_INFO} - 
     * Generate line number information (-lineinfo) (0: false, default).
     * 
     * <p>Option type: {@code int}. Applies to: compiler only</p>
     * </li>
     * <li>{@link #CU_JIT_CACHE_MODE JIT_CACHE_MODE} - 
     * Specifies whether to enable caching explicitly (-dlcm). Choice is based on supplied {@code CUjit_cacheMode_enum}.
     * 
     * <p>Option type: {@code unsigned int} for enumerated type {@code CUjit_cacheMode_enum}. Applies to: compiler only</p>
     * </li>
     * <li>{@link #CU_JIT_NEW_SM3X_OPT JIT_NEW_SM3X_OPT} - Used for internal purposes only, in this version of CUDA.</li>
     * <li>{@link #CU_JIT_FAST_COMPILE JIT_FAST_COMPILE} - Used for internal purposes only, in this version of CUDA.</li>
     * <li>{@link #CU_JIT_GLOBAL_SYMBOL_NAMES JIT_GLOBAL_SYMBOL_NAMES} - 
     * Array of device symbol names that will be relocated to the corresponing host addresses stored in {@link #CU_JIT_GLOBAL_SYMBOL_ADDRESSES JIT_GLOBAL_SYMBOL_ADDRESSES}.
     * 
     * <p>Must contain {@link #CU_JIT_GLOBAL_SYMBOL_COUNT JIT_GLOBAL_SYMBOL_COUNT} entries. When loding a device module, driver will relocate all encountered unresolved symbols to the host
     * addresses. It is only allowed to register symbols that correspond to unresolved global variables. It is illegal to register the same device symbol
     * at multiple addresses.</p>
     * 
     * <p>Option type: {@code const char **}. Applies to: dynamic linker only</p>
     * </li>
     * <li>{@link #CU_JIT_GLOBAL_SYMBOL_ADDRESSES JIT_GLOBAL_SYMBOL_ADDRESSES} - 
     * Array of host addresses that will be used to relocate corresponding device symbols stored in {@link #CU_JIT_GLOBAL_SYMBOL_NAMES JIT_GLOBAL_SYMBOL_NAMES}.
     * 
     * <p>Must contain {@link #CU_JIT_GLOBAL_SYMBOL_COUNT JIT_GLOBAL_SYMBOL_COUNT} entries.</p>
     * 
     * <p>Option type: {@code void **}. Applies to: dynamic linker only</p>
     * </li>
     * <li>{@link #CU_JIT_GLOBAL_SYMBOL_COUNT JIT_GLOBAL_SYMBOL_COUNT} - 
     * Number of entries in {@link #CU_JIT_GLOBAL_SYMBOL_NAMES JIT_GLOBAL_SYMBOL_NAMES} and {@link #CU_JIT_GLOBAL_SYMBOL_ADDRESSES JIT_GLOBAL_SYMBOL_ADDRESSES} arrays.
     * 
     * <p>Option type: {@code unsigned int}. Applies to: dynamic linker only</p>
     * </li>
     * </ul>
     */
    public static final int
        CU_JIT_MAX_REGISTERS               = 0x0,
        CU_JIT_THREADS_PER_BLOCK           = 0x1,
        CU_JIT_WALL_TIME                   = 0x2,
        CU_JIT_INFO_LOG_BUFFER             = 0x3,
        CU_JIT_INFO_LOG_BUFFER_SIZE_BYTES  = 0x4,
        CU_JIT_ERROR_LOG_BUFFER            = 0x5,
        CU_JIT_ERROR_LOG_BUFFER_SIZE_BYTES = 0x6,
        CU_JIT_OPTIMIZATION_LEVEL          = 0x7,
        CU_JIT_TARGET_FROM_CUCONTEXT       = 0x8,
        CU_JIT_TARGET                      = 0x9,
        CU_JIT_FALLBACK_STRATEGY           = 0xA,
        CU_JIT_GENERATE_DEBUG_INFO         = 0xB,
        CU_JIT_LOG_VERBOSE                 = 0xC,
        CU_JIT_GENERATE_LINE_INFO          = 0xD,
        CU_JIT_CACHE_MODE                  = 0xE,
        CU_JIT_NEW_SM3X_OPT                = 0xF,
        CU_JIT_FAST_COMPILE                = 0x10,
        CU_JIT_GLOBAL_SYMBOL_NAMES         = 0x11,
        CU_JIT_GLOBAL_SYMBOL_ADDRESSES     = 0x12,
        CU_JIT_GLOBAL_SYMBOL_COUNT         = 0x13;

    /**
     * Online compilation targets. ({@code CUjit_target})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_TARGET_COMPUTE_20 TARGET_COMPUTE_20} - Compute device class 2.0</li>
     * <li>{@link #CU_TARGET_COMPUTE_21 TARGET_COMPUTE_21} - Compute device class 2.1</li>
     * <li>{@link #CU_TARGET_COMPUTE_30 TARGET_COMPUTE_30} - Compute device class 3.0</li>
     * <li>{@link #CU_TARGET_COMPUTE_32 TARGET_COMPUTE_32} - Compute device class 3.2</li>
     * <li>{@link #CU_TARGET_COMPUTE_35 TARGET_COMPUTE_35} - Compute device class 3.5</li>
     * <li>{@link #CU_TARGET_COMPUTE_37 TARGET_COMPUTE_37} - Compute device class 3.7</li>
     * <li>{@link #CU_TARGET_COMPUTE_50 TARGET_COMPUTE_50} - Compute device class 5.0</li>
     * <li>{@link #CU_TARGET_COMPUTE_52 TARGET_COMPUTE_52} - Compute device class 5.2</li>
     * <li>{@link #CU_TARGET_COMPUTE_53 TARGET_COMPUTE_53} - Compute device class 5.3</li>
     * <li>{@link #CU_TARGET_COMPUTE_60 TARGET_COMPUTE_60} - Compute device class 6.0.</li>
     * <li>{@link #CU_TARGET_COMPUTE_61 TARGET_COMPUTE_61} - Compute device class 6.1.</li>
     * <li>{@link #CU_TARGET_COMPUTE_62 TARGET_COMPUTE_62} - Compute device class 6.2.</li>
     * <li>{@link #CU_TARGET_COMPUTE_70 TARGET_COMPUTE_70} - Compute device class 7.0.</li>
     * <li>{@link #CU_TARGET_COMPUTE_72 TARGET_COMPUTE_72} - Compute device class 7.2.</li>
     * <li>{@link #CU_TARGET_COMPUTE_75 TARGET_COMPUTE_75} - Compute device class 7.5.</li>
     * </ul>
     */
    public static final int
        CU_TARGET_COMPUTE_20 = 0x14,
        CU_TARGET_COMPUTE_21 = 0x15,
        CU_TARGET_COMPUTE_30 = 0x1E,
        CU_TARGET_COMPUTE_32 = 0x20,
        CU_TARGET_COMPUTE_35 = 0x23,
        CU_TARGET_COMPUTE_37 = 0x25,
        CU_TARGET_COMPUTE_50 = 0x32,
        CU_TARGET_COMPUTE_52 = 0x34,
        CU_TARGET_COMPUTE_53 = 0x35,
        CU_TARGET_COMPUTE_60 = 0x3C,
        CU_TARGET_COMPUTE_61 = 0x3D,
        CU_TARGET_COMPUTE_62 = 0x3E,
        CU_TARGET_COMPUTE_70 = 0x46,
        CU_TARGET_COMPUTE_72 = 0x48,
        CU_TARGET_COMPUTE_75 = 0x4B;

    /**
     * Cubin matching fallback strategies. ({@code CUjit_fallback})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_PREFER_PTX PREFER_PTX} - Prefer to compile ptx if exact binary match not found</li>
     * <li>{@link #CU_PREFER_BINARY PREFER_BINARY} - Prefer to fall back to compatible binary code if exact match not found</li>
     * </ul>
     */
    public static final int
        CU_PREFER_PTX    = 0x0,
        CU_PREFER_BINARY = 0x1;

    /**
     * Caching modes for {@code dlcm}. ({@code CUjit_cacheMode})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_JIT_CACHE_OPTION_NONE JIT_CACHE_OPTION_NONE} - Compile with no -dlcm flag specified</li>
     * <li>{@link #CU_JIT_CACHE_OPTION_CG JIT_CACHE_OPTION_CG} - Compile with L1 cache disabled</li>
     * <li>{@link #CU_JIT_CACHE_OPTION_CA JIT_CACHE_OPTION_CA} - Compile with L1 cache enabled</li>
     * </ul>
     */
    public static final int
        CU_JIT_CACHE_OPTION_NONE = 0x0,
        CU_JIT_CACHE_OPTION_CG   = 0x1,
        CU_JIT_CACHE_OPTION_CA   = 0x2;

    /**
     * Flags to register a graphics resource. ({@code CUgraphicsRegisterFlags})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_GRAPHICS_REGISTER_FLAGS_NONE GRAPHICS_REGISTER_FLAGS_NONE}</li>
     * <li>{@link #CU_GRAPHICS_REGISTER_FLAGS_READ_ONLY GRAPHICS_REGISTER_FLAGS_READ_ONLY}</li>
     * <li>{@link #CU_GRAPHICS_REGISTER_FLAGS_WRITE_DISCARD GRAPHICS_REGISTER_FLAGS_WRITE_DISCARD}</li>
     * <li>{@link #CU_GRAPHICS_REGISTER_FLAGS_SURFACE_LDST GRAPHICS_REGISTER_FLAGS_SURFACE_LDST}</li>
     * <li>{@link #CU_GRAPHICS_REGISTER_FLAGS_TEXTURE_GATHER GRAPHICS_REGISTER_FLAGS_TEXTURE_GATHER}</li>
     * </ul>
     */
    public static final int
        CU_GRAPHICS_REGISTER_FLAGS_NONE           = 0x0,
        CU_GRAPHICS_REGISTER_FLAGS_READ_ONLY      = 0x1,
        CU_GRAPHICS_REGISTER_FLAGS_WRITE_DISCARD  = 0x2,
        CU_GRAPHICS_REGISTER_FLAGS_SURFACE_LDST   = 0x4,
        CU_GRAPHICS_REGISTER_FLAGS_TEXTURE_GATHER = 0x8;

    /**
     * Flags for mapping and unmapping interop resources. ({@code CUgraphicsMapResourceFlags})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_GRAPHICS_MAP_RESOURCE_FLAGS_NONE GRAPHICS_MAP_RESOURCE_FLAGS_NONE}</li>
     * <li>{@link #CU_GRAPHICS_MAP_RESOURCE_FLAGS_READ_ONLY GRAPHICS_MAP_RESOURCE_FLAGS_READ_ONLY}</li>
     * <li>{@link #CU_GRAPHICS_MAP_RESOURCE_FLAGS_WRITE_DISCARD GRAPHICS_MAP_RESOURCE_FLAGS_WRITE_DISCARD}</li>
     * </ul>
     */
    public static final int
        CU_GRAPHICS_MAP_RESOURCE_FLAGS_NONE          = 0x0,
        CU_GRAPHICS_MAP_RESOURCE_FLAGS_READ_ONLY     = 0x1,
        CU_GRAPHICS_MAP_RESOURCE_FLAGS_WRITE_DISCARD = 0x2;

    /**
     * Array indices for cube faces. ({@code CUarray_cubemap_face})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_CUBEMAP_FACE_POSITIVE_X CUBEMAP_FACE_POSITIVE_X} - Positive X face of cubemap</li>
     * <li>{@link #CU_CUBEMAP_FACE_NEGATIVE_X CUBEMAP_FACE_NEGATIVE_X} - Negative X face of cubemap</li>
     * <li>{@link #CU_CUBEMAP_FACE_POSITIVE_Y CUBEMAP_FACE_POSITIVE_Y} - Positive Y face of cubemap</li>
     * <li>{@link #CU_CUBEMAP_FACE_NEGATIVE_Y CUBEMAP_FACE_NEGATIVE_Y} - Negative Y face of cubemap</li>
     * <li>{@link #CU_CUBEMAP_FACE_POSITIVE_Z CUBEMAP_FACE_POSITIVE_Z} - Positive Z face of cubemap</li>
     * <li>{@link #CU_CUBEMAP_FACE_NEGATIVE_Z CUBEMAP_FACE_NEGATIVE_Z} - Negative Z face of cubemap</li>
     * </ul>
     */
    public static final int
        CU_CUBEMAP_FACE_POSITIVE_X = 0x0,
        CU_CUBEMAP_FACE_NEGATIVE_X = 0x1,
        CU_CUBEMAP_FACE_POSITIVE_Y = 0x2,
        CU_CUBEMAP_FACE_NEGATIVE_Y = 0x3,
        CU_CUBEMAP_FACE_POSITIVE_Z = 0x4,
        CU_CUBEMAP_FACE_NEGATIVE_Z = 0x5;

    /**
     * Limits. ({@code CUlimit})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_LIMIT_STACK_SIZE LIMIT_STACK_SIZE} - GPU thread stack size</li>
     * <li>{@link #CU_LIMIT_PRINTF_FIFO_SIZE LIMIT_PRINTF_FIFO_SIZE} - GPU printf FIFO size</li>
     * <li>{@link #CU_LIMIT_MALLOC_HEAP_SIZE LIMIT_MALLOC_HEAP_SIZE} - GPU malloc heap size</li>
     * <li>{@link #CU_LIMIT_DEV_RUNTIME_SYNC_DEPTH LIMIT_DEV_RUNTIME_SYNC_DEPTH} - GPU device runtime launch synchronize depth</li>
     * <li>{@link #CU_LIMIT_DEV_RUNTIME_PENDING_LAUNCH_COUNT LIMIT_DEV_RUNTIME_PENDING_LAUNCH_COUNT} - GPU device runtime pending launch count</li>
     * <li>{@link #CU_LIMIT_MAX_L2_FETCH_GRANULARITY LIMIT_MAX_L2_FETCH_GRANULARITY} - A value between 0 and 128 that indicates the maximum fetch granularity of L2 (in Bytes). This is a hint</li>
     * </ul>
     */
    public static final int
        CU_LIMIT_STACK_SIZE                       = 0x0,
        CU_LIMIT_PRINTF_FIFO_SIZE                 = 0x1,
        CU_LIMIT_MALLOC_HEAP_SIZE                 = 0x2,
        CU_LIMIT_DEV_RUNTIME_SYNC_DEPTH           = 0x3,
        CU_LIMIT_DEV_RUNTIME_PENDING_LAUNCH_COUNT = 0x4,
        CU_LIMIT_MAX_L2_FETCH_GRANULARITY         = 0x5;

    /**
     * Error codes. ({@code CUresult})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CUDA_SUCCESS CUDA_SUCCESS} - 
     * The API call returned with no errors.
     * 
     * <p>In the case of query calls, this also means that the operation being queried is complete (see {@link #cuEventQuery EventQuery} and {@link #cuStreamQuery StreamQuery}).</p>
     * </li>
     * <li>{@link #CUDA_ERROR_INVALID_VALUE CUDA_ERROR_INVALID_VALUE} - This indicates that one or more of the parameters passed to the API call is not within an acceptable range of values.</li>
     * <li>{@link #CUDA_ERROR_OUT_OF_MEMORY CUDA_ERROR_OUT_OF_MEMORY} - The API call failed because it was unable to allocate enough memory to perform the requested operation.</li>
     * <li>{@link #CUDA_ERROR_NOT_INITIALIZED CUDA_ERROR_NOT_INITIALIZED} - This indicates that the CUDA driver has not been initialized with {@link #cuInit Init} or that initialization has failed.</li>
     * <li>{@link #CUDA_ERROR_DEINITIALIZED CUDA_ERROR_DEINITIALIZED} - This indicates that the CUDA driver is in the process of shutting down.</li>
     * <li>{@link #CUDA_ERROR_PROFILER_DISABLED CUDA_ERROR_PROFILER_DISABLED} - 
     * This indicates profiler is not initialized for this run. This can happen when the application is running with external profiling tools like visual
     * profiler.
     * </li>
     * <li>{@link #CUDA_ERROR_PROFILER_NOT_INITIALIZED CUDA_ERROR_PROFILER_NOT_INITIALIZED} - 
     * This error return is deprecated as of CUDA 5.0.
     * 
     * <p>It is no longer an error to attempt to enable/disable the profiling via {@link CUDAProfiler#cuProfilerStart ProfilerStart} or {@link CUDAProfiler#cuProfilerStop ProfilerStop} without initialization.</p>
     * </li>
     * <li>{@link #CUDA_ERROR_PROFILER_ALREADY_STARTED CUDA_ERROR_PROFILER_ALREADY_STARTED} - 
     * This error return is deprecated as of CUDA 5.0.
     * 
     * <p>It is no longer an error to call {@link CUDAProfiler#cuProfilerStart ProfilerStart} when profiling is already enabled.</p>
     * </li>
     * <li>{@link #CUDA_ERROR_PROFILER_ALREADY_STOPPED CUDA_ERROR_PROFILER_ALREADY_STOPPED} - 
     * This error return is deprecated as of CUDA 5.0.
     * 
     * <p>It is no longer an error to call {@link CUDAProfiler#cuProfilerStop ProfilerStop} when profiling is already disabled.</p>
     * </li>
     * <li>{@link #CUDA_ERROR_NO_DEVICE CUDA_ERROR_NO_DEVICE} - This indicates that no CUDA-capable devices were detected by the installed CUDA driver.</li>
     * <li>{@link #CUDA_ERROR_INVALID_DEVICE CUDA_ERROR_INVALID_DEVICE} - This indicates that the device ordinal supplied by the user does not correspond to a valid CUDA device.</li>
     * <li>{@link #CUDA_ERROR_INVALID_IMAGE CUDA_ERROR_INVALID_IMAGE} - This indicates that the device kernel image is invalid. This can also indicate an invalid CUDA module.</li>
     * <li>{@link #CUDA_ERROR_INVALID_CONTEXT CUDA_ERROR_INVALID_CONTEXT} - 
     * This most frequently indicates that there is no context bound to the current thread.
     * This can also be returned if the context passed to an API call is not a valid handle (such as a context that has had {@link CU40#cuCtxDestroy CtxDestroy} invoked on it).
     * This can also be returned if a user mixes different API versions (i.e. 3010 context with 3020 API calls).
     * 
     * <p>See {@link #cuCtxGetApiVersion CtxGetApiVersion} for more details.</p>
     * </li>
     * <li>{@link #CUDA_ERROR_CONTEXT_ALREADY_CURRENT CUDA_ERROR_CONTEXT_ALREADY_CURRENT} - 
     * This indicated that the context being supplied as a parameter to the API call was already the active context.
     * 
     * <p>This error return is deprecated as of CUDA 3.2. It is no longer an error to attempt to push the active context via {@link CU40#cuCtxPushCurrent CtxPushCurrent}.</p>
     * </li>
     * <li>{@link #CUDA_ERROR_MAP_FAILED CUDA_ERROR_MAP_FAILED} - This indicates that a map or register operation has failed.</li>
     * <li>{@link #CUDA_ERROR_UNMAP_FAILED CUDA_ERROR_UNMAP_FAILED} - This indicates that an unmap or unregister operation has failed.</li>
     * <li>{@link #CUDA_ERROR_ARRAY_IS_MAPPED CUDA_ERROR_ARRAY_IS_MAPPED} - This indicates that the specified array is currently mapped and thus cannot be destroyed.</li>
     * <li>{@link #CUDA_ERROR_ALREADY_MAPPED CUDA_ERROR_ALREADY_MAPPED} - This indicates that the resource is already mapped.</li>
     * <li>{@link #CUDA_ERROR_NO_BINARY_FOR_GPU CUDA_ERROR_NO_BINARY_FOR_GPU} - 
     * This indicates that there is no kernel image available that is suitable for the device.
     * 
     * <p>This can occur when a user specifies code generation options for a particular CUDA source file that do not include the corresponding device
     * configuration.</p>
     * </li>
     * <li>{@link #CUDA_ERROR_ALREADY_ACQUIRED CUDA_ERROR_ALREADY_ACQUIRED} - This indicates that a resource has already been acquired.</li>
     * <li>{@link #CUDA_ERROR_NOT_MAPPED CUDA_ERROR_NOT_MAPPED} - This indicates that a resource is not mapped.</li>
     * <li>{@link #CUDA_ERROR_NOT_MAPPED_AS_ARRAY CUDA_ERROR_NOT_MAPPED_AS_ARRAY} - This indicates that a mapped resource is not available for access as an array.</li>
     * <li>{@link #CUDA_ERROR_NOT_MAPPED_AS_POINTER CUDA_ERROR_NOT_MAPPED_AS_POINTER} - This indicates that a mapped resource is not available for access as a pointer.</li>
     * <li>{@link #CUDA_ERROR_ECC_UNCORRECTABLE CUDA_ERROR_ECC_UNCORRECTABLE} - This indicates that an uncorrectable ECC error was detected during execution.</li>
     * <li>{@link #CUDA_ERROR_UNSUPPORTED_LIMIT CUDA_ERROR_UNSUPPORTED_LIMIT} - This indicates that the {@code CUlimit} passed to the API call is not supported by the active device.</li>
     * <li>{@link #CUDA_ERROR_CONTEXT_ALREADY_IN_USE CUDA_ERROR_CONTEXT_ALREADY_IN_USE} - 
     * This indicates that the {@code CUcontext} passed to the API call can only be bound to a single CPU thread at a time but is already bound to a CPU
     * thread.
     * </li>
     * <li>{@link #CUDA_ERROR_PEER_ACCESS_UNSUPPORTED CUDA_ERROR_PEER_ACCESS_UNSUPPORTED} - This indicates that peer access is not supported across the given devices.</li>
     * <li>