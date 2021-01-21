package world.gregs.void.engine.entity.character.player

/**
 * @author GregHib <greg@gregs.world>
 * @since April 18, 2020
 */
data class GameLoginInfo(
    val username: String,
    val password: String,
    val isaacKeys: IntArray,
    val mode: Int,
    val width: Int,
    val height: Int,
    val antialias: Int,
    val settings: String,
    val affiliate: Int,
    val session: Int,
    val os: Int,
    val is64Bit: Int,
    val versionType: Int,
    val vendorType: Int,
    val javaRelease: Int,
    val javaVersion: Int,
    val javaUpdate: Int,
    val isUnsigned: Int,
    val heapSize: Int,
    val processorCount: Int,
    val totalMemory: Int
)