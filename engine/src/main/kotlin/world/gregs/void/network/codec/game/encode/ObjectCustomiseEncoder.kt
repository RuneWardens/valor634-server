package world.gregs.void.network.codec.game.encode

import world.gregs.void.buffer.Endian
import world.gregs.void.buffer.Modifier
import world.gregs.void.buffer.write.writeByte
import world.gregs.void.buffer.write.writeShort
import world.gregs.void.engine.entity.character.player.Player
import world.gregs.void.network.codec.Encoder
import world.gregs.void.network.codec.game.GameOpcodes.OBJECT_CUSTOMISE
import world.gregs.void.network.packet.PacketSize

/**
 * @author GregHib <greg@gregs.world>
 * @since June 27, 2020
 */
class ObjectCustomiseEncoder : Encoder(OBJECT_CUSTOMISE, PacketSize.BYTE) {

    /**
     * Note: Populated arrays must be exact same size as originals
     * @param tile The tile offset from the chunk update send
     * @param id Object id
     * @param type Object type
     * @param modelIds Replacement model ids
     * @param colours Replacement colours
     * @param textureColours Replacement texture colours
     * @param clear Clear previous customisations
     */
    fun encode(
        player: Player,
        tile: Int,
        id: Int,
        type: Int,
        modelIds: IntArray? = null,
        colours: IntArray? = null,
        textureColours: IntArray? = null,
        clear: Boolean = false
    ) = player.send(getLength(modelIds, colours, textureColours)) {
        writeByte(type, type = Modifier.ADD)
        var flag = 0
        if (clear) {
            flag = flag or 0x1
        }
        if (modelIds != null) {
            flag = flag or 0x2
        }
        if (colours != null) {
            flag = flag or 0x4
        }
        if (textureColours != null) {
            flag = flag or 0x8
        }
        writeByte(flag)
        writeByte(tile, type = Modifier.SUBTRACT)
        writeShort(id, order = Endian.LITTLE)
        modelIds?.forEach { modelId ->
            writeShort(modelId)
        }
        colours?.forEach { colour ->
            writeShort(colour)
        }
        textureColours?.forEach { textureColour ->
            writeShort(textureColour)
        }
    }

    private fun getLength(modelIds: IntArray?, colours: IntArray?, textureColours: IntArray?): Int {
        var count = 5
        count += (modelIds?.size ?: 0) * 2
        count += (colours?.size ?: 0) * 2
        count += (textureColours?.size ?: 0) * 2
        return count
    }
}